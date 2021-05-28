package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.domain.FollowHistory;
import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.service.FollowHistoryService;
import com.w.crmsystem.service.PotentialcustomerSerivce;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *   @ClassName:  
 * @Description:
 * @Author: yun
 * @Date 2021/5/15 1:14
 * @Version 1.0
 */

/**
 * 跟进历史
 */
@Controller
@RequestMapping("/follow")
public class FollowHistoryController {

    @Resource
    private FollowHistoryService followHistoryService;
    @Resource
    private PotentialcustomerSerivce potentialcustomerSerivce;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private FollowHistory followHistory;
    @Resource
    private Gson gson;
    @Resource
    private ResultSingleInfo resultSingleInfo;
    @Resource
    private ResultInfo resultInfo;


    /**
     * 客户信息的跟进
     * @param traceTime
     * @param traceDetails
     * @param traceType
     * @param traceResult
     * @param customerId
     * @param type
     * @return
     */
    @RequestMapping("/addFollowHisInfo")
    @ResponseBody
    public String addFollowHisInfo(String traceTime,String traceDetails,String traceType,Integer traceResult,Integer customerId,Integer type){

        Subject subject= SecurityUtils.getSubject();
        followHistory.setInputUser(employeeService.findByUserName((String)subject.getPrincipal()).getRealname());
        followHistory.setCustomerId(customerId);
        followHistory.setTraceDetails(traceDetails);
        followHistory.setTraceResult(traceResult);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            followHistory.setTraceTime(format.parse(traceTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        followHistory.setTraceType(traceType);
        followHistory.setType(type);
        followHistoryService.addFollowHisInfo(followHistory);
        resultSingleInfo.setCode(200);
        resultSingleInfo.setMsg("已完成客户："+potentialcustomerSerivce.queryById(followHistory.getCustomerId())+"的跟进信息录入，请再接再厉！");
        return gson.toJson(resultSingleInfo);
    }


    /**
     * 普通分页和关键字分页查询
     * @param page
     * @param limit
     * @param keyword
     * @param startTime
     * @param endTime
     * @param resultId
     * @return
     */
    @RequestMapping("/queryAllByKey")
    @ResponseBody
    public String queryAllByKey(Integer page,Integer limit,String keyword,String startTime,String endTime,Integer resultId){
        resultInfo.setCode(0);
        if (StringUtils.isEmpty(keyword)&&StringUtils.isEmpty(startTime)&& StringUtils.isEmpty(endTime)&&StringUtils.isEmpty(resultId)){
            resultInfo.setData(followHistoryService.queryAll(page,limit));
            resultInfo.setCount(followHistoryService.countAll());
        }else {
            resultInfo.setCount(followHistoryService.countByKey(keyword,startTime,endTime,resultId));
            resultInfo.setData(followHistoryService.queryByKey(page,limit,keyword,startTime,endTime,resultId));
        }
        return gson.toJson(resultInfo);
    }
}
