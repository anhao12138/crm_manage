package com.w.crmsystem.service.impl;


import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.util.PasswordUtil;
import com.w.crmsystem.domain.*;
import com.w.crmsystem.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/1 13:08
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private EmproleMapper emproleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolepermissionMapper rolepermissionMapper;


    /**
     *  更具用户名查找用户信息
     * @param username
     * @return
     */
    @Override
    public Employee findByUserName(String username) {
        List<Employee> byUserName = employeeMapper.findByUserName(username);
        System.out.println(byUserName);
        if (byUserName.size()<0){
            return null;
        }
        return byUserName.get(0);
    }

    /**
     *  更具用户名查找角色信息
     * @param username
     * @return
     */
    @Override
    public Set<String> findRoles(String username) {
        Set<String> roleNameSet = new HashSet<>();
        Integer empId = findByUserName(username).getEmpId();
        List<Emprole> usersRoleList = emproleMapper.findByUserId(empId);
        for(int i = 0; i < usersRoleList.size(); i++){
            Role role = roleMapper.findById(usersRoleList.get(i).getRoleId());
            roleNameSet.add(role.getName());
        }
        return roleNameSet;
    }

    /**
     * 更具用户查询锁具有的权限信息
     * @param username
     * @return
     */
    @Override
    public List<Permission> findByUserNameForMenu(String username) {
        List<Permission> permissions=new ArrayList<>();
        Integer empId = findByUserName(username).getEmpId();
        List<Integer> roleIdList = new ArrayList<>();
        List<Emprole> usersRoleList = emproleMapper.findByUserId(empId);
        for(int i = 0; i < usersRoleList.size(); i++){
            Integer roleId = roleMapper.findById(usersRoleList.get(i).getRoleId()).getRoleId();
            roleIdList.add(roleId);
        }
        List<Rolepermission> rolePermissionList = rolepermissionMapper.findByRoleIdIn(roleIdList);
        for(int i = 0;i<rolePermissionList.size();i++){
            Permission permission = permissionMapper.findById(rolePermissionList.get(i).getPerId());
            permissions.add(permission);
        }
        return permissions;
    }


    /**
     *  更具用户名查找权限
     * @param username
     * @return
     */
    @Override
    public Set<String> findPermissions(String username) {
        Set<String> permissionNameSet = new HashSet<>();
        Integer empId = findByUserName(username).getEmpId();
        List<Integer> roleIdList = new ArrayList<>();
        List<Emprole> usersRoleList = emproleMapper.findByUserId(empId);
        for(int i = 0; i < usersRoleList.size(); i++){
            Integer roleId = roleMapper.findById(usersRoleList.get(i).getRoleId()).getRoleId();
            roleIdList.add(roleId);
        }
        List<Rolepermission> rolePermissionList = rolepermissionMapper.findByRoleIdIn(roleIdList);
        for(int i = 0;i<rolePermissionList.size();i++){
            Permission permission = permissionMapper.findById(rolePermissionList.get(i).getPerId());
            permissionNameSet.add(permission.getResource());
        }
        return permissionNameSet;
    }


    /**
     * 分页查询非条件分页查询
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Employee> queryListEmp(Integer page, Integer limit) {
        return employeeMapper.queryListEmp((page-1)*limit,limit);
    }

    /**
     * 查询总条数
     * @return
     */
    @Override
    public Integer countAll() {
        return employeeMapper.countAll();
    }

    /**
     * 批量添加 可以单个添加（集合中只有一个元素就是单个添加）
     * @param list
     * @return
     */
    @Override
    public int addBatch(List<EmployeeExample> list) {
        return employeeMapper.addBatchEmp(list);
    }

    /**
     * 批量删除 可以单个删除（集合中只有单个元素就是单个删除）
     * @param empIds
     * @return
     */
    @Override
    public Integer delBatchEmp(List<Integer> empIds) {
        return employeeMapper.delBatchEmp(empIds);
    }

    /**
     * 单条信息的增加
     * @param employee
     * @return
     */
    @Override
    public boolean addSingleEmp(Employee employee) {
        return employeeMapper.addSingleEmp(employee)>0?true:false;
    }

    /**
     * 员工信息更新
     * @param employee
     * @return
     */
    @Override
    public boolean updateEmp(Employee employee) {
        return employeeMapper.updateEmp(employee)>0?true:false;
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @Override
    public boolean resetPaswd(Integer id) {
        String newPass= PasswordUtil.encodePwd("123456");
        return employeeMapper.resetPaswd(id,newPass)>0?true:false;
    }

    /**
     * 条件分页查询
     * @param page
     * @param limit
     * @param keyword
     * @param deptId
     * @return
     */
    @Override
    public List<EmployeeExample> queryByConditions(Integer page, Integer limit, String keyword, Integer deptId) {
        return employeeMapper.queryByConditions((page-1)*limit,limit,keyword,deptId);
    }

    /**
     * 条件查询总数
     * @param keyword
     * @param deptId
     * @return
     */
    @Override
    public Integer countByConditions(String keyword, Integer deptId) {
        return employeeMapper.countByConditions(keyword,deptId);
    }

    /**
     *
     * @param empId
     * @return
     */
    @Override
    public boolean changeDeparture(Integer empId) {
        return employeeMapper.changeDeparture(empId)>0?true:false;
    }

    /**
     *
     * @param empId
     * @return
     */
    @Override
    public String queryById(Integer empId) {
        return employeeMapper.queryById(empId);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Employee> queryAllEmpInfo() {
        return employeeMapper.queryAllEmpInfo();
    }


}
