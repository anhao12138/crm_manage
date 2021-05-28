package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.domain.Permission;
import com.w.crmsystem.service.PermissionService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    /**
     * 权限列表数据
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/permissionAll")
    @ResponseBody
    public String permissionAll(Integer page,Integer limit){
        List<Permission> permissions = permissionService.queryList(page, limit);
        Gson gson=new Gson();
        ResultInfo<Permission> resultInfo=new ResultInfo<>();
        resultInfo.setCode(0);
        resultInfo.setMsg("获取成功");
        resultInfo.setCount(permissionService.countPer());
        resultInfo.setData(permissions);
        return gson.toJson(resultInfo);
    }

    /**
     * 改变权限是否可用
     * @param perId
     * @return
     */
    @RequestMapping("/changeLock")
    @ResponseBody
    public String changeLock(Integer perId,Integer statu){
        System.out.println(perId+"=====>"+statu);
        Gson gson=new Gson();
        if (permissionService.changeLock(perId,statu)){
            return gson.toJson(new ResultSingleInfo(200,"",new String()));
        }
        return gson.toJson(new ResultSingleInfo(202,"",new String()));
    }


}
