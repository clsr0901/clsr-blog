package com.clsr0901.blog.mapper;

import com.clsr0901.blog.entity.Message;
import com.clsr0901.blog.vo.MessageVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    public static final String DBNAME = " t_message ";
    public static final String ID = " id ";
    public static final String DESTUSERID = " destUserId ";
    public static final String SOURCEUSERID = " sourceUserId ";
    public static final String MESSAGE = " message ";
    public static final String CREATETIME = " createTime ";
    public static final String DB_FIELDS = " ( " + DESTUSERID + "," + SOURCEUSERID + "," + MESSAGE  + " ) ";
    public static final String ALL_FILEDS = ID + "," + DESTUSERID + "," + SOURCEUSERID + "," + MESSAGE + "," + CREATETIME ;

    @Insert({"insert into", DBNAME, DB_FIELDS,  " values (#{destUserId}, #{sourceUserId}, #{message})"})
    @Options(useGeneratedKeys=true,keyProperty="id")
    public void insert(Message message);

    @Delete({"delete from", DBNAME, "where", ID, "= #{id}"})
    public void deleteById(@Param("id") int id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "message", column = "message"),
            @Result(property = "destUserId", column = "destUserId"),
            @Result(property = "sourceUserId", column = "sourceUserId"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"SELECT", ALL_FILEDS, "from", DBNAME, "where", ID, "= #{id}"})
    public Message findOneById(@Param("id") int id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "message", column = "message"),
            @Result(property = "destUserName", column = "destUserName"),
            @Result(property = "sourceUserName", column = "sourceUserName"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"SELECT distinct tm.id, tm.message, tm.createTime, tu.username AS sourceUserName, tu1.username AS destUserName FROM t_message tm",
            "LEFT JOIN t_user tu ON tm.sourceUserId = tu.id",
            "LEFT JOIN t_user tu1 ON tm.destUserId = tu1.id WHERE tm.destUserId = #{destUserId} order by", CREATETIME, "desc"})
    public List<MessageVO> findAllByDestUserId(@Param("destUserId") int destUserId);

}
