package com.w.crmsystem.controller.admin;

import com.google.gson.Gson;
import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.domain.Potentialcustomer;
import com.w.crmsystem.service.DirdetialService;
import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.service.PotentialcustomerSerivce;
import com.w.crmsystem.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName: FAILCustomerController
 * @Description:
 * @Author: yun
 * @Date: 2021/5/23 19:28
 */
@Controller
@RequestMapping("/fail")
public class FAILCustomerController {
    @Resource
    private PotentialcustomerSerivce potentialcustomerSerivce;
    @Resource
    private DirdetialService dirdetialService;
    @Resource
    private Gson gson;
    @Resource
    private ResultInfo resultInfo;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private ResultSingleInfo resultSingleInfo;


    /**
     * 失败客户查询
     * @param page
     * @param limit
     * @param keyword
     * @param state
     * @return
     */
    @RequestMapping("/queryAllFail")
    @ResponseBody
    public String queryAllFail(Integer page,Integer limit, String keyword,Integer state){
        resultInfo.setCode(0);
        resultInfo.setCount(potentialcustomerSerivce.countByKey(keyword,-1));
        resultInfo.setData(potentialcustomerSerivce.queryLimitStatusByKey(page,limit,keyword,-1));
        return gson.toJson(resultInfo);
    }


    @RequestMapping("/addAddUpdateInfo")
    @ResponseBody
    public String addAndUpdateInfo(Potentialcustomer potentialcustomer){
        if (StringUtils.isEmpty(potentialcustomer.getAge())){
            new NullPointerException("客户年龄还没写");
        }
        if (StringUtils.isEmpty(potentialcustomer.getJobId())){
            new  NullPointerException("工作不能空哦");
        }
        if (StringUtils.isEmpty(potentialcustomer.getTel())){
            new NullPointerException("还有空的");
        }
        resultSingleInfo.setCode(200);
        if (StringUtils.isEmpty(potentialcustomer.getPcId())){
            //插入
            potentialcustomer.setInputtime(new Date());
            Subject subject= SecurityUtils.getSubject();
            String employee = (String) subject.getSession().getAttribute("user");
            Integer empId=employeeService.findByUserName(employee).getEmpId();
            //固定录入人
            potentialcustomer.setInputuserId(empId);
            //固定负责人
            potentialcustomer.setSellerId(empId);
            //固定状态为失败客户
            potentialcustomer.setStatus(-1);
            potentialcustomerSerivce.addPotentialInfo(potentialcustomer);
            resultSingleInfo.setMsg("添加成功");
        }else {
            //跟新
            potentialcustomerSerivce.updatePotentialInfo(potentialcustomer);
            resultSingleInfo.setMsg("更新成功"+potentialcustomer.getName()+"的信息");
        }
        return gson.toJson(resultSingleInfo);
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public  String changeState(Integer pcId,Integer state){
        if (potentialcustomerSerivce.changeState(pcId, state)){
            resultSingleInfo.setCode(200);
            resultSingleInfo.setMsg("修改成功");
            return  gson.toJson(resultSingleInfo);
        }
        resultSingleInfo.setCode(208);
        resultSingleInfo.setMsg("修改失败,联系管理员");
        return gson.toJson(resultSingleInfo);
    }
}
