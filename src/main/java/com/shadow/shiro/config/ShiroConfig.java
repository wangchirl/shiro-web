package com.shadow.shiro.config;

import com.shadow.shiro.shiro.CustomerFilter;
import com.shadow.shiro.shiro.CustomerRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    // shiro 安全管理器
    @Bean
    public SecurityManager securityManager(CustomerRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义 realm 身份验证 + 授权
        securityManager.setRealm(realm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    // shiro filter
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        // 自定义 shiro 过滤器
        HashMap<String, Filter> filters = new HashMap<>();
        filters.put("cust", new CustomerFilter());
        shiroFilter.setFilters(filters);

        // 过滤器路径配置，必须使用有序表
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login.html", "anon"); // 匿名访问
        filterMap.put("/login", "anon");
        filterMap.put("/captcha", "anon");
        filterMap.put("/**", "cust");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    // 支持注解权限校验
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

}
