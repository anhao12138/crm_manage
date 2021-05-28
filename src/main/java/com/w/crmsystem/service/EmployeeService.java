package com.w.crmsystem.service;

import com.w.crmsystem.domain.Employee;
import com.w.crmsystem.domain.EmployeeExample;
import com.w.crmsystem.domain.Permission;

import java.util.List;
import java.util.Set;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/1 13:08
 * @Version 1.0
 */
public interface EmployeeService {
    Employee findByUserName(String username);

    Set<String> findPermissions(String username);

    Set<String> findRoles(String username);
    //以上是权限认定

    /*
    ======================================页面显示路径查询=========================================================
     */

    List<Permission> findByUserNameForMenu(String username);


    List<Employee> queryListEmp(Integer page, Integer limit);


    Integer countAll();

    int addBatch(List<EmployeeExample> list);

    Integer delBatchEmp(List<Integer> empIds);

    boolean addSingleEmp(Employee employee);

    boolean updateEmp(Employee employee);

    boolean resetPaswd(Integer id);

    List<EmployeeExample> queryByConditions(Integer page, Integer limit, String keyword, Integer deptId);

    Integer countByConditions(String keyword, Integer dept);

    boolean changeDeparture(Integer empId);

    String queryById(Integer inputuserId);

    List<Employee> queryAllEmpInfo();
}
