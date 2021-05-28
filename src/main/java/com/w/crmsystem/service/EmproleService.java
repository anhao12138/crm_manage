package com.w.crmsystem.service;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 13:59
 * @Version 1.0
 */
public interface EmproleService {
    void addEmprole(List<Integer> list,Integer empId);
    void delBatchById(Integer empIds);
    List<Integer> queryRoleIds(Integer empId);
}
