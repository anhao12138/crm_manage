package com.w.crmsystem.controller.admin;

import com.google.gson.Gson;
import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.baseException.NullInfoException;
import com.w.crmsystem.domain.Potentialcustomer;
import com.w.crmsystem.domain.PotentialcustomerExample;
import com.w.crmsystem.service.DirdetialService;
import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.service.PotentialcustomerSerivce;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName: LostCustomerController
 * @Description:
 * @Author: yun
 * @Date: 2021/5/23 19:59
 */

@RequestMapping("/lost")
public class LostCustomerController {
    @Resource
    private PotentialcustomerSerivce potentialcustomerSerivce;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private DirdetialService dirdetialService;
    @Resource
    private Gson gson;
    @Resource
    private Potentialcustomer potentialcustomer;
    @Resource
    private PotentialcustomerExample potentialcustomerExample;
    @Resource
    private ResultSingleInfo resultSingleInfo;
    @Resource
    private ResultInfo resultInfo;



    /**
     * 分页查询包括关键字和状态查询
     * @param page
     * @param limit
     * @param keyword
     * @param -2
     * @return
     */
    @RequestMapping("/queryAllLost")
    @ResponseBody
    public String queryAllByKey(Integer page,Integer limit,String keyword){
        resultInfo.setCode(0);
        resultInfo.setCount(potentialcustomerSerivce.countByKey(keyword,-2));
        resultInfo.setData(potentialcustomerSerivce.queryLimitStatusByKey(page,limit,keyword,-2));
        return gson.toJson(resultInfo);

    }

    /**
     * 修改增加客户信息
     * 传入的是对象
     * @param potentialcustomer
     * @return
     */
    @RequestMapping("/addAndUpdateInfo")
    @ResponseBody
    public String addAndUpdateInfo(Potentialcustomer potentialcustomer){
        if (StringUtils.isEmpty(potentialcustomer.getAge())){
            new NullInfoException("客户年龄不能为空的");
        }
        if (StringUtils.isEmpty(potentialcustomer.getJobId())){
            new NullInfoException("客户的年龄咋可能为空");
        }
        if (StringUtils.isEmpty(potentialcustomer.getTel())){
            new NullInfoException("客户的电话是不能为空的");
        }
        resultSingleInfo.setCode(20);
        if (StringUtils.isEmpty(potentialcustomer.getPcId())){
            //插入操作
            potentialcustomer.setInputtime(new Date());
            Subject subject= SecurityUtils.getSubject();
            String employee= (String) subject.getSession().getAttribute("user");
            Integer empId=employeeService.findByUserName(employee).getEmpId();
            potentialcustomer.setInputuserId(empId);
            potentialcustomer.setSellerId(empId);
            potentialcustomer.setStatus(-2);
            potentialcustomerSerivce.addPotentialInfo(potentialcustomer);
            resultSingleInfo.setMsg("新增成功");
        }else {
            //更新操作
            potentialcustomerSerivce.updatePotentialInfo(potentialcustomer);
            resultSingleInfo.setMsg("客户的信息更新了");
        }

        return gson.toJson(resultSingleInfo);
    }

    /**
     * 修改 开发 状态
     * @param pcId
     * @param state
     * @return
     */
    @RequestMapping("/changeState")
    @ResponseBody
    public String changeState(Integer pcId,Integer state){
        if (potentialcustomerSerivce.changeState(pcId,state)){
            resultSingleInfo.setCode(200);
            resultSingleInfo.setMsg("修改成功");
            return gson.toJson(resultSingleInfo);
        }
        resultSingleInfo.setCode(202);
        resultSingleInfo.setMsg("修改失败了呜呜呜");
        return gson.toJson(resultSingleInfo);
    }
}