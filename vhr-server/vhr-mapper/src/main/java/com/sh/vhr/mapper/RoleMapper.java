package com.sh.vhr.mapper;

import com.sh.vhr.model.Role;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RoleMapper{
    List<Role> getAllRoles();

    Integer addRole(Role role);

    Integer deleteRoleById(Integer roleId);
}