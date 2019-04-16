package com.kyle.base.role.dao;

import java.util.List;
import java.util.Map;

import com.kyle.base.role.entity.SysRole;

public interface SysRoleMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);
    
    List<SysRole> listSysRole();

	List<SysRole> getRolesByUserId(Integer userId);

	void insertRoleResource(Map<String, Object> param);

	List<Integer> selectResourceByRoleId(Integer roleId);

	void deleteRoleResource(Map<String, Object> param);

}