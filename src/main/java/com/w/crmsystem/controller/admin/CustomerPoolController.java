package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.domain.Employee;
import com.w.crmsystem.domain.HandOverHistory;
import com.w.crmsystem.domain.Potentialcustomer;
import com.w.crmsystem.domain.PotentialcustomerExample;
import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.service.HandOverHistoryService;
import com.w.crmsystem.service.PotentialcustomerSerivce;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/13 15:39
 * @Version 1.0
 */
@Controller
@RequestMapping("/customerPool")
public class CustomerPoolController {
    @Resource
    private PotentialcustomerSerivce potentialcustomerSerivce;
    @Resource
    private HandOverHistoryService handOverHistoryService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private Gson gson;
    @Resource
    private ResultInfo resultInfo;
    @Resource
    private ResultSingleInfo resultSingleInfo;
    @Resource
    private Potentialcustomer potentialcustomer;
    @Resource
    private HandOverHistory handOverHistory;

    /**
     * 分页查询全部
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public String queryAll(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit){
        List<PotentialcustomerExample> potentialcustomers = potentialcustomerSerivce.queryAll(page,limit);
        Gson gson=new Gson();
        ResultInfo<PotentialcustomerExample> resultInfo= new ResultInfo<>();
        resultInfo.setCode(0);
        resultInfo.setMsg("获取成功");
        resultInfo.setCount(potentialcustomerSerivce.countAll());
        resultInfo.setData(potentialcustomers);
        return gson.toJson(resultInfo);
    }

    /**
     * 条件分页查询
     * @param page
     * @param limit
     * @param key
     * @return
     */
    @RequestMapping("/queryAllByKey")
    @ResponseBody
    public String queryAllByKey(Integer page,Integer limit,String key){
        resultInfo.setCode(0);
        resultInfo.setCount(potentialcustomerSerivce.countByKey(key, 2));
        resultInfo.setData(potentialcustomerSerivce.queryLimitStatusByKey(page, limit, key, 2));
        return gson.toJson(resultInfo);
    }


    /**
     * 客户移交
     * @param pcId
     * @param sellerId
     * @param empId
     * @param reason
     * @return
     */
    @RequestMapping("/handOverInfo")
    @ResponseBody
    public String handOverInfo(Integer pcId,Integer sellerId,Integer empId,String reason){
        Subject subject= SecurityUtils.getSubject();
        System.out.println(empId);
        //将该客户状态改为正式客户
        potentialcustomerSerivce.changeState(pcId, 1);
        potentialcustomer.setPcId(pcId);
        Integer empId1=empId;
        if (StringUtils.isEmpty(empId)){
            empId1=employeeService.findByUserName((String) subject.getPrincipal()).getEmpId();
        }

        potentialcustomer.setSellerId(empId1);
        //更改该客户的负责人
        potentialcustomerSerivce.updatePotentialInfo(potentialcustomer);

        handOverHistory.setCustomerId(pcId);
        handOverHistory.setNewSellerId(empId);
        handOverHistory.setOldSellerId(sellerId);
        handOverHistory.setTransReason(reason);
        handOverHistory.setTransUser(employeeService.findByUserName((String) subject.getPrincipal()).getRealname());
        handOverHistory.setTransTime(new Date());

        handOverHistoryService.addHandOverhis(handOverHistory);

        resultSingleInfo.setMsg("该客户已从"+employeeService.queryById(sellerId)+"移交到"+employeeService.queryById(empId1)+"手上");
        resultSingleInfo.setCode(200);

        return gson.toJson(resultSingleInfo);
    }
}
