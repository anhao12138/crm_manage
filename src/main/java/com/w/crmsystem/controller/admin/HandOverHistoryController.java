package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.service.HandOverHistoryService;
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
 * @Date 2021/5/15 1:14
 * @Version 1.0
 */
@Controller
@RequestMapping("/handoverHis")
public class HandOverHistoryController {

    @Resource
    private HandOverHistoryService handOverHistoryService;
    @Resource
    private ResultInfo resultInfo;
    @Resource
    private Gson gson;

    @RequestMapping("/queryAllByKey")
    @ResponseBody
    public String queryAllByKey(Integer page,Integer limit,String keyword,String startTime,String endTime){
        resultInfo.setCode(0);
        if (StringUtils.isEmpty(keyword)&&StringUtils.isEmpty(startTime)&& StringUtils.isEmpty(endTime)){
            resultInfo.setData(handOverHistoryService.queryAll(page,limit));
            resultInfo.setCount(handOverHistoryService.countAll());
        }else {
            resultInfo.setCount(handOverHistoryService.countByKey(keyword,startTime,endTime));
            resultInfo.setData(handOverHistoryService.queryByKey(page,limit,keyword,startTime,endTime));
        }
        return gson.toJson(resultInfo);
    }

}
