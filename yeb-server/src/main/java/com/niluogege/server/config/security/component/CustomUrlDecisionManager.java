package com.niluogege.server.config.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限控制，判断用户角色
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collections) throws AccessDeniedException, InsufficientAuthenticationException {
        System.out.println("CustomUrlDecisionManager 拿到用户角色和 url 所需角色进行对比，如果满足则放行，不满足则报错 authentication="+authentication);
        for (ConfigAttribute configAttribute : collections) {
            //当前url 所需角色
            String needRole = configAttribute.getAttribute();
            System.out.println("needRole=" + needRole);

            //如果只需要登录
            if ("ROLE_LOGIN".equals(needRole)) {
                //AnonymousAuthenticationToken 就代表没有登录
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录请登录");
                } else {
                    return;
                }
            }
            //判断用户角色是否为url 所需角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                System.out.println("authority=" + authority.getAuthority());
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }

        }

        throw new AccessDeniedException("权限不足 请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
