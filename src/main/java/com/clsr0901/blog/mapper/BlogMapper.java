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
    public static final String SUMMARY = " summary ";
    public static final String HIT = " hit ";
    public static final String VIEW = " view ";
    public static final String STICKY = " sticky ";
    public static final String HIGHLIGHT = " highlight ";
    public static final String CREATETIME = " createtime ";
    public static final String UPDATETIME = " updatetime ";
    public static final String DB_FIELDS = " ( " + USERID + "," + CONTENT + "," + TITLE + "," + SUMMARY + " ) ";
    public static final String ALL_FILEDS = ID + "," + USERID + "," + CONTENT + "," + TITLE + "," + SUMMARY + "," + HIT + "," + VIEW + "," + STICKY + "," + HIGHLIGHT + ","
            + CREATETIME + "," + UPDATETIME;

    @Insert({"insert into", DBNAME, DB_FIELDS,  " values (#{userId}, #{content}, #{title}, #{summary})"})
    @Options(useGeneratedKeys=true,keyProperty="id")
    public void insert(Blog blog);

    @Delete({"delete from ", DBNAME, "where", ID, "= #{id}"})
    public void delete(@Param("id") int id);

    @Update({"update", DBNAME, "set", CONTENT, "= #{content},",TITLE, "= #{title},",SUMMARY, "= #{summary},", HIT, "= #{hit},", VIEW, "= #{view},",
            STICKY, "= #{sticky},",HIGHLIGHT, "= #{highlight} where", ID, "= #{id}"})
    public void update(Blog blog);

    @Update({"update", DBNAME, "set", CONTENT, "= #{content},",TITLE, "= #{title},",SUMMARY, "= #{summary} where", ID, "= #{id}"})
    public void updateContent(Blog blog);

    @Update({"update", DBNAME, "set", VIEW , "= #{view} where", ID, "= #{id}"})
    public void updateView(Blog blog);

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
    @Select({"select ", ALL_FILEDS, "from", DBNAME, " order by ", CREATETIME, " DESC"})
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
    @Select({"SELECT tb.* FROM t_blog tb LEFT JOIN t_user tu ON tb.userId = tu.id WHERE",
            "tb.title LIKE binary  #{keycode} OR tb.content LIKE binary  #{keycode} OR tb.summary LIKE binary  #{keycode}",
            " OR tb.createtime LIKE binary  #{keycode} OR tb.updatetime LIKE binary  #{keycode} OR tu.email LIKE binary  #{keycode}",
            " OR tu.username LIKE binary  #{keycode} OR tu.phone LIKE binary  #{keycode} OR tu.instruction LIKE binary  #{keycode}",
            " order by createtime DESC"})
    public List<Blog> findAllByKeycode(@Param("keycode") String keycode);

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

    @Select({"SELECT COUNT(tb.id) FROM t_blog tb WHERE tb.userId = #{userId}"})
    public int countByUserId(@Param("userId") int userId);
}
