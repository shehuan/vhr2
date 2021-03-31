package com.sh.vhr.mapper;

import com.sh.vhr.model.Empsalary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpsalaryMapper{
    List<Empsalary> getAllEmpsalarys();

    Integer deleteByEmpId(Integer empId);

    Integer addRecord(@Param("empId") Integer empId,@Param("salaryId") Integer salaryId);
}