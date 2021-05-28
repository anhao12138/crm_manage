package com.w.crmsystem.mapper;

import com.w.crmsystem.domain.Employee;
import com.w.crmsystem.domain.EmployeeExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmployeeMapper {

    //自定义生成

    List<Employee> findByUserName(@Param("username") String username);

    List<Employee> queryListEmp(@Param("offset") Integer offset,@Param("limit") Integer limit);

    /**
     * 获取员工总个数
     * @return
     */
    @Select("select count(*) from employee")
    Integer countAll();


    /**
     * 批量添加
     * @param list
     * @return
     */
    int addBatchEmp(List<EmployeeExample> list);

    /**
     * 批量删除
     * @param empIds
     * @return
     */
    int delBatchEmp(List<Integer> empIds);

    /**
     * 单个员工信息添加
     * @param employee
     * @return
     */
    Integer addSingleEmp(Employee employee);

    /**
     * 员工信息更新
     * @param employee
     * @return
     */
    Integer updateEmp(Employee employee);

    /**
     * 重置密码
     * @param id
     * @param newPass
     * @return
     */
    Integer resetPaswd(@Param("empId") Integer id,@Param("newPass") String newPass);

    /**
     * 条件查询分页
     * @param offset
     * @param limit
     * @param keyword
     * @param deptId
     * @return
     */
    List<EmployeeExample> queryByConditions(@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("keyword") String keyword,@Param("dept_id") Integer deptId);

    /**
     * 条件查询总数
     * @param keyword
     * @param deptId
     * @return
     */
    Integer countByConditions(@Param("keyword") String keyword,@Param("dept_id") Integer deptId);

    /**
     * 员工离职修改
     * @param empId
     * @return
     */
    Integer changeDeparture(@Param("empId") Integer empId);

    /**
     * 根据员工ID查找员工信息
     * @param empId
     * @return
     */
    String queryById(Integer empId);

    /**
     * 查询所有员工信息
     * @return
     */
    List<Employee> queryAllEmpInfo();
}