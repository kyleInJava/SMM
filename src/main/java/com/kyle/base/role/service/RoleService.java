package com.kyle.base.role.service;

import java.util.List;
import java.util.Map;

import com.kyle.base.role.entity.SysRole;
import com.kyle.utils.ServiceException;

public interface RoleService {

	Map<String,Object> listRole(Map<String,Object> param) throws ServiceException;
	
	List<SysRole> getUserRoleByUserId(Integer userId);

	void add(Map<String, Object> param) throws ServiceException;

	Map<String, Object> getRole(Map<String, Object> param) throws ServiceException;

	void updateRole(Map<String, Object> param) throws ServiceException;

	void deleteRole(Map<String, Object> param) throws ServiceException;
}
