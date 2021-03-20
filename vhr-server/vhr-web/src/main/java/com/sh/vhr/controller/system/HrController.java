package com.sh.vhr.controller.system;

import com.sh.vhr.model.Hr;
import com.sh.vhr.model.RespBean;
import com.sh.vhr.model.Role;
import com.sh.vhr.service.HrService;
import com.sh.vhr.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;

    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public List<Hr> getAllHrs(String keyword) {
        return hrService.getAllHrs(keyword);
    }

    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @GetMapping("/role")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHr(Integer hrid, Integer[] rids) {
        if (hrService.updateHrRole(hrid, rids)) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @DeleteMapping("/{hrid}")
    public RespBean deleteHrById(@PathVariable Integer hrid) {
        if (hrService.deleteHrById(hrid) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
