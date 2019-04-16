package com.kyle.base.user.service;

import java.util.Map;

import com.kyle.base.user.entity.SysUser;
import com.kyle.utils.ServiceException;

public interface UserService {

	Map<String,Object> listUser(Map<String,Object> paraMap) throws ServiceException;
	
	void addUser(Map<String,Object> paraMap) throws ServiceException;

	Map<String,Object> getUserBasicInfo(Map<String, Object> param) throws ServiceException;

	SysUser getUserById(Integer userId);

	void updateUserInfo(Map<String, Object> param) throws ServiceException;

	void changePwd(Map<String, Object> param) throws ServiceException;

	void changeState(Map<String, Object> param) throws ServiceException;

}
