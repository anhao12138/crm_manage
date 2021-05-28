package com.w.crmsystem.controller.admin;

import com.w.crmsystem.base.ResultInfo;
import com.w.crmsystem.base.ResultSingleInfo;
import com.w.crmsystem.baseException.NullInfoException;
import com.w.crmsystem.domain.Employee;
import com.w.crmsystem.domain.EmployeeExample;
import com.w.crmsystem.service.EmployeeService;
import com.w.crmsystem.service.EmproleService;
import com.w.crmsystem.util.CreateInitMenu;
import com.w.crmsystem.util.ExcelRead;
import com.w.crmsystem.util.PasswordUtil;
import com.w.crmsystem.util.StringUtil;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author: yun
 * @Date 2021/5/4 17:51
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private EmproleService emproleService;

    /**
     * 员工登录
     * 包括超级管理员
     * @param userName
     * @param password
     * @param rememberMe
     * @return
     */
    @RequestMapping("/loginInfo")
    public ModelAndView login(String userName, String password, Boolean rememberMe){
        ModelAndView model=new ModelAndView();
        //密码加密
        String newPassword = PasswordUtil.encodePwd(password);
        System.out.println(newPassword);
        if (rememberMe==null){
            rememberMe=false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userName,newPassword);
        token.setRememberMe(rememberMe);
        System.out.println("==============>"+((UsernamePasswordToken) token).isRememberMe());
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
            System.out.println("登录成功");
            currentUser.getSession().setAttribute("user", userName);

            model.setViewName("index");
            return model;

        }catch (IncorrectCredentialsException ice) {
            System.out.println("密码匹配失败");
            model.addObject("info", "密码匹配失败");

        } catch (LockedAccountException lae) {
            System.out.println("锁定账户");
            model.addObject("info", "账户已被锁定");

        } catch (AuthenticationException ae) {
            System.out.println("认证失败");
            model.addObject("info", "认证失败");
        }
        model.setViewName("login");
        return model;
    }
    @RequestMapping("/initMenu")
    @ResponseBody
    public String initMenu(){
        Subject subject=SecurityUtils.getSubject();
        String userName = String.valueOf(subject.getSession().getAttribute("user"));
        CreateInitMenu createInitMenu=new CreateInitMenu();
        String initMenuInfo = createInitMenu.getInitMenuInfo(employeeService.findByUserNameForMenu(userName));
        //initMenuInfo已经经过gson进行转换了
        return initMenuInfo;
    }
    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 分页获取信息，非查询分页
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/queryListEmp")
    @ResponseBody
    public String queryListEmp(@RequestParam("page")Integer page, @RequestParam("limit")Integer limit){
        List<Employee> employees=employeeService.queryListEmp(page,limit);
        Gson gson=new Gson();
        ResultInfo<Employee> resultInfo= new ResultInfo<>();
        resultInfo.setCode(0);
        resultInfo.setMsg("获取成功");
        resultInfo.setCount(employeeService.countAll());
        resultInfo.setData(employees);
        return gson.toJson(resultInfo);
    }

    /**
     * 通过文件批量添加数据 此处需要检查文件后缀是否满足要求，不满足返回错误内容并抛弃此次请求
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/addBatchFile",method = RequestMethod.POST)
    @ResponseBody
    public String addBatchFile(@RequestParam MultipartFile file) throws IOException {

        ExcelRead read=new ExcelRead();
        String filename = file.getOriginalFilename().replace(".", ",");
        String[] split = filename.split(",");
        System.out.println(split[split.length-1]);
        System.out.println(file.getOriginalFilename());
        List<EmployeeExample> list=null;
        if (split[split.length-1].equals("xls")){
            list= read.ReadXls((FileInputStream) file.getInputStream());
        }else {
            list=read.ReadXlsx((FileInputStream) file.getInputStream());
        }
        int total=list.size();
        //批量加入
        int successTotal=employeeService.addBatch(list);
        Gson gson=new Gson();
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        resultSingleInfo.setCode(0);
        resultSingleInfo.setMsg("添加成功"+successTotal+"条数据,总共有"+total+"条数据");
        return gson.toJson(resultSingleInfo);
    }

    /**
     * 批量删除数据
     * 单条数据的删除同样适用
     * 前端传入的待删除员工ID的集合为empIds
     * @param empIds
     * @return
     */
    @RequestMapping("/delBatchEmp")
    @ResponseBody
    public String delBatchEmp(@RequestParam List<Integer> empIds){
//        List<Integer> list=new ArrayList<>();
//        for (int i = 0; i < empIds.length; i++) {
//            list.add(empIds[i]);
//        }
        Integer successTotal = employeeService.delBatchEmp(empIds);
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        Gson gson=new Gson();
        if (successTotal==empIds.size()){
            resultSingleInfo.setCode(200);

        }else {
            resultSingleInfo.setCode(201);
        }
        resultSingleInfo.setMsg("总共需要删除"+empIds.size()+"条数据！删除成功"+successTotal+"条数据");
        return gson.toJson(resultSingleInfo);
    }

    /**
     * 添加单条信息
     * @param employee
     * @return
     */
    @RequestMapping("/addSingleEmp")
    public String addSingleEmp(Employee employee){
        employeeService.addSingleEmp(employee);
        return "employee.do";
    }

    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    @RequestMapping("/updateEmp")
    public String updateEmp(Employee employee){
        employeeService.updateEmp(employee);
        return "employee.do";
    }

    /**
     * 重置密码 默认密码是：123456
     * @param id
     * @return
     */
    @RequestMapping("/resetPaswd")
    @ResponseBody
    public String resetPaswd(Integer id){
        boolean b = employeeService.resetPaswd(id);
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        Gson gson=new Gson();
        resultSingleInfo.setMsg("重置密码成功！重置后的密码是123456");
        resultSingleInfo.setCode(200);
        if(!b){
            resultSingleInfo.setCode(202);
            resultSingleInfo.setMsg("重置密码失败");
        }

        return gson.toJson(resultSingleInfo);
    }

    /**
     * 条件查询
     * keyword：姓名/邮箱
     * dept：部门
     * @param page
     * @param limit
     * @param keyword
     * @param deptId
     * @return
     */
    @RequestMapping("/queryByConditions")
    @ResponseBody
    public String queryByConditions(
            @RequestParam("page")Integer page,
            @RequestParam("limit")Integer limit,
            @RequestParam("keyword") String keyword,
            @RequestParam("deptId") Integer deptId){
        List<EmployeeExample> employees=employeeService.queryByConditions(page,limit,keyword,deptId);
        ResultInfo<EmployeeExample> resultInfo=new ResultInfo<>();
        resultInfo.setCode(0);
        resultInfo.setMsg("成功");
        resultInfo.setCount(employeeService.countByConditions(keyword,deptId));
        resultInfo.setData(employees);
        Gson gson=new Gson();
        return gson.toJson(resultInfo);
    }

    /**
     * 员工离职修改
     * @param empId
     * @return
     */
    @RequestMapping("/changeDeparture")
    @ResponseBody
    public String changeDeparture(Integer empId){
        employeeService.changeDeparture(empId);
        Gson gson=new Gson();
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        resultSingleInfo.setCode(200);
        resultSingleInfo.setMsg("已经离职成功");
        return gson.toJson(resultSingleInfo);
    }

    /**
     *
     * @param username
     * @param realname
     * @param birthDay
     * @param email
     * @param sex
     * @param phone
     * @param deptId
     * @param roles
     * @return
     */
    @RequestMapping("/addAndUpdateEmployeeInfo")
    @ResponseBody
    public String addEmployeeInfo(@RequestParam("username")String username,
                                  @RequestParam("realName")String realname,
                                  @RequestParam("birthDay")String birthDay,
                                  @RequestParam("email")String email,
                                  @RequestParam("sex")String sex,
                                  @RequestParam("phone")String phone,
                                  @RequestParam("deptId")Integer deptId,
                                  @RequestParam("roles")List<Integer> roles,
                                  @RequestParam("empId")Integer empId){

        //不为空判断
        if (StringUtils.isEmpty(sex)){
            new NullInfoException("性别不能为空");
        }
        if (StringUtils.isEmpty(username)){
            new NullInfoException("工号不能为空");
        }
        if (StringUtil.isEmptiy(realname)){
            new NullInfoException("员工真实姓名不能为空");
        }
        if (StringUtil.isEmptiy(birthDay)){
            new NullInfoException("员工出生日期不能为空");
        }
        if (StringUtil.isEmptiy(email)){
            new NullInfoException("员工邮箱不能为空");
        }

        Employee employee=new Employee();
        employee.setPassword(PasswordUtil.encodePwd("123456"));
        employee.setInputtime(new Date());
        employee.setEmail(email);
        employee.setTel(phone);
        employee.setRealname(realname);
        employee.setDeptId(deptId);
        //格式化日期 String转换Date
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(birthDay);
            employee.setBirthDay(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employee.setState(1);
        employee.setUsername(username);

        Gson gson=new Gson();
        ResultSingleInfo resultSingleInfo=new ResultSingleInfo();
        System.out.println("判断是更新还是插入");
        if (empId!=null){
            //更新操作
            System.out.println("更新操作");
            employee.setEmpId(empId);
            employeeService.updateEmp(employee);
            emproleService.delBatchById(empId);
            if (roles.size()>0){
                emproleService.addEmprole(roles, empId);
            }
            resultSingleInfo.setMsg("更新员工："+realname+"的信息已成功");
        }else {
            //插入操作

            employeeService.addSingleEmp(employee);
            emproleService.addEmprole(roles,employee.getEmpId());
            resultSingleInfo.setMsg("添加员工："+realname+"的信息已成功");
        }

        resultSingleInfo.setCode(200);
        return gson.toJson(resultSingleInfo);
    }

    /**
     * 通过用户ID获取用户所拥有的角色
     * @param empId
     * @return
     */
    @RequestMapping("/getRoles")
    @ResponseBody
    public String getRoles(Integer empId){
        List<Integer> list = emproleService.queryRoleIds(empId);
        Gson gson=new Gson();

        return gson.toJson(new ResultSingleInfo(200,"成功",list));
    }
}
