package com.w.crmsystem.service;

import com.w.crmsystem.domain.Role;
import com.w.crmsystem.domain.RoleExample;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/6 22:58
 * @Version 1.0
 */
public interface RoleService {
    List<Role> queryAll();

    List<RoleExample> queryAllLimitRole(Integer page, Integer limit);

    Integer countAll();

    boolean delRoleById(Integer roleId);

    Integer addRoleInfo(Role role);

    Boolean updateRoleInfo(Role role);
}
