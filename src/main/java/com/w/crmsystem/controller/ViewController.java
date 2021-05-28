package com.w.crmsystem.controller;

import com.w.crmsystem.base.TraceTypeInfo;
import com.google.gson.Gson;
import com.w.crmsystem.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 *   @ClassName: ViewController
* @Description:
 * @Author: yun
 * @Date 2021/5/4 17:50
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
public class ViewController {

    @Resource
    private DepartmentService departmentService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RoleService roleService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private DetailDirectoryService detailDirectoryService;
    @Resource
    private DirdetialService dirdetialService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private TraceTypeInfo traceTypeInfo;
    @Resource
    private Gson gson;

    @RequestMapping("/head")
    public String Head(){
        return "login";
    }

    @RequestMapping("/index")
    public String Index(){
        return "index";
    }
    @RequestMapping("/department")
    public String DepartMent(){
        return "admin/systemMan/department";
    }


    @RequestMapping("/employee")
    public String Employee(Model model){
        model.addAttribute("departments", departmentService.queryAllInfoDep());
        Gson gson=new Gson();
        model.addAttribute("roles", gson.toJson(roleService.queryAll()));
        return "admin/systemMan/employee";
    }
    @RequestMapping("/permission")
    public String Permission(){
        return "admin/systemMan/permission";
    }
    @RequestMapping("/role")
    public String Role(Model model){
        Gson gson=new Gson();
        model.addAttribute("permissions",gson.toJson(permissionService.queryAll()));
        return "admin/systemMan/role";
    }

    @RequestMapping("/followHisMan")
    public String FollowHisMan(){
        return "admin/clientHis/followHisMan";
    }

    @RequestMapping("/handoverHisMan")
    public String HandoverHisMan(){
        return "admin/clientHis/handoverHisMan";
    }

    @RequestMapping("/CustomerRePoll")
    public String CustomerRePoll(Model model){
        model.addAttribute("employees", employeeService.queryAllEmpInfo());
        return"admin/clientMan/CustomerRePoll";
    }

    @RequestMapping("/fmCustomerMan")
    public String FmCustomerMan(Model model){
        setModel(model);
        return "admin/clientMan/fmCustomerMan";
    }

    @RequestMapping("/potentialCustoMan")
    public String PotentialCustoMan(Model model){
        setModel(model);
        return "admin/clientMan/potentialCustoMan";
    }

    @RequestMapping("/failCustomer")
    public String failCustomer(){
        return "admin/clientMan/FailCustomer";
    }
    @RequestMapping("/lostCustomer")
    public String lostCustomer(){
        return "admin/clientMan/lostCustomer";
    }

    @RequestMapping("/datadictionary")
    public String Datadictionary(){
        return "admin/dataMan/datadictionary";
    }

    @RequestMapping("/detaileddata")
    public String Detaileddata(Model model){
        model.addAttribute("dataDics", dataDictionaryService.queryAll());
        return "admin/dataMan/detaileddata";
    }

    @RequestMapping("/customer")
    public String Customer(){
        return "admin/reportMan/customer";
    }

    @RequestMapping("/echars_b")
    public String Echars_b(){
        return "admin/reportMan/echars_b";
    }

    @RequestMapping("/echars_z")
    public String Echars_z(){
        return "admin/reportMan/echars_z";
    }
    @RequestMapping("/line")
    public String Line(){
        return "admin/reportMan/line";
    }

    @RequestMapping("/403")
    public String unauthorized(){
        return "403";
    }


    public void setModel(Model model){
        model.addAttribute("details", dirdetialService.queryDetailByDataId("职业"));
        model.addAttribute("sources", dirdetialService.queryDetailByDataId("来源"));
        model.addAttribute("employees", employeeService.queryAllEmpInfo());
        model.addAttribute("traceInfo", traceTypeInfo.getTraceInfo());
    }

}
