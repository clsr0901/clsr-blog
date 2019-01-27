package com.clsr0901.blog.mapper;

import com.clsr0901.blog.entity.Comment;
import com.clsr0901.blog.vo.CommentVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    public static final String DBNAME = " t_comment ";
    public static final String ID = " id ";
    public static final String BLOGID = " blogId ";
    public static final String DESTUSERID = " destUserId ";
    public static final String SOURCEUSERID = " sourceUserId ";
    public static final String CONTENT = " content ";
    public static final String ACTION = " action ";
    public static final String CREATETIME = " createTime ";
    public static final String DB_FIELDS = " ( " + BLOGID  + "," + DESTUSERID + "," + SOURCEUSERID + "," + CONTENT  +  "," + ACTION + " ) ";
    public static final String ALL_FILEDS = ID + "," + BLOGID + "," + DESTUSERID + "," + SOURCEUSERID + "," + CONTENT + ","+ ACTION  + "," + CREATETIME ;

    @Insert({"insert into", DBNAME, DB_FIELDS,  " values (#{blogId}, #{destUserId}, #{sourceUserId}, #{content}, #{action})"})
    @Options(useGeneratedKeys=true,keyProperty="id")
    public void insert(Comment comment);

    @Delete({"delete from", DBNAME, "where", ID, "= #{id}"})
    public void deleteById(@Param("id") int id);

    @Select("SELECT COUNT(tc.id) FROM t_comment tc WHERE tc.blogId = #{blogId}")
    public int countByBlogId(@Param("blogId") int blogId);

    @Select({"SELECT COUNT(tc.id)  FROM t_comment tc",
            "LEFT JOIN t_blog tb ON tc.blogId = tb.id",
            "LEFT JOIN t_user tu ON tu.id = tb.userId WHERE tu.id = #{userId}"})
    public int countByUserId(@Param("userId") int userId);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "blogId", column = "blogId"),
            @Result(property = "destUserId", column = "destUserId"),
            @Result(property = "destUserName", column = "destUserName"),
            @Result(property = "sourceUserId", column = "sourceUserId"),
            @Result(property = "sourceUserName", column = "sourceUserName"),
            @Result(property = "action", column = "action"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"SELECT * from", DBNAME, "where", ID, "= #{id}"})
    public CommentVO findById(@Param("id") int id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "content", column = "content"),
            @Result(property = "blogId", column = "blogId"),
            @Result(property = "destUserId", column = "destUserId"),
            @Result(property = "destUserName", column = "destUserName"),
            @Result(property = "sourceUserId", column = "sourceUserId"),
            @Result(property = "sourceUserName", column = "sourceUserName"),
            @Result(property = "action", column = "action"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"SELECT tc.*, tu.username AS sourceUserName, tu1.username AS destUserName FROM t_comment tc ",
            "LEFT JOIN t_user tu ON tc.sourceUserId = tu.id",
            "LEFT JOIN t_user tu1 ON tc.destUserId = tu1.id WHERE tc.blogId = #{blogId} order by", CREATETIME, "desc"})
    public List<CommentVO> findAllByBlogId(@Param("blogId") int blogId);
}
