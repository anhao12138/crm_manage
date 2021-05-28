package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.baseException.NullInfoException;
import com.w.crmsystem.domain.DataDictionary;
import com.w.crmsystem.service.DataDictionaryService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:
*  @Description: 数据字典管理
 * @Author: yun
 * @Date 2021/5/12 19:59
 * @Version 1.0
 */
@Controller
public class DataDictionaryController {

    @Resource
    private DataDictionaryService dataDictionaryService;

    /**
     * 分页查询数据字典 包括关键字模糊查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryDataDicInfo")
    @ResponseBody
    public String queryDataDicInfo(Integer page,Integer limit,String key){
        List<DataDictionary> dataDictionaries=null;
        ResultInfo<DataDictionary> resultInfo=new ResultInfo<>();
        Integer count=0;
        Gson gson=new Gson();

        if (!StringUtils.isEmpty(key)){
            //执行模糊查询
            dataDictionaries=dataDictionaryService.queryByKeyLimit(page,limit,key);
            count=dataDictionaryService.countByKey(key);

        }else {
            //纯粹的分页查询
            dataDictionaries=dataDictionaryService.queryDataDicInfo(page,limit);
            count=dataDictionaryService.countAll();
        }
        resultInfo.setMsg("成功");
        resultInfo.setCode(0);
        resultInfo.setCount(count);
        resultInfo.setData(dataDictionaries);

        return gson.toJson(resultInfo);
    }


    /**
     * 根据ID删除数据字典
     * @param dataId
     * @return
     */
    //    @RequiresPermissions(value = {"systemDictionary:delete","字典目录删除"},logical = Logical.OR)
    @RequestMapping("/delById")
    @ResponseBody
    public String delById(Integer dataId){
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        Gson gson=new Gson();
        if(dataDictionaryService.delById(dataId)){
            resultSingleInfo.setCode(200);
            resultSingleInfo.setMsg("删除成功了哟");
            return gson.toJson(resultSingleInfo);
        }
        resultSingleInfo.setCode(202);
        resultSingleInfo.setMsg("删除失败了好尴尬");
        return gson.toJson(resultSingleInfo);
    }

    /**
     * 添加或者更新数据字典
     * @param intro
     * @param title
     * @param sn
     * @param dicdirId
     * @return
     */
    @RequestMapping("/addAndUpdate")
    @ResponseBody
    public String addAndUpdate(String intro,String title,String sn,Integer dicdirId){
        if (StringUtils.isEmpty(intro)){
            new NullInfoException("字典目录简介不能为空的哟亲");
        }
        if (StringUtils.isEmpty(sn)){
            new NullInfoException("字典编号不能为空的哟亲");
        }
        if (StringUtils.isEmpty(title)){
            new NullInfoException("字典目录名称不能为空");
        }
        DataDictionary dataDictionary=new DataDictionary();
        dataDictionary.setIntro(intro);
        dataDictionary.setSn(sn);
        dataDictionary.setTitle(title);
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        if (StringUtils.isEmpty(dicdirId)){
            //添加
            dataDictionaryService.addDataDicInfo(dataDictionary);
            resultSingleInfo.setMsg("新增名为"+dataDictionary.getTitle()+"数据字典成功");
        }else {
            //更新
            dataDictionaryService.updateDataDicInfo(dataDictionary);
            resultSingleInfo.setMsg("修改名为"+dataDictionary.getTitle()+"数据字典成功");
        }

        resultSingleInfo.setCode(200);
        Gson gson=new Gson();
        return gson.toJson(resultSingleInfo);
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(DataDictionary dataDictionary){
        dataDictionaryService.updateDataDicInfo(dataDictionary);
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        resultSingleInfo.setMsg("修改名为"+dataDictionary.getTitle()+"数据字典成功");
        resultSingleInfo.setCode(200);
        Gson gson=new Gson();
        return gson.toJson(resultSingleInfo);
    }
}
