package com.w.crmsystem.service.impl;

import com.w.crmsystem.mapper.RolepermissionMapper;
import com.w.crmsystem.service.RolepermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 16:00
 * @Version 1.0
 */
@Service
public class RolepermissionServiceImpl implements RolepermissionService {

    @Resource
    private RolepermissionMapper rolepermissionMapper;

    /**
     * 添加权限中间件
     * @param roleId
     * @param perIds
     * @return
     */
    @Override
    public boolean addRolePermission(Integer roleId, List<Integer> perIds) {
        return rolepermissionMapper.addRolePermission(roleId, perIds)>0?true:false;
    }

    /**
     * 删除权限
     * @param roleId
     * @return
     */
    @Override
    public boolean delByRoleId(Integer roleId) {
        return rolepermissionMapper.delByRoleId(roleId)>0?true:false;
    }
}
