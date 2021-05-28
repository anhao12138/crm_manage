package com.w.crmsystem.service.impl;

import com.w.crmsystem.domain.HandOverHistory;
import com.w.crmsystem.domain.HandOverHistoryExample;
import com.w.crmsystem.mapper.HandOverHistoryMapper;
import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.service.HandOverHistoryService;
import com.w.crmsystem.service.PotentialcustomerSerivce;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/15 1:13
 * @Version 1.0
 */
@Service
public class HandOverHistoryServiceImpl implements HandOverHistoryService {

    @Resource
    private HandOverHistoryMapper handOverHistoryMapper;
    @Resource
    private PotentialcustomerSerivce potentialcustomerSerivce;
    @Resource
    private EmployeeService employeeService;

    @Override
    public boolean addHandOverhis(HandOverHistory handOverHistory) {
        return handOverHistoryMapper.addHandOverhis(handOverHistory)>0?true:false;
    }

    @Override
    public List<HandOverHistoryExample> queryAll(Integer page, Integer limit) {

        List<HandOverHistoryExample> handOverHistoryExamples = handOverHistoryMapper.queryAll((page - 1) * limit, limit);

        return getEmployeeInfoForHand(handOverHistoryExamples);
    }

    @Override
    public List<HandOverHistoryExample> queryByKey(Integer page, Integer limit, String keyword, String startTime, String endTime) {
        List<HandOverHistoryExample> handOverHistoryExamples = handOverHistoryMapper.queryByKey((page - 1) * limit, limit, keyword, startTime, endTime);
        return getEmployeeInfoForHand(handOverHistoryExamples);
    }

    @Override
    public Integer countAll() {
        return handOverHistoryMapper.countAll();
    }

    @Override
    public Integer countByKey(String keyword, String startTime, String endTime) {
        return handOverHistoryMapper.countByKey(keyword,startTime,endTime);
    }
    public List<HandOverHistoryExample> getEmployeeInfoForHand(List<HandOverHistoryExample> handOverHistoryExamples){
        List<HandOverHistoryExample> list=new ArrayList<>();

        for (HandOverHistoryExample h:handOverHistoryExamples){
            h.setNewSeller(employeeService.queryById(h.getNewSellerId()));
            h.setOldSeller(employeeService.queryById(h.getOldSellerId()));
            list.add(h);
        }

        return list;
    }

}
