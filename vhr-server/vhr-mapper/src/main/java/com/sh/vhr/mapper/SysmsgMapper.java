package com.sh.vhr.mapper;

import com.sh.vhr.model.Sysmsg;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SysmsgMapper{
    List<Sysmsg> getAllSysmsgs();
}