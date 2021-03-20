package com.sh.vhr.mapper;

import com.sh.vhr.model.Politicsstatus;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PoliticsstatusMapper{
    List<Politicsstatus> getAllPoliticsstatuss();

    Politicsstatus getPoliticsstatusById(Integer id);
}