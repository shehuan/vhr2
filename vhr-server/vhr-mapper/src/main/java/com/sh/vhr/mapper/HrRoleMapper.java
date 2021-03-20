package com.sh.vhr.mapper;

import com.sh.vhr.model.HrRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HrRoleMapper{
    List<HrRole> getAllHrRoles();

    Integer deleteRoleByHrId(Integer hrid);

    Integer insertRecord(@Param("hrid") Integer hrid, @Param("roleIds") Integer[] roleIds);
}