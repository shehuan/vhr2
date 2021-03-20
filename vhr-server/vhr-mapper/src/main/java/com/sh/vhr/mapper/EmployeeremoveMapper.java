package com.sh.vhr.mapper;

import com.sh.vhr.model.Employeeremove;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EmployeeremoveMapper{
    List<Employeeremove> getAllEmployeeremoves();
}