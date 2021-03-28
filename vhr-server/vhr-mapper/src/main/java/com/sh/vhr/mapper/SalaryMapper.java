package com.sh.vhr.mapper;

import com.sh.vhr.model.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SalaryMapper {
    List<Salary> getAllSalaries();

    int addSalary(Salary salary);

    int deleteSalaryById(Integer id);

    int updateSalary(Salary salary);
}