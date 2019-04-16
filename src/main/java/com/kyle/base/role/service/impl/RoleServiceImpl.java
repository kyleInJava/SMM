package com.kyle.base.role.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kyle.base.role.dao.SysRoleMapper;
import com.kyle.base.role.entity.SysRole;
import com.kyle.base.role.service.RoleService;
import com.kyle.utils.CommonUtil;
import com.kyle.utils.PageUtil;
import com.kyle.utils.ServiceException;
import com.kyle.utils.StateInfo;
import com.kyle.utils.UserUtil;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public Map<String, Object> listRole(Map<String, Object> param) throws ServiceException {
		Map<String,Object> resultMap = new HashMap<>();
		PageUtil.startpage(param);
		List<SysRole> list = sysRoleMapper.listSysRole();
		PageUtil.packPageResult(resultMap, list);
		return resultMap;
	}
	
	@Override
	public List<SysRole> getUserRoleByUserId(Integer userId) {
		return sysRoleMapper.getRolesByUserId(userId);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void add(Map<String, Object> param) throws ServiceException {
		
		String code = CommonUtil.CheckData(param.get("code"), String.class, StateInfo.code_must_not_null);
		String name = CommonUtil.CheckData(param.get("name"), String.class, StateInfo.name_must_not_null);
		Integer dataPermission = CommonUtil.CheckData(param.get("dataPermission"), Integer.class, StateInfo.dataPermission_must_not_null);
		String note = CommonUtil.CheckData(param.get("note"), String.class);
		
		SysRole role = new SysRole();
		role.setCode(code);
		role.setName(name);
		role.setState(0);//新增的 时候默认是正常状态
		role.setDataPermission(dataPermission);
		role.setNote(note);
		role.setUpdater(UserUtil.currentUser().getId());
		role.setUdpateTime(new Date());
		sysRoleMapper.insertSelective(role);
		
		//如果在新增角色的时候选择了权限
		if(param.get("resources") != null){
			List<Integer> resources = (List<Integer>)param.get("resources");
			if(resources.size() > 0){
				param.put("role_id", role.getId());
				sysRoleMapper.insertRoleResource(param);
			}
		}
	}

	@Override
	public Map<String, Object> getRole(Map<String, Object> param) throws ServiceException {
		Integer roleId = CommonUtil.CheckData(param.get("id"), Integer.class, StateInfo.id_must_not_null);
		SysRole role = sysRoleMapper.selectByPrimaryKey(roleId);
		List<Integer> resources =  sysRoleMapper.selectResourceByRoleId(roleId);
		Map<String,Object> result = new HashMap<>();
		result.put("role", role);
		result.put("resource", resources);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateRole(Map<String, Object> param) throws ServiceException {
		Integer roleId = CommonUtil.CheckData(param.get("id"), Integer.class, StateInfo.id_must_not_null);
		String code = CommonUtil.CheckData(param.get("code"), String.class, StateInfo.code_must_not_null);
		String name = CommonUtil.CheckData(param.get("name"), String.class, StateInfo.name_must_not_null);
		Integer dataPermission = CommonUtil.CheckData(param.get("dataPermission"), Integer.class, StateInfo.dataPermission_must_not_null);
		String note = CommonUtil.CheckData(param.get("note"), String.class);
		
		String state = param.get("state").toString();
		SysRole role = sysRoleMapper.selectByPrimaryKey(roleId);
		role.setCode(code);
		role.setName(name);
		role.setNote(note);
		role.setState(Integer.parseInt(state));
		role.setDataPermission(dataPermission);
		role.setUpdater(UserUtil.currentUser().getId());
		role.setUdpateTime(new Date());
		
		sysRoleMapper.updateByPrimaryKeySelective(role);
		//删除对应的菜单
		param.put("role_id", roleId);
		sysRoleMapper.deleteRoleResource(param);
		//对角色对应的权限进行处理
		if(param.get("resources") != null){
			List<Integer> resources = (List<Integer>)param.get("resources");
			if(resources.size() > 0){
				sysRoleMapper.insertRoleResource(param);
			}
		}
	}

	@Override
	public void deleteRole(Map<String, Object> param) throws ServiceException {
		Integer roleId = CommonUtil.CheckData(param.get("id"), Integer.class, StateInfo.id_must_not_null);
		SysRole role = sysRoleMapper.selectByPrimaryKey(roleId);
		role.setState(1);
		role.setUpdater(UserUtil.currentUser().getId());
		role.setUdpateTime(new Date());
		sysRoleMapper.updateByPrimaryKeySelective(role);
		param.put("role_id", roleId);
		sysRoleMapper.deleteRoleResource(param);
	}

}
