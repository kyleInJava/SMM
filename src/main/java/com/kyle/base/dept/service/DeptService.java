package com.kyle.base.dept.service;

import java.util.Map;

import com.kyle.utils.ServiceException;

public interface DeptService {

	Map<String,Object> getDeptTree(Map<String,Object> param);

	void addDept(Map<String, Object> param) throws ServiceException;

	void updateDept(Map<String, Object> param) throws ServiceException;

	void deleteDept(Map<String, Object> param) throws ServiceException;
}
