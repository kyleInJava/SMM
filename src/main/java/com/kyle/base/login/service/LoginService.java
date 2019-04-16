package com.kyle.base.login.service;

import java.util.Map;

import com.kyle.utils.ServiceException;

public interface LoginService {

	Map<String,Object> login(Map<String, Object> param) throws ServiceException;

	Map<String, Object> logout(Map<String, Object> param);
	

}
