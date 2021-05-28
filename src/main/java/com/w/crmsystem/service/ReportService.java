package com.w.crmsystem.service;

import com.w.crmsystem.base.CakeInfo;
import com.w.crmsystem.base.ReportExample;
import com.w.crmsystem.base.Report;

import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/17 17:06
 * @Version 1.0
 */
public interface ReportService {
    List<ReportExample> queryAllByKey(Report report, Integer page, Integer limit);
    Integer countAllByKey(Report report);
    List<CakeInfo> queryOfCake();
    List<CakeInfo> queryOfColunm(String username);
}
