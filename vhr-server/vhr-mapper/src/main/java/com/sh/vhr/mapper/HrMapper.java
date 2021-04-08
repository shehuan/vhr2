package com.sh.vhr.mapper;

import com.sh.vhr.model.Hr;
import com.sh.vhr.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HrMapper {
    Hr findUserByUsername(String username);

    List<Role> findRolesByUserId(Integer hrId);

    List<Hr> getAllHrs(@Param("id") Integer id, @Param("keyword") String keyword);

    Integer updateHr(Hr hr);

    Integer deleteHrById(Integer hrid);

    List<Hr> getAllHrsExceptCurrentHr(Integer id);

    Hr findHrById(Integer hrid);

    int updatePassword(@Param("hrid") Integer hrid, @Param("encodePass") String encodePass);
}