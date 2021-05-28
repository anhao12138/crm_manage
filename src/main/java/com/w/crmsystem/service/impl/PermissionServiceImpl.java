package com.w.crmsystem.service.impl;

import com.w.crmsystem.domain.Permission;
import com.w.crmsystem.mapper.PermissionMapper;
import com.w.crmsystem.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 9:16
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 查询所有权限信息
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<Permission> queryList(Integer page, Integer limit) {
        return permissionMapper.queryList((page-1)*limit,limit);
    }

    /**
     * 权限个数
     * @return
     */
    @Override
    public Integer countPer() {
        return permissionMapper.countPer();
    }

    /**
     * 修改权限状态
     * @param perId
     * @return
     */
    @Override
    public boolean changeLock(Integer perId,Integer statu) {
        return permissionMapper.changeLock(perId,statu)>0?true:false;
    }

    /**
     * 查找所有权限
     * @return
     */
    @Override
    public List<Permission> queryAll() {
        return permissionMapper.queryAll();
    }
}
