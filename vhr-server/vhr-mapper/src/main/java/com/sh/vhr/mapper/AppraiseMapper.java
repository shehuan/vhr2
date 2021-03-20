package com.sh.vhr.mapper;

import com.sh.vhr.model.Appraise;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AppraiseMapper{
    List<Appraise> getAllAppraises();
}