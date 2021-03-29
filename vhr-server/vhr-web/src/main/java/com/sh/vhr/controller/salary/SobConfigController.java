package com.sh.vhr.controller.salary;

import com.sh.vhr.model.RespPageBean;
import com.sh.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salary/sobcfg")
public class SobConfigController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public RespPageBean getEmployeeWithSalaryByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "10") Integer pageSize) {
        return employeeService.getEmployeeWithSalaryByPage(pageNum, pageSize);
    }
}
