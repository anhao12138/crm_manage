package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

    //自定义生成

    Permission findById(Integer permissionId);

    List<Permission> queryList(@Param("offset")Integer offset,@Param("limit")Integer limit);

    @Select("select count(*) from permission")
    Integer countPer();

    Integer changeLock(@Param("perId") Integer perId, @Param("statu") Integer statu);

    List<Permission> queryAll();
}