package com.w.crmsystem.service.impl;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.domain.Permission;
import com.w.crmsystem.domain.Role;
import com.w.crmsystem.domain.RoleExample;
import com.w.crmsystem.domain.Rolepermission;
import com.w.crmsystem.mapper.PermissionMapper;
import com.w.crmsystem.mapper.RoleMapper;
import com.w.crmsystem.mapper.RolepermissionMapper;
import com.w.crmsystem.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 9:16
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolepermissionMapper rolepermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Role> queryAll() {
        return roleMapper.queryAll();
    }

    /**
     * 分页查询角色
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<RoleExample> queryAllLimitRole(Integer page, Integer limit) {
        List<Role> roles = roleMapper.queryAllLimitRole((page - 1) * limit, limit);

        List<RoleExample> roleExamples=new ArrayList<>();
        for (Role r:roles){
            List<Permission> permissions=new ArrayList<>();
            List<Rolepermission> rolepermissions=rolepermissionMapper.findByRoleId(r.getRoleId());
            System.out.println("总数===============>"+rolepermissions.size());
            for (Rolepermission rp:rolepermissions){
                Permission permission = permissionMapper.findById(rp.getPerId());
                permissions.add(permission);
            }
            RoleExample roleExample=new RoleExample();
            roleExample.setName(r.getName());
            ResultInfo<Permission> resultInfo=new ResultInfo<>();

            resultInfo.setData(permissions);
            resultInfo.setCode(0);
            resultInfo.setMsg("成功");
            resultInfo.setCount(rolepermissions.size());

            roleExample.setPermissions(resultInfo);
            roleExample.setSn(r.getSn());
            roleExample.setRoleId(r.getRoleId());
            roleExamples.add(roleExample);
        }
        return roleExamples;
    }

    /**
     * 角色总数
     * @return
     */
    @Override
    public Integer countAll() {
        return roleMapper.countAll();
    }

    /**
     * 删除角色单个
     * @param roleId
     * @return
     */
    @Override
    public boolean delRoleById(Integer roleId) {
        return roleMapper.delRoleById(roleId)>0?true:false;
    }

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    @Override
    public Integer addRoleInfo(Role role) {
        return roleMapper.addRoleInfo(role);
    }

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    @Override
    public Boolean updateRoleInfo(Role role) {
        return roleMapper.updateRoleInfo(role)>0?true:false;
    }
}
