package com.sh.vhr.service;

import com.sh.vhr.mapper.EmpsalaryMapper;
import com.sh.vhr.mapper.SalaryMapper;
import com.sh.vhr.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private EmpsalaryMapper empsalaryMapper;

    public List<Salary> getAllSalaries() {
        return salaryMapper.getAllSalaries();
    }

    public int addSalary(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.addSalary(salary);
    }

    public int deleteSalaryById(Integer id) {
        return salaryMapper.deleteSalaryById(id);
    }

    public int updateSalary(Salary salary) {
        return salaryMapper.updateSalary(salary);
    }

    public Integer updateEmployeeSalary(Integer empId, Integer salaryId) {
        return empsalaryMapper.addRecord(empId, salaryId);
    }
}
