package com.w.crmsystem.service;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 15:58
 * @Version 1.0
 */
public interface RolepermissionService {

    boolean addRolePermission(Integer roleId, List<Integer> perIds);

    boolean delByRoleId(Integer roleId);
}
