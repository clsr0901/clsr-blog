package com.clsr0901.blog.mapper;

import com.clsr0901.blog.entity.Source;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SourceMapper {
    public static final String DBNAME = " t_source ";
    public static final String ID = " id ";
    public static final String USERID = " userId ";
    public static final String FILENAME = " name ";
    public static final String MD5 = " uid ";
    public static final String PATH = " url ";
    public static final String CREATETIME = " createtime ";
    public static final String LENGTH = " length ";
    public static final String MIME = " mime ";
    public static final String TYPE = " type ";
    public static final String DB_FIELDS = " ( " + USERID + "," + FILENAME + "," + MD5 + "," + PATH + "," + LENGTH + "," + MIME + "," + TYPE +  " ) ";
    public static final String ALL_FILEDS = ID + "," + USERID + "," + FILENAME + "," + MD5 + "," + PATH + "," + CREATETIME  + "," + LENGTH + ","
            + MIME + "," + TYPE;

    @Insert({"insert into", DBNAME, DB_FIELDS,  " values (#{userId}, #{name}, #{uid}, #{url}, #{length}, #{mime}, #{type})"})
    @Options(useGeneratedKeys=true,keyProperty="id")
    public void insert(Source source);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "length", column = "length"),
            @Result(property = "mime", column = "mime"),
            @Result(property = "type", column = "type"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"select", ALL_FILEDS, "from", DBNAME, "where", ID, "= #{id}"})
    public Source findById(@Param("id") int id);
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "length", column = "length"),
            @Result(property = "mime", column = "mime"),
            @Result(property = "type", column = "type"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"select", ALL_FILEDS, "from", DBNAME, "where", USERID, "= #{userId}"})
    public List<Source> findByUserId(@Param("userId") int userId);

    @Delete({"delete from", DBNAME, "where", MD5, "= #{md5}"})
    public void deleteByMd5(@Param("md5") String md5);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "userId"),
            @Result(property = "name", column = "name"),
            @Result(property = "url", column = "url"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "length", column = "length"),
            @Result(property = "mime", column = "mime"),
            @Result(property = "type", column = "type"),
            @Result(property = "createtime", column = "createtime"),
    })
    @Select({"select", ALL_FILEDS, "from", DBNAME, "where", MD5, "= #{md5}"})
    public Source findByMd5(@Param("md5") String md5);

}
