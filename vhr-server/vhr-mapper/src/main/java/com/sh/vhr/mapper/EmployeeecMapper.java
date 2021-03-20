package com.sh.vhr.mapper;

import com.sh.vhr.model.Employeeec;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EmployeeecMapper{
    List<Employeeec> getAllEmployeeecs();
}