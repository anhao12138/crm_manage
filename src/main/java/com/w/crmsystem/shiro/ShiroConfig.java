package com.w.crmsystem.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *   @ClassName:  
* @Description:
 * @Author: yun
 * @Date 2021/4/26 15:05
 * @Version 1.0
 */
@Configuration
public class ShiroConfig {

    //需要在shiro配置文件中增加一个方法，用于thymeleaf和shiro标签配合使用

    //*** 千万别忘了加 bean , 否则页面无法正常的显示权限 !!! ***//
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置SecuritManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> filterChainDefinitionMaps = new LinkedHashMap<String, String>();
        //filterChainDefinitionMaps.put("/","anon");
        filterChainDefinitionMaps.put("/head.do","anon");
        filterChainDefinitionMaps.put("/index.do", "anon");
        filterChainDefinitionMaps.put("/department.do", "perms[department.do]");
        filterChainDefinitionMaps.put("/employee.do", "perms[employee.do]");
        filterChainDefinitionMaps.put("/permission.do", "perms[permission.do]");
        filterChainDefinitionMaps.put("/role.do", "perms[role.do]");
        filterChainDefinitionMaps.put("/403.do", "anon");
        //filterChainDefinitionMaps.put("/queryByConditions.do", "perms[queryByConditions.do]");
        // 配置退出过滤器,其中的具体代码Shiro已经替我们实现了
        filterChainDefinitionMaps.put("/logout", "logout");
        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
        filterChainDefinitionMaps.put("/**", "anon");

        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.do");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("redirect:/head.do");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index.do");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMaps);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realms());
        return securityManager;
    }

    @Bean
    public MyRealm realms(){
        MyRealm userRealm = new MyRealm();
        return userRealm;
    }

    //开启shiro aop注解支持.

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
