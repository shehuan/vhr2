package com.sh.vhr.service;

import com.sh.vhr.mapper.NationMapper;
import com.sh.vhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;

    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
