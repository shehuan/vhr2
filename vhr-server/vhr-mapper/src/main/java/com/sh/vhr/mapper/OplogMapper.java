package com.sh.vhr.mapper;

import com.sh.vhr.model.Oplog;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OplogMapper{
    List<Oplog> getAllOplogs();
}