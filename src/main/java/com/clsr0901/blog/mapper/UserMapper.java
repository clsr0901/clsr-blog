package com.clsr0901.blog.mapper;

import com.clsr0901.blog.entity.Role;
import com.clsr0901.blog.entity.User;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    public static final String DBNAME = " t_user ";
    public static final String ID = " id ";
    public static final String AVATAR = " avatar ";
    public static final String EMAIL = " email ";
    public static final String PASSWORD = " password ";
    public static final String USERNAME = " username ";
    public static final String PHONE = " phone ";
    public static final String INSTRUCTION = " instruction ";
    public static final String CREATETIME = " createtime ";
    public static final String DB_FIELDS = " ( " + AVATAR + "," + EMAIL + "," + USERNAME + "," + PASSWORD + "," + PHONE + "," + INSTRUCTION + " ) ";
    public static final String ALL_FILEDS = ID + "," + AVATAR + "," + EMAIL + "," + PASSWORD + "," + USERNAME + ","
            + PHONE + "," + INSTRUCTION + "," + CREATETIME;

    @Insert({"insert into", DBNAME, DB_FIELDS,  " values (#{avatar}, #{email}, #{username}, #{password}, #{phone}, #{instruction})"})
    @Options(useGeneratedKeys=true,keyProperty="id")
    public void insert(User user);

    @Update({"update", DBNAME, "set", AVATAR, "= #{AVATAR},",EMAIL, "= #{email},", PHONE, "= #{phone},", INSTRUCTION, "= #{instruction} where", ID, "= #{id}"})
    public void update(User user);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "instruction", column = "instruction"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"select ", ALL_FILEDS, " from ", DBNAME, "where", ID, "= #{id}"})
    public User findById(@Param("id") Integer id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "instruction", column = "instruction"),
            @Result(property = "createtime", column = "createtime"),
            @Result(property = "roles", column = "id",
                    many = @Many(select = "com.clsr0901.blog.mapper.RoleMapper.findByUserId"))
    })
    @Select({"SELECT tu.* FROM t_user tu",
            "WHERE tu.username = #{username}"})
    public User findByUsername(@Param("username") String username);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "username", column = "username"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "instruction", column = "instruction"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"select ", ALL_FILEDS, " from ", DBNAME, "where", PHONE, "= #{phone}"})
    public User findByPhone(@Param("phone") String phone);
}
