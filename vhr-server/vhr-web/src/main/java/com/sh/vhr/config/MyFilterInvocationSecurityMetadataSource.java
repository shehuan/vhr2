package com.sh.vhr.config;

import com.sh.vhr.model.Menu;
import com.sh.vhr.model.Role;
import com.sh.vhr.service.MenuService;
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
 * 根据当前的请求路径，获取访问该路径需要的角色
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuService menuService;

    // Ant风格的路径匹配
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 请求的地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
//        if (antPathMatcher.match("/verifyCode", requestUrl)) {
//            // 返回null表示当前请求不需要任何角色都能访问
//            return null;
//        }
        List<Menu> menus = menuService.getAllMenuWithRole();
        for (Menu menu : menus) {
            // 请求路径和menu中的路径匹配
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                // 获取访问路径需要的角色
                List<Role> roles = menu.getRoles();
                // 将角色集合转为角色编码数组
                String[] roleNames = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleNames[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(roleNames);
            }
        }
        // 如果前边未匹配到路径，则做如下返回，相当于一个标记，后续会根据这个标记让用户登录后才可以访问资源
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
