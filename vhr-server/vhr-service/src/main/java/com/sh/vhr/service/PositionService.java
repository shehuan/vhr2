package com.sh.vhr.service;

import com.sh.vhr.mapper.PositionMapper;
import com.sh.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    public Integer addPosition(Position position) {
        position.setEnabled(true);
        position.setCreateDate(new Date());
        return positionMapper.addPosition(position);
    }

    public Integer updatePosition(Position position) {
        return positionMapper.updatePosition(position);
    }

    public Integer deletePosition(Integer id){
        return positionMapper.deletePosition(id);
    }

    public Integer deletePositions(Integer[] ids){
        return positionMapper.deletePositions(ids);
    }
}
