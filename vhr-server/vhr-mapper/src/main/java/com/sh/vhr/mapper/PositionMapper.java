package com.sh.vhr.mapper;

import com.sh.vhr.model.Position;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PositionMapper {
    List<Position> getAllPositions();

    int addPosition(Position position);

    int updatePosition(Position position);

    Integer deletePosition(Integer id);

    Integer deletePositions(Integer[] ids);

    Position getPositionById(Integer id);
}