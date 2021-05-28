package com.w.crmsystem.service.impl;

import com.w.crmsystem.base.CakeInfo;
import com.w.crmsystem.base.ReportExample;
import com.w.crmsystem.base.Report;
import com.w.crmsystem.mapper.ReportMapper;
import com.w.crmsystem.service.ReportService;
import com.w.crmsystem.util.DateChange;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/17 17:06
 * @Version 1.0
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportMapper reportMapper;
    @Resource
    private DateChange dateChange;


    @Override
    public List<ReportExample> queryAllByKey(Report report, Integer page, Integer limit) {
        System.out.println(report.getTypeInfoStr());
        report.setOffset((page-1)*limit);
        report.setLimit(limit);
        return reportMapper.queryAllByKey(report);
    }

    @Override
    public Integer countAllByKey(Report report) {
        return reportMapper.countAllByKey(report);
    }

    @Override
    public List<CakeInfo> queryOfCake() {

        List<CakeInfo> cakeInfos = reportMapper.queryOfCake(dateChange.getEndDate(), changeMonthAddOne(dateChange.getNowDate()));
        return getProcessing(cakeInfos);
    }

    @Override
    public List<CakeInfo> queryOfColunm(String username) {
        System.out.println(dateChange.getEndDate());
        List<CakeInfo> list = reportMapper.queryOfColunm(dateChange.getEndDate(), changeMonthAddOne(dateChange.getNowDate()), username);
        return getProcessing(list);
    }

    //数据处理

    public List<CakeInfo> getProcessing(List<CakeInfo> cakeInfos){

        Map<String, Integer> initDate = dateChange.getInitDate();
        Set<Map.Entry<String, Integer>> entries = initDate.entrySet();
        List<CakeInfo> list=new LinkedList<>();

        for (Map.Entry<String, Integer> map:entries) {
            CakeInfo cake=new CakeInfo();
            cake.setValue(map.getValue());
            cake.setName(map.getKey());
            list.add(cake);
        }
        //接下来就是list和cakeinfos的比较
        for (int i=0;i<12;i++){
            for (int j=0;j<cakeInfos.size();j++){
                if (list.get(i).getName().equals(cakeInfos.get(j).getName())){
                    list.get(i).setValue(cakeInfos.get(j).getValue());
                    j=cakeInfos.size();
                }
            }
        }

        return list;
    }
    public String changeMonthAddOne(String data){
        String[] split = data.split("-");
        Integer mon = Integer.valueOf(split[1]);
        mon+=1;
        return split[0]+"-"+dateChange.addinfoMonth(mon);
    }
}
