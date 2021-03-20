package com.sh.vhr.controller.system.basic;

import com.sh.vhr.model.Menu;
import com.sh.vhr.model.RespBean;
import com.sh.vhr.model.Role;
import com.sh.vhr.service.MenuService;
import com.sh.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("mids/{roleId}")
    public List<Integer> getMenuIdsByRoleId(@PathVariable Integer roleId) {
        return menuService.getMenuIdsByRoleId(roleId);
    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer roleId, Integer[] menuIds) {
        if (menuService.updateMenuRole(roleId, menuIds)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (roleService.addRole(role) == 1) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @DeleteMapping("/role/{roleId}")
    public RespBean deleteRoleById(@PathVariable Integer roleId) {
        if (roleService.deleteRoleById(roleId) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
