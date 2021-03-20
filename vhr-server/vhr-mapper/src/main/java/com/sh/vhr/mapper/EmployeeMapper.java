package com.sh.vhr.mapper;

import com.sh.vhr.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> getAllEmployees();

    Integer getEmployeeCountByDepartmentId(Integer departmentId);

    List<Employee> getEmployeeByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize, @Param("keyword") String keyword);

    Long getTotal(String keyword);

    Integer addEmployee(Employee employee);

    Integer getMaxWorkID();

    Integer deleteEmployee(Integer id);

    Integer updateEmployee(Employee employee);

    int addEmployees(@Param("employeeList") List<Employee> employeeList);

    List<Employee> getEmployeeByPageSuper(@Param("offset") Integer offset,
                                          @Param("pageSize") Integer pageSize,
                                          @Param("emp") Employee employee,
                                          @Param("beginDateScope") Date[] beginDateScope);

    Long getTotalSuper(@Param("emp") Employee employee, @Param("beginDateScope") Date[] beginDateScope);
}