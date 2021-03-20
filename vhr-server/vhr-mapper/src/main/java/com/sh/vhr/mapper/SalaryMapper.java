package com.sh.vhr.mapper;

import com.sh.vhr.model.Salary;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SalaryMapper{
    List<Salary> getAllSalarys();
}