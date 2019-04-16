package com.kyle.base.resource.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kyle.base.resource.entity.SysResource;
import com.kyle.utils.ServiceException;

public interface ResourceService {
	
	Map<String,Object> getResourceTree(Map<String,Object> param);
	
	List<SysResource> getResourceByUserId(Integer userId);

	void addResouce(Map<String, Object> param) throws ServiceException;

	void updateResouce(Map<String, Object> param) throws ServiceException;

	void deleteResouce(Map<String, Object> param) throws ServiceException;
	
	Map<String, Set<Integer>> getPermissionResourceByUserId(Integer userId);
	
}
