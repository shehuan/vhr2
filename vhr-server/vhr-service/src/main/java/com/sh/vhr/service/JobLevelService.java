package com.sh.vhr.service;

import com.sh.vhr.mapper.JobLevelMapper;
import com.sh.vhr.model.JobLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobLevelService {
    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevels(){
        return jobLevelMapper.getAllJobLevels();
    }
}
