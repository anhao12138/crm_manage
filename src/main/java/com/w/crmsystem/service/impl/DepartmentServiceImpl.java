package com.w.crmsystem.service.impl;

import com.w.crmsystem.domain.Department;
import com.w.crmsystem.mapper.DepartmentMapper;
import com.w.crmsystem.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/6 23:04
 * @Version 1.0
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    /**
     * 查询总数
     * @return
     */
    @Override
    public Integer countAll() {
        return departmentMapper.countAll();
    }

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Department> queryByList(Integer page, Integer limit) {
        return departmentMapper.queryByLimit((page-1)*limit, limit);
    }

    /**
     * 更具ID删除单条数据
     * @param id
     * @return
     */
    @Override
    public boolean delById(Integer id) {
        return departmentMapper.delById(id)>0?true:false;
    }

    /**
     * 添加部门信息
     * @param department
     * @return
     */
    @Override
    public boolean insertDep(Department department) {

        return departmentMapper.insertDep(department)>0?true:false;
    }

    /**
     * 更新部门信息
     * @param department
     * @return
     */
    @Override
    public boolean updateDep(Department department) {
        return departmentMapper.updateDep(department)>0?true:false;
    }

    /**
     * 查询所有部门信息
     * @return
     */
    @Override
    public List<Department> queryAllInfoDep() {
        return departmentMapper.queryAllInfoDep();
    }
}
