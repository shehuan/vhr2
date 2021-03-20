package com.sh.vhr.mapper;

import com.sh.vhr.model.Msgcontent;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MsgcontentMapper{
    List<Msgcontent> getAllMsgcontents();
}