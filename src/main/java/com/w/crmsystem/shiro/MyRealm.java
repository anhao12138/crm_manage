package com.w.crmsystem.shiro;

import com.w.crmsystem.domain.Employee;
import com.w.crmsystem.service.EmployeeService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/4/26 12:52
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {

    private static Logger log = Logger.getLogger(MyRealm.class);
    @Resource
    private EmployeeService employeeService;

    /**
     * shiro授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("=======================================>"+principals.getPrimaryPrincipal());
        String username = (String)principals.getPrimaryPrincipal();
        //查找将要登录的用户信息通过用户名
        Employee user = employeeService.findByUserName(username);
        //登录成功后锁定用户
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        log.info("执行service");
        //根据用户名查找对应的角色集合 当前用户所有的角色
        authorizationInfo.setRoles(employeeService.findRoles(user.getUsername()));
        //根据用户名查找对应的资源集合 当前用户所拥有的角色的权限
        authorizationInfo.setStringPermissions(employeeService.findPermissions(user.getUsername()));
        System.out.println("======================================================================");
        System.out.println("角色：=========>"+employeeService.findRoles(user.getUsername()));
        System.out.println("权限：=========>"+employeeService.findPermissions(user.getUsername()));
        System.out.println("======================================================================");
        return authorizationInfo;
    }

    /**
     * shiro认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();

        //根据用户名查找用户
        Employee employee = employeeService.findByUserName(username);
        System.out.println(employee);
        if(employee == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser",employee);
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                employee.getUsername(),
                employee.getPassword(),
                getName()
        );


        return authenticationInfo;
    }
}