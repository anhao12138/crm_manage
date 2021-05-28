package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.baseException.NullInfoException;
import com.w.crmsystem.domain.DetailDirectory;
import com.w.crmsystem.domain.Dirdetial;
import com.w.crmsystem.domain.DirdetialExample;
import com.w.crmsystem.service.DetailDirectoryService;
import com.w.crmsystem.service.DirdetialService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:
*  @Description: 数据字典明细
 * @Author: yun
 * @Date 2021/5/13 1:31
 * @Version 1.0
 */
@Controller
public class DirdetialController {

    @Resource
    private DetailDirectoryService detailDirectoryService;

    @Resource
    private DirdetialService dirdetialService;

    @Resource
    private ResultSingleInfo resultSingleInfo;

    @Resource
    private Gson gson;

    /**
     * 分页查询包括关键字分页查询
     * @param page
     * @param limit
     * @param key
     * @return
     */
    @RequestMapping("/queryDeatilInfo")
    @ResponseBody
    public String queryDeatilInfo(Integer page,Integer limit,String key,Integer dirdicId){
        List<DirdetialExample> dirdetialExamples=null;
        ResultInfo<DirdetialExample> resultInfo=new ResultInfo<>();
        Gson gson=new Gson();
        Integer count=0;
        if (StringUtils.isEmpty(key)&&StringUtils.isEmpty(dirdicId)){
            //普通查询
            dirdetialExamples=dirdetialService.queryDeatilInfo(page,limit);
            count=dirdetialService.countAll();
        }else {
            dirdetialExamples=dirdetialService.queryDeatilInfoByKey(page,limit,key,dirdicId);
            count=dirdetialService.countByKey(key,dirdicId);
        }
        resultInfo.setData(dirdetialExamples);
        resultInfo.setCount(count);
        resultInfo.setCode(0);
        resultInfo.setMsg("成功");
        return gson.toJson(resultInfo);

    }

    /**
     * 单条数据删除
     * @param dirdeId
     * @return
     */
    @RequestMapping("/delByIdDirDetail")
    @ResponseBody
    public String delByIdDirDetail(Integer dirdeId){
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        Gson gson=new Gson();
        if (dirdetialService.delByIdDirDetail(dirdeId)){
            detailDirectoryService.delDetailById(dirdeId);
            resultSingleInfo.setMsg("删除成功啦开心心^v^");
            resultSingleInfo.setCode(200);
            return gson.toJson(resultSingleInfo);
        }
        resultSingleInfo.setMsg("删除失败啦哭戚戚");
        resultSingleInfo.setCode(202);
        return gson.toJson(resultSingleInfo);
    }

    /**
     * 更新或者添加 字典详细信息
     * @param dirdetailId 更新用ID
     * @param title 标题
     * @param sequence 序号
     * @param dirId 字典ID
     * @return
     */
    @RequestMapping("/addDirDetailAndUpdate")
    @ResponseBody
    public String addDirDetailAndUpdate(Integer dirdetailId,String title,Integer sequence,Integer dirId){
        if (StringUtils.isEmpty(title)){
            new NullInfoException("数据字典明细不能为空哟亲");
        }
        if (StringUtils.isEmpty(sequence)){
            new NullInfoException("数据字典的序号可是不能为空的呢亲");
        }
        if (StringUtils.isEmpty(dirId)){
            new NullInfoException("不要做无谓的挣扎了");
        }
        DetailDirectory detailDirectory=new DetailDirectory();

        Dirdetial dirdetial=new Dirdetial();

        dirdetial.setSequence(sequence);
        dirdetial.setTitle(title);

        if (StringUtils.isEmpty(dirdetailId)){
            //新增操作
            dirdetialService.addDirDetailInfo(dirdetial);
            detailDirectory.setDetId(dirdetial.getDirdetailId());
            detailDirectory.setDirId(dirId);
            //同时向中间表添加数据
            detailDirectoryService.addDeatilDire(detailDirectory);

            resultSingleInfo.setCode(200);
            resultSingleInfo.setMsg("添加"+title+"成功啦！！！呱唧呱唧");
        }else {
            //更新操作
            dirdetial.setDirdetailId(dirdetailId);
            detailDirectory.setDetId(dirdetailId);
            detailDirectory.setDirId(dirId);
            //同时更新中间表
            detailDirectoryService.updateDetailInfo(detailDirectory);

            dirdetialService.updateDirDetail(dirdetial);
            resultSingleInfo.setCode(200);
            resultSingleInfo.setMsg("更新"+title+"成功啦！！！呱唧呱唧");

        }
        return gson.toJson(resultSingleInfo);
    }
}
