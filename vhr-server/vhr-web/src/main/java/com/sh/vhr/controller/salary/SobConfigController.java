package com.sh.vhr.controller.salary;

import com.sh.vhr.model.RespBean;
import com.sh.vhr.model.RespPageBean;
import com.sh.vhr.model.Salary;
import com.sh.vhr.service.EmployeeService;
import com.sh.vhr.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SalaryService salaryService;

    @GetMapping("/")
    public RespPageBean getEmployeeWithSalaryByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                                    @RequestParam(defaultValue = "10") Integer pageSize) {
        return employeeService.getEmployeeWithSalaryByPage(pageNum, pageSize);
    }

    @GetMapping("/salaries")
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer empId, Integer salaryId) {
        if (salaryService.updateEmployeeSalary(empId, salaryId) == 2) {
            return RespBean.ok("更新成功！");
        }
        return RespBean.error("更新失败");
    }
}
