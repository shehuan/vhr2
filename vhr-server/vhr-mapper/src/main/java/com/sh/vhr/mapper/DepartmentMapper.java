package com.sh.vhr.mapper;

import com.sh.vhr.model.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    List<Department> getAllDepartments();

    List<Department> getAllDepartmentsByParentId(Integer parentId);

    Integer addDepartment(Department department);

    Department getParentDepartment(Integer id);

    Integer updateDepartmentById(Department department);

    Integer deleteDepartmentById(Integer id);

    Integer getChildrenDepartmentCount(Integer parentId);
}