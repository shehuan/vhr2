package com.sh.vhr.mapper;

import com.sh.vhr.model.Adjustsalary;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AdjustsalaryMapper{
    List<Adjustsalary> getAllAdjustsalarys();
}