package com.kyle.base.resource.dao;

import java.util.List;
import java.util.Map;

import com.kyle.base.resource.entity.SysResource;

public interface SysResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResource record);

	List<SysResource> listResource(Map<String, Object> param);

	List<SysResource> getResourceByUserId(Integer userId);

	List<SysResource> getNoPermissionsRequiredResource();
	
	List<Map<String, Object>> getPermissionResourceByUserId(Integer userId);

}