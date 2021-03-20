package com.sh.vhr.mapper;

import com.sh.vhr.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> getMenusByHrId(Integer hrId);

    List<Menu> getAllMenuWithRole();

    List<Menu> getAllMenus();

    List<Menu> getMenusByParentId(Integer parentId);

    List<Integer> getMenuIdsByRoleId(Integer roleId);
}