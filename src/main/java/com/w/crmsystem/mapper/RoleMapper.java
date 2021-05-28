package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {


    Role findById(Integer roleId);

    List<Role> queryAll();

    @Select("select count(*) from role")
    Integer countAll();

    List<Role> queryAllLimitRole(@Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer delRoleById(@Param("roleId") Integer roleId);

    Integer addRoleInfo(Role role);

    Integer updateRoleInfo(Role role);
}