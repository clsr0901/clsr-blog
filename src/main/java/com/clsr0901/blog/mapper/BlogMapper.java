package com.clsr0901.blog.mapper;

import com.clsr0901.blog.entity.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    public static final String DBNAME = " t_blog ";
    public static final String ID = " id ";
    public static final String USERID = " userId ";
    public static final String CONTENT = " content ";
    public static final String TITLE = " title ";
    public static final String HIT = " hit ";
    public static final String VIEW = " view ";
    public static final String STICKY = " sticky ";
    public static final String HIGHLIGHT = " highlight ";
    public static final String CREATETIME = " createtime ";
    public static final String UPDATETIME = " updatetime ";
    public static final String DB_FIELDS = " ( " + USERID + "," + CONTENT + "," + TITLE + " ) ";
    public static final String ALL_FILEDS = ID + "," + USERID + "," + CONTENT + "," + TITLE + ","  + HIT + "," + VIEW + "," + STICKY + "," + HIGHLIGHT + ","
            + CREATETIME + "," + UPDATETIME;

    @Insert({"insert into", DBNAME, DB_FIELDS,  " values (#{userId}, #{content}, #{title})"})
    @Options(useGeneratedKeys=true,keyProperty="id")
    public void insert(Blog blog);

    @Update({"update", DBNAME, "set", CONTENT, "= #{conent},",TITLE, "= #{title},", HIT, "= #{hit},", VIEW, "= #{view},",
            STICKY, "= #{sticky},",HIGHLIGHT, "= #{highlight} where", ID, "= #{id}"})
    public void update(Blog blog);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "content", column = "content"),
            @Result(property = "title", column = "title"),
            @Result(property = "hit", column = "hit"),
            @Result(property = "view", column = "view"),
            @Result(property = "sticky", column = "sticky"),
            @Result(property = "highlight", column = "highlight"),
            @Result(property = "createtime", column = "createtime"),
            @Result(property = "updatetime", column = "updatetime"),
    })
    @Select({"select ", ALL_FILEDS, "from", DBNAME})
    public List<Blog> findAll();

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "content", column = "content"),
            @Result(property = "title", column = "title"),
            @Result(property = "hit", column = "hit"),
            @Result(property = "view", column = "view"),
            @Result(property = "sticky", column = "sticky"),
            @Result(property = "highlight", column = "highlight"),
            @Result(property = "createtime", column = "createtime"),
            @Result(property = "updatetime", column = "updatetime"),
    })
    @Select({"select ", ALL_FILEDS, "from", DBNAME, "where", ID, "= #{id}"})
    public Blog findById(@Param("id") int id);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "content", column = "content"),
            @Result(property = "title", column = "title"),
            @Result(property = "hit", column = "hit"),
            @Result(property = "view", column = "view"),
            @Result(property = "sticky", column = "sticky"),
            @Result(property = "highlight", column = "highlight"),
            @Result(property = "createtime", column = "createtime"),
            @Result(property = "updatetime", column = "updatetime"),
    })
    @Select({"select ", ALL_FILEDS, "from", DBNAME, "where", USERID, "= #{userId}"})
    public List<Blog> findByUserId(@Param("userId") int userId);
}
