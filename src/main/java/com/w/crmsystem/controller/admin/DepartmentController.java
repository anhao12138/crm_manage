package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.domain.Department;
import com.w.crmsystem.service.DepartmentService;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName:
*  @Description: 部门信息
 * @Author: yun
 * @Date 2021/5/6 22:20
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    /**
     * 分页查询，不包括条件分类查询操作
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryList")
    @ResponseBody
    public String queryList(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit){
        List<Department> departments = departmentService.queryByList(page, limit);
        ResultInfo<Department> resultInfo=new ResultInfo<>();
        resultInfo.setCode(0);
        resultInfo.setCount(departmentService.countAll());
        resultInfo.setMsg("成功");
        resultInfo.setData(departments);
        Gson gson=new Gson();
        System.out.println(departments);
        return gson.toJson(resultInfo);
    }

    /**
     * 添加或更新部门信息
     * 通过判断deptId是否为空可以知晓是添加还是更新
     * deptId为空：添加
     * deptId不为空：更新
     * @param department
     * @return
     */
    @RequestMapping("/addAndUpadteInfoDep")
    public String addInfoDep(Department department){
        System.out.println("更新添加操作===========================>"+department.toString());
        Integer deptId = department.getDeptId();
        if (deptId==null){
            departmentService.insertDep(department);
        }else {
            departmentService.updateDep(department);
        }

        return "redirect:/department.do";
    }


    /**
     * 通过ID删除部门信息
     * @param deptId
     * @return
     */
    @RequestMapping("/delInfoDep")
    @ResponseBody
    public String delInfoDep(@RequestParam("deptId") Integer deptId){
        System.out.println("deptID===================>"+deptId);
        Gson gson=new Gson();
        if(departmentService.delById(deptId)){
            return gson.toJson(new ResultSingleInfo(200,"删除成功",new String()));
        }
        return gson.toJson(new ResultSingleInfo(202,"删除成功",new String()));
    }

}
