package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.service.DirdetialService;
import com.w.crmsystem.service.PotentialcustomerSerivce;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/13 15:39
 * @Version 1.0
 */

/**
 * 正式客户
 */
@Controller
@RequestMapping("/formal")
public class FormalCustomerController {

    @Resource
    private PotentialcustomerSerivce potentialcustomerSerivce;
    @Resource
    private DirdetialService dirdetialService;
    @Resource
    private Gson gson;
    @Resource
    private ResultSingleInfo resultSingleInfo;
    @Resource
    private ResultInfo resultInfo;

    /**
     * 使用日期降序排序所有分页查询
     * 分页查询包括普通分页和条件分页
     * 只查询流失客户和正式客户
     * @param page
     * @param limit
     * @param keyword
     * @param state
     * @return
     */
    @RequestMapping("/queryAllByKey")
    @ResponseBody
    public String queryAllByKey(Integer page,Integer limit,String keyword,Integer state){
        resultInfo.setCode(0);
        if (StringUtils.isEmpty(keyword)&&StringUtils.isEmpty(state)){
            //普通分页查询

            resultInfo.setData(potentialcustomerSerivce.queryLimitStatusAll(page,limit));
            resultInfo.setCount(potentialcustomerSerivce.countLimitStatusAll());

        }else {
            //条件分页查询

            resultInfo.setData(potentialcustomerSerivce.queryLimitStatusByKey(page,limit,keyword,state));
            resultInfo.setCount(potentialcustomerSerivce.countLimitStatusByKey(keyword,state));
        }
        return gson.toJson(resultInfo);
    }

}
