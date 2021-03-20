package com.sh.vhr.mapper;

import com.sh.vhr.model.Nation;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface NationMapper{
    List<Nation> getAllNations();

    Nation getNationById(Integer id);
}