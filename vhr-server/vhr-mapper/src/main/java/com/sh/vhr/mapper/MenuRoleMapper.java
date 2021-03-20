package com.sh.vhr.mapper;

import com.sh.vhr.model.MenuRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MenuRoleMapper{
    List<MenuRole> getAllMenuRoles();

    Integer deleteByRoleId(Integer roleId);

    Integer insertRecord(Integer roleId, Integer[] menuIds);
}