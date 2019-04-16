package com.kyle.base.user.dao;

import java.util.List;
import java.util.Map;

import com.kyle.base.user.entity.SysUser;

public interface SysUserMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

	List<Map<String, Object>> listUser(Map<String, Object> paraMap);

	SysUser getUserByAccount(String account);

	Map<String,Object> getUserBasicInfo(Map<String, Object> param);

}