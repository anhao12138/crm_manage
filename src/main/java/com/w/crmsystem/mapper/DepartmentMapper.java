package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.Department;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {
    @Select("select count(*) from department")
    Integer countAll();


    List<Department> queryAllInfoDep();

    List<Department> queryByLimit(@Param("offset") Integer offset,@Param("limit") Integer limit);
    int insertDep(Department department);
    int delById(@Param("deptId")Integer deptId);
    int updateDep(Department department);
}