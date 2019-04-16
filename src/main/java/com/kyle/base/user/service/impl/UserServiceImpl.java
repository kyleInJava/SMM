package com.kyle.base.user.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.kyle.base.role.dao.SysRoleMapper;
import com.kyle.base.user.dao.SysUserMapper;
import com.kyle.base.user.entity.SysUser;
import com.kyle.base.user.service.UserService;
import com.kyle.utils.CommonUtil;
import com.kyle.utils.DeptUtil;
import com.kyle.utils.PageUtil;
import com.kyle.utils.ServiceException;
import com.kyle.utils.StateInfo;
import com.kyle.utils.UserUtil;

@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource 
	private HttpServletRequest request;

	@Override
	public Map<String,Object> listUser(Map<String,Object> paraMap) throws ServiceException {
		
		Map<String, Object> resultMap = new HashMap<>();
		//初始化查询页面
		PageUtil.startpage(paraMap);
		List<Map<String,Object>> userList = sysUserMapper.listUser(paraMap);
		//包装分页数据
		PageUtil.packPageResult(resultMap, userList);
		Set<Integer> depts = DeptUtil.getRequestPermissionDept();
		System.out.println(depts);
		return resultMap;
	}

	@Override
	public void addUser(Map<String, Object> paraMap) throws ServiceException {
		
		SysUser user = new SysUser();
		String account = CommonUtil.CheckData(paraMap.get("account"), String.class, StateInfo.account_must_not_null);
		String password = CommonUtil.CheckData(paraMap.get("password"), String.class, StateInfo.password_must_not_null);
		String name = CommonUtil.CheckData(paraMap.get("name"), String.class, StateInfo.name_must_not_null);
		Integer age = CommonUtil.CheckData(paraMap.get("age"), Integer.class, StateInfo.age_must_not_null);
		Integer gender = CommonUtil.CheckData(paraMap.get("gender"), Integer.class, StateInfo.gender_must_not_null);
		Integer dept = CommonUtil.CheckData(paraMap.get("dept"), Integer.class, StateInfo.dept_must_not_null);
		Integer job = CommonUtil.CheckData(paraMap.get("job"), Integer.class, StateInfo.job_must_not_null);
		//生成4位数的盐
		String salt = CommonUtil.RandomStringGenerator(4);
		//对密码进行加盐后加密
		password = CommonUtil.MD5(account+password+salt);

		user.setAccount(account);
		user.setPassword(password);
		user.setSalt(salt);
		user.setName(name);
		user.setAge(age);
		user.setGender(gender);
		user.setDept(dept);
		user.setJob(job);
		user.setUpdater(UserUtil.currentUser().getId());
		user.setUpdateTime(new Date());
		
		sysUserMapper.insertSelective(user);
	}

	@Override
	public Map<String, Object> getUserBasicInfo(Map<String, Object> param) throws ServiceException {
		//校验字段
		CommonUtil.CheckData(param.get("userId"), Integer.class, StateInfo.userId_must_not_null);
		Map<String, Object> userInfo = sysUserMapper.getUserBasicInfo(param);
		return userInfo;
	}

	@Override
	public SysUser getUserById(Integer userId) {
		SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public void updateUserInfo(Map<String, Object> paraMap) throws ServiceException {
		
		Integer userId = CommonUtil.CheckData(paraMap.get("id"), Integer.class, StateInfo.userId_must_not_null);
		String name = CommonUtil.CheckData(paraMap.get("name"), String.class, StateInfo.name_must_not_null);
		Integer age = CommonUtil.CheckData(paraMap.get("age"), Integer.class, StateInfo.age_must_not_null);
		Integer gender = CommonUtil.CheckData(paraMap.get("gender"), Integer.class, StateInfo.gender_must_not_null);
		Integer dept = CommonUtil.CheckData(paraMap.get("dept"), Integer.class, StateInfo.dept_must_not_null);
		Integer job = CommonUtil.CheckData(paraMap.get("job"), Integer.class, StateInfo.job_must_not_null);
		
		
		SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		user.setName(name);
		user.setAge(age);
		user.setGender(gender);
		user.setDept(dept);
		user.setJob(job);
		user.setUpdater(UserUtil.currentUser().getId());
		user.setUpdateTime(new Date());
		
		sysUserMapper.updateByPrimaryKeySelective(user);
		
	}

	@Override
	public void changePwd(Map<String, Object> param) throws ServiceException {
		
		Integer userId = CommonUtil.CheckData(param.get("id"), Integer.class, StateInfo.userId_must_not_null);
		String password = CommonUtil.CheckData(param.get("password"), String.class, StateInfo.password_must_not_null);
		SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		String account = user.getAccount();
		String salt = user.getSalt();
		password = CommonUtil.MD5(account+password+salt);//生成新的 加密密码
		user.setPassword(password);
		
		sysUserMapper.updateByPrimaryKeySelective(user);
		
	}

	@Override
	public void changeState(Map<String, Object> param) {
		Integer userId = Integer.parseInt(param.get("id").toString());
		Integer state = Integer.parseInt(param.get("state").toString());
		SysUser user = sysUserMapper.selectByPrimaryKey(userId);
		user.setState(state);
		sysUserMapper.updateByPrimaryKeySelective(user);
	}

	
}
