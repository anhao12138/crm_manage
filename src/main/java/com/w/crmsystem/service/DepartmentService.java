package com.w.crmsystem.service;

import com.w.crmsystem.domain.Department;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/6 22:58
 * @Version 1.0
 */
public interface DepartmentService {
    Integer countAll();
    List<Department> queryByList(Integer page, Integer limit);
    boolean delById(Integer id);
    boolean insertDep(Department department);
    boolean updateDep(Department department);
    List<Department> queryAllInfoDep();
}
