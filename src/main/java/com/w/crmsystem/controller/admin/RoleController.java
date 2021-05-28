package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.baseException.NullInfoException;
import com.w.crmsystem.domain.Role;
import com.w.crmsystem.domain.RoleExample;
import com.w.crmsystem.service.RoleService;
import com.w.crmsystem.service.RolepermissionService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/5/9 9:11
 * @Version 1.0
 */
@Controller
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private RolepermissionService rolepermissionService;

    /**
     * 获取所有角色 分页查询
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryAllRole")
    @ResponseBody
    public String queryAllRole(Integer page,Integer limit){
        List<RoleExample> roles= roleService.queryAllLimitRole(page,limit);
        ResultInfo<RoleExample> resultInfo=new ResultInfo<>();
        Gson gson=new Gson();
        resultInfo.setCode(0);
        resultInfo.setCount(roleService.countAll());
        resultInfo.setMsg("成功");
        resultInfo.setData(roles);
        return gson.toJson(resultInfo);
    }

    /**
     * 删除角色by ID
     * @param roleId
     * @return
     */
    @RequestMapping("/delRoleById")
    @ResponseBody
    public String delRoleById(Integer roleId){
        boolean b = roleService.delRoleById(roleId);
        Gson gson=new Gson();
        if (b){
           return gson.toJson(new ResultSingleInfo(200,"删除角色成功",new String()));
        }
       return gson.toJson(new ResultSingleInfo(200,"删除角色成功",new String()));
    }

    @RequestMapping("/addRoleInfo")
    @ResponseBody
    public String addRoleInfo(
            @RequestParam("sn") String sn,
            @RequestParam("name") String name,
            @RequestParam("perIds") List<Integer> perIds){

        if (StringUtils.isEmpty(sn)){
            new NullInfoException("角色编号不能为空");
        }
        if (StringUtils.isEmpty(name)){
            new NullInfoException("角色名称不能为空");
        }
        Role role=new Role();
        role.setName(name);
        role.setSn(sn);
        roleService.addRoleInfo(role);
        System.out.println("插入后的返回对象："+role.getRoleId());
        rolepermissionService.addRolePermission(role.getRoleId(),perIds);

        Gson gson=new Gson();
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        resultSingleInfo.setCode(200);
        resultSingleInfo.setMsg("添加新的角色成功啦^v^呱唧呱唧");
        return gson.toJson(resultSingleInfo);
    }

    /**
     * 修改角色信息
     * 包括：角色名称，编号，权限
     * 步骤：
     *  1，修改role表中的角色信息
     *  2，先删除该角色ID所拥有的权限信息在rolepermission表中
     *  3，插入权限信息到rolepermission表中
     *
     * @Param info
     * @return
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(String sn,String name,Integer roleId,@RequestParam("perIds") List<Integer> perIds){


        System.out.println("sn="+sn+"\tname="+name+"\troleId="+roleId+"\tperIds="+perIds);
        if (StringUtils.isEmpty(sn)){
            new NullInfoException("角色编号不能为空");
        }
        if (StringUtils.isEmpty(name)){
            new NullInfoException("角色名称不能为空");
        }
        Role role=new Role(roleId,sn,name);
        roleService.updateRoleInfo(role);
        rolepermissionService.delByRoleId(role.getRoleId());
        if (perIds.size()!=0){
            rolepermissionService.addRolePermission(role.getRoleId(),perIds);
        }


        Gson gson=new Gson();
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        resultSingleInfo.setCode(200);
        resultSingleInfo.setMsg("修改成功啦^v^呱唧呱唧");
        return gson.toJson(resultSingleInfo);
    }

}
