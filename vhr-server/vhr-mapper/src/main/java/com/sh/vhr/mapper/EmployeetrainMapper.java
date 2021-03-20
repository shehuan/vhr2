package com.sh.vhr.mapper;

import com.sh.vhr.model.Employeetrain;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EmployeetrainMapper{
    List<Employeetrain> getAllEmployeetrains();
}