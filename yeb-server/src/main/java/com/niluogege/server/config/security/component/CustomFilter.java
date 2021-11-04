package com.niluogege.server.config.security.component;

import com.niluogege.server.pojo.Menu;
import com.niluogege.server.pojo.Role;
import com.niluogege.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 通过UI 获取 这次请求应该对应的角色
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private IMenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        System.out.println("CustomFilter 查询当前url 所需的角色列表");
        //获取到 请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> allMenuWithRole = menuService.getAllMenuWithRole();
        for (Menu menu : allMenuWithRole) {
            //通过url 找到 对应Menu对象
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                //返回所需角色列表
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                System.out.println("当前url 所需要的 角色列表 ="+str.toString());
                return SecurityConfig.createList(str);
            }
        }

        //没找到 说明主需要登录就可以
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
