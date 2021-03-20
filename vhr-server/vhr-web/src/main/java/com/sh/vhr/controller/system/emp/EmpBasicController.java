package com.sh.vhr.controller.system.emp;

import com.sh.vhr.model.*;
import com.sh.vhr.service.*;
import com.sh.vhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;

    // 民族
    @Autowired
    NationService nationService;

    // 政治面貌
    @Autowired
    PoliticsstatusService politicsstatusService;

    // 职称
    @Autowired
    JobLevelService jobLevelService;

    // 职位
    @Autowired
    PositionService positionService;

    // 部门
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          String keyword) {
        return employeeService.getEmployeeByPage(pageNum, pageSize, keyword);
    }

    @GetMapping("/superSearch")
    public RespPageBean getEmployeeByPageSuper(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "10") Integer pageSize,
                                               Employee employee, Date[] beginDateScope) {
        return employeeService.getEmployeeByPageSuper(pageNum, pageSize, employee, beginDateScope);
    }

    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee) {
        if (employeeService.addEmployee(employee) == 1) {
            return RespBean.ok("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.getAllNations();
    }

    @GetMapping("/politicsstatus")
    public List<Politicsstatus> getAllPolitics() {
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/joblevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAllJobLevels();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/maxWorkID")
    public RespBean getMaxWorkID() {
        String workID = String.format("%08d", employeeService.getMaxWorkID() + 1);
        return RespBean.ok("", workID);
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmployee(@PathVariable Integer id) {
        if (employeeService.deleteEmployee(id) == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.ok("删除失败！");
    }

    @PutMapping("/")
    public RespBean updateEmployee(@RequestBody Employee employee) {
        if (employeeService.updateEmployee(employee) == 1) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.ok("更新失败！");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        List<Employee> employeeList = (List<Employee>) employeeService.getEmployeeByPage(null, null, null).getData();
        return POIUtils.employee2Excel(employeeList);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<Employee> employeeList = POIUtils.excel2Employee(file, nationService.getAllNations(),
                departmentService.getAllDepartments2(), politicsstatusService.getAllPoliticsstatus(),
                positionService.getAllPositions(), jobLevelService.getAllJobLevels());

        if (employeeService.addEmployees(employeeList) == employeeList.size()) {
            return RespBean.ok("上传成功！");
        }
        return RespBean.ok("上传失败！");
    }
}
