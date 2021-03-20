package com.sh.vhr.mapper;

import com.sh.vhr.model.JobLevel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface JobLevelMapper {
    List<JobLevel> getAllJobLevels();

    JobLevel getJobLevelById(Integer id);
}