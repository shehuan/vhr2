package com.sh.vhr.service;

import com.sh.vhr.mapper.DepartmentMapper;
import com.sh.vhr.mapper.EmployeeMapper;
import com.sh.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public List<Department> getAllDepartments2() {
        return departmentMapper.getAllDepartments();
    }

    @Transactional
    public Boolean addDepartment(Department department) {
        // 添加部门
        departmentMapper.addDepartment(department);
        // 查询上级部门
        Department parentDepartment = departmentMapper.getParentDepartment(department.getParentId());
        // 修改新添加部门的depPath
        department.setDepPath(parentDepartment.getDepPath() + "." + department.getId());
        departmentMapper.updateDepartmentById(department);
        if (!parentDepartment.getIsParent()) {
            // 修改上级部门的isParent
            parentDepartment.setIsParent(true);
            departmentMapper.updateDepartmentById(parentDepartment);
        }
        return true;
    }

    @Transactional
    public Integer deleteDepartment(Integer id, Integer parentId) {
        // 查询部门下是否有员工
        if (employeeMapper.getEmployeeCountByDepartmentId(id) > 0) {
            return -1;
        }
        // 删除部门
        if (departmentMapper.deleteDepartmentById(id) != 1) {
            return -2;
        }
        // 查询已删除部门的上级部门是否还有下级部门
        Integer count = departmentMapper.getChildrenDepartmentCount(parentId);
        if (count == 0) {
            // 修改上级部门的isParent
            Department parentDepartment = new Department();
            parentDepartment.setId(parentId);
            parentDepartment.setIsParent(false);
            departmentMapper.updateDepartmentById(parentDepartment);
        }
        return 1;
    }
}
