package com.sh.vhr.controller.system.basic;

import com.sh.vhr.model.Department;
import com.sh.vhr.model.RespBean;
import com.sh.vhr.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department) {
        if (departmentService.addDepartment(department)) {
            return RespBean.ok("添加成功！", department);
        }
        return RespBean.error("添加失败！");
    }

    @DeleteMapping("/{parentId}/{id}")
    public RespBean deleteDepartment(@PathVariable Integer parentId, @PathVariable Integer id) {
        Integer result = departmentService.deleteDepartment(id, parentId);
        if (result == -1) {
            return RespBean.error("该部门下有关联的员工，无法删除！");
        } else if (result == -2) {
            return RespBean.error("该部门下有关联的部门，无法删除！");
        }
        return RespBean.ok("删除成功！");
    }
}
