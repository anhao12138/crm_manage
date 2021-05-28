package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.Rolepermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolepermissionMapper {

    List<Rolepermission> findByRoleIdIn(List<Integer> roleIdList);

    Integer addRolePermission(@Param("roleId")Integer roleId,@Param("list") List<Integer> list);

    Integer delByRoleId(Integer roleId);

    List<Rolepermission> findByRoleId(@Param("roleId") Integer roleId);
}