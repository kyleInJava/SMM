package com.kyle.base.login.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.kyle.base.login.service.LoginService;
import com.kyle.base.resource.dao.SysResourceMapper;
import com.kyle.base.resource.entity.SysResource;
import com.kyle.base.resource.service.ResourceService;
import com.kyle.base.role.dao.SysRoleMapper;
import com.kyle.base.role.entity.SysRole;
import com.kyle.base.user.dao.SysUserMapper;
import com.kyle.base.user.entity.SysUser;
import com.kyle.utils.CommonUtil;
import com.kyle.utils.Constant;
import com.kyle.utils.ServiceException;
import com.kyle.utils.StateInfo;
import com.kyle.utils.UserUtil;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SysResourceMapper sysResourceMapper;
	@Resource
	private ResourceService resourceService;
	
	@Override
	public Map<String, Object> login(Map<String, Object> param) throws ServiceException{
		String account = CommonUtil.CheckData(param.get("account"), String.class, StateInfo.account_must_not_null);
		String password = CommonUtil.CheckData(param.get("password"), String.class, StateInfo.password_must_not_null);
		
		//通过账号找人
		SysUser user = userMapper.getUserByAccount(account);
		//如果人不存在，则提示人不存在
		if(user == null) {
			throw new ServiceException(StateInfo.account_pwd_error);
		}
		//用盐、账号、密码来组成新的字符串
		String salt = user.getSalt();
		String tempWord = account+password+salt;
		
		//输入的密码加盐加密后的字符串
		String inPassword = CommonUtil.MD5(tempWord);
		String outPassword = user.getPassword();
		if(!inPassword.equals(outPassword)){
			throw new ServiceException(StateInfo.account_pwd_error);
		}
		
		//通过验证后查询当前登录人的信息，并保存到session中
		Integer userId = user.getId();
		Map<String, Object> userInfo = new HashMap<>();
		
		List<SysRole> roles = sysRoleMapper.getRolesByUserId(userId);
		//查询当前用户的所有资源，包括有权限的和不需要权限的资源
		List<SysResource> resources = sysResourceMapper.getResourceByUserId(userId);
		
		//将一些不必要的信息去除掉，然后保存到session中，并传给前台
		user.setPassword(null);
		user.setSalt(null);
		user.setUpdater(null);
		user.setUpdateTime(null);
		userInfo.put(Constant.USER, user);
		userInfo.put(Constant.ROLE, roles);
		userInfo.put(Constant.RESOURCE, resources);
		
		//登录的时候查询当前用户的资源与对应部门之间的关系，并保存到session中，为以后的数据权限作准备
		Map<String, Set<Integer>> permissionResource = resourceService.getPermissionResourceByUserId(userId);
		//将基础数据保存在session中
		HttpSession session = UserUtil.getSession(true);
		session.setAttribute(Constant.USER, user);
		session.setAttribute(Constant.ROLE, roles);
		session.setAttribute(Constant.RESOURCE, resources);
		session.setAttribute(Constant.RESOURCE_DEPTS, permissionResource);
		
		return userInfo;
	}

	
	@Override
	public Map<String, Object> logout(Map<String, Object> param) {
		HttpSession session = UserUtil.getSession(false);
		session.invalidate();//直接设置session失效
		return null;
	}
	
	

}
