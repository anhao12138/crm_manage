package com.w.crmsystem.service.impl;

import com.w.crmsystem.mapper.EmproleMapper;
import com.w.crmsystem.service.EmproleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/12 14:02
 * @Version 1.0
 */
@Service
public class EmproleServiceImpl implements EmproleService {

    @Resource
    private EmproleMapper emproleMapper;

    @Override
    public void addEmprole(List<Integer> list,Integer empId) {
        emproleMapper.addEmprole(list,empId);
    }

    @Override
    public void delBatchById(Integer empIds) {
        emproleMapper.delBatchById(empIds);
    }

    @Override
    public List<Integer> queryRoleIds(Integer empId) {
        return emproleMapper.queryRoleIds(empId);
    }
}
