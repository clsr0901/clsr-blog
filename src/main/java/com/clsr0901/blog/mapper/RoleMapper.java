package com.clsr0901.blog.mapper;

import com.clsr0901.blog.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {
    public static final String DBNAME = " t_role ";
    public static final String ID = " id ";
    public static final String NAME = " name ";

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
    })
    @Select("SELECT * FROM t_role tr LEFT JOIN t_user_role tur ON tr.id = tur.role_id WHERE tur.user_id = #{userId}")
    public List<Role> findByUserId(@Param("userId") int userId);
}
