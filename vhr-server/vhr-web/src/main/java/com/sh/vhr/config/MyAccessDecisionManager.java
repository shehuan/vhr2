package com.sh.vhr.config;


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
 * 将当前用户具有的角色和MyFilterInvocationSecurityMetadataSource中getAttributes()方法的角色对比
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // configAttributes是MyFilterInvocationSecurityMetadataSource中getAttributes()方法的返回值，即需要的角色
        for (ConfigAttribute ca : configAttributes) {
            String needRole = ca.getAttribute();
            // 如果是MyFilterInvocationSecurityMetadataSource中返回的标记
            if ("ROLE_LOGIN".equals(needRole)) {
                // 是匿名用户，还没登录
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录，请登录！");
                } else {
                    // 已经登录，则直接结束循环，放行
                    return;
                }
            }

            // 当前用户具有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 将当前用户具有的角色和访问路径需要的角色比对
            for (GrantedAuthority authority : authorities) {
                // 一个路径可能多个角色都能访问，有时候需要用户同时具备多个角色才能访问，这里简单起见只需要用户有一个角色能匹配即可放行
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员！");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
