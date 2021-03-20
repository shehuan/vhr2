package com.sh.vhr.service;

import com.sh.vhr.mapper.PoliticsstatusMapper;
import com.sh.vhr.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoliticsstatusService {
    @Autowired
    PoliticsstatusMapper politicsstatusMapper;

    public List<Politicsstatus> getAllPoliticsstatus(){
        return politicsstatusMapper.getAllPoliticsstatuss();
    }
}
