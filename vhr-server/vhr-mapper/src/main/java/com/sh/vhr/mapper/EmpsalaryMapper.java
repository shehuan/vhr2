package com.sh.vhr.mapper;

import com.sh.vhr.model.Empsalary;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface EmpsalaryMapper{
    List<Empsalary> getAllEmpsalarys();
}