package com.sh.vhr.service;

import com.sh.vhr.mapper.MenuMapper;
import com.sh.vhr.mapper.MenuRoleMapper;
import com.sh.vhr.model.Hr;
import com.sh.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Autowired
    MenuRoleMapper menuRoleMapper;

    /**
     * 查询当前用户可以访问的菜单
     *
     * @return
     */
    public List<Menu> getMenusByHrId() {
        Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuMapper.getMenusByHrId(hr.getId());
    }


    //    @Cacheable redis缓存
    public List<Menu> getAllMenuWithRole() {
        return menuMapper.getAllMenuWithRole();
    }

    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getMenuIdsByRoleId(Integer roleId) {
        return menuMapper.getMenuIdsByRoleId(roleId);
    }

    @Transactional
    public boolean updateMenuRole(Integer roleId, Integer[] menuIds) {
        menuRoleMapper.deleteByRoleId(roleId);
        if (menuIds == null || menuIds.length == 0) {
            return true;
        }
        Integer result = menuRoleMapper.insertRecord(roleId, menuIds);
        return result == menuIds.length;
    }
}
