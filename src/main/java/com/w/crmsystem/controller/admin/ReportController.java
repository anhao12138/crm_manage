package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ReportExample;
import com.w.crmsystem.base.Report;
import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.service.ReportService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/17 17:05
 * @Version 1.0
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    @Resource
    private ReportService reportService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private Gson gson;
    @Resource
    private ResultInfo resultInfo;
    @Resource
    private ResultSingleInfo resultSingleInfo;


    @RequestMapping("/queryAllByInfo")
    @ResponseBody
    public String queryAllByInfo(@RequestParam(value = "startTime", required = false) String startTime,
                                 @RequestParam(value = "endTime", required = false) String endTime,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "groupType", required = false) Integer groupType,
                                 @RequestParam("page") Integer page,
                                 @RequestParam("limit") Integer limit) {
        Report report = new Report();
        report.setEndTime(endTime);
        report.setName(name);
        report.setStartTime(startTime);


        if (StringUtils.isEmpty(groupType)) {

            report.setGroupType(0);
        } else {
            report.setGroupType(groupType);
        }
        List<ReportExample> reportExamples = reportService.queryAllByKey(report, page, limit);
        resultInfo.setData(reportExamples);
        System.out.println(reportService.countAllByKey(report));
        resultInfo.setCount(reportService.countAllByKey(report));
        resultInfo.setCode(0);
        return gson.toJson(resultInfo);
    }

    /**
     * 饼状图
     *
     * @return
     */
    @RequestMapping("/cakeInfo")
    @ResponseBody
    public String cakeInfo() {

        resultSingleInfo.setData(reportService.queryOfCake());
        resultSingleInfo.setCode(200);
        return gson.toJson(resultSingleInfo);
    }

    /**
     * 条状图
     *
     * @param username
     * @return
     */
    @RequestMapping("/columnInfo")
    @ResponseBody
    public String columnInfo(@RequestParam(value = "username", required = false) String username) {
        if (StringUtils.isEmpty(username)) {
            Subject subject = SecurityUtils.getSubject();
            username = (String) subject.getPrincipal();
            System.out.println(username);
        }


        resultSingleInfo.setCode(200);
        ResultColumn resultColumn = new ResultColumn();
        resultColumn.setData(reportService.queryOfColunm(username));
        resultColumn.setRealName(employeeService.findByUserName(username).getRealname());
        resultSingleInfo.setData(resultColumn);
        return gson.toJson(resultSingleInfo);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    class ResultColumn {
        private Object data;
        private String realName;

    }
}

