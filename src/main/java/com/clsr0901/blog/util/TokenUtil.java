package com.clsr0901.blog.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.Date;

@Slf4j
public class TokenUtil {
    public static String getToken(String name, Integer token_expiration) {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "mySecretKey".toCharArray());
        return Jwts.builder()
                .setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + token_expiration))
                .signWith(SignatureAlgorithm.RS256, keyStoreKeyFactory.getKeyPair("jwt").getPrivate()) //RSA非对称私钥加密
                .compact();
    }

    public static String parseToken(String token) {
        String username = "";
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "mySecretKey".toCharArray());
        if (token != null) {
            try {
                JwtParser jwtParser = Jwts.parser().setSigningKey(keyStoreKeyFactory.getKeyPair("jwt").getPublic());//RSA非对称公钥解密
                Jws<Claims> jws = jwtParser.parseClaimsJws(token.replace("Bearer ", ""));
                Claims claims = jws.getBody();
                username = claims.getSubject();
            }catch (JwtException e){
                log.error("验证token异常 e = {}", e);
            }
        }
        return username;
    }
}
