package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.Emprole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmproleMapper {

    List<Emprole> findByUserId(Integer record);

    void addEmprole(@Param("list") List<Integer> list,@Param("empId")Integer empId);

    void delBatchById(@Param("empId") Integer empId);

    List<Integer> queryRoleIds(@Param("empId") Integer empId);
}