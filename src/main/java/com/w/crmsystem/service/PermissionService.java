package com.w.crmsystem.service;

import com.w.crmsystem.domain.Permission;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/6 22:58
 * @Version 1.0
 */
public interface PermissionService {
    List<Permission> queryList(Integer page, Integer limit);

    Integer countPer();

    boolean changeLock(Integer perId,Integer statu);

    List<Permission> queryAll();
}
