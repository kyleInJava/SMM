package com.kyle.base.dept.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.kyle.base.dept.dao.SysDeptMapper;
import com.kyle.base.dept.entity.SysDept;
import com.kyle.base.dept.service.DeptService;
import com.kyle.utils.CommonUtil;
import com.kyle.utils.DeptUtil;
import com.kyle.utils.ServiceException;
import com.kyle.utils.StateInfo;
import com.kyle.utils.UserUtil;

@Service
public class DeptServiceImpl implements DeptService {

	@Resource
	private SysDeptMapper sysDeptMapper;
	
	@Override
	public Map<String, Object> getDeptTree(Map<String,Object> param) {
		List<SysDept> deptList = sysDeptMapper.getDeptList(param);
		List<SysDept> deptTree = DeptUtil.buildDeptTree(deptList);
		Map<String,Object> res = new HashMap<>();
		res.put("datalist", deptTree);
		return res;
	}

	@Override
	public void addDept(Map<String, Object> param) throws ServiceException {
		String name = CommonUtil.CheckData(param.get("name"), String.class, StateInfo.name_must_not_null);
		Integer sort = CommonUtil.CheckData(param.get("sort"), Integer.class, StateInfo.sort_must_not_null);
		Integer parentId = CommonUtil.CheckData(param.get("parentId"), Integer.class, StateInfo.parentId_must_not_null);
		Integer type = CommonUtil.CheckData(param.get("type"), Integer.class, StateInfo.type_must_not_null);
		
		
		//先通过parentId来找到上级部门
		SysDept parentDept = sysDeptMapper.selectByPrimaryKey(parentId);
		
		SysDept dept = new SysDept();
		dept.setName(name);
		dept.setSort(sort);
		dept.setParentId(parentId);
		dept.setPath("*");//先占位，后面更新
		dept.setType(type);
		dept.setState(0);//初始化默认正常
		dept.setUpdater(UserUtil.currentUser().getId());
		dept.setUpdateTime(new Date());
		
		sysDeptMapper.insertSelective(dept);
		dept.setPath(parentDept.getPath()+dept.getId()+"/");
		sysDeptMapper.updateByPrimaryKeySelective(dept);
		
		//更新servletContext中的部门信息
		List<SysDept> depts = sysDeptMapper.getAllDept();
		DeptUtil.updateServletContextDept(depts);
		
	}

	@Override
	public void updateDept(Map<String, Object> param) throws ServiceException {
		Integer deptId = CommonUtil.CheckData(param.get("id"), Integer.class, StateInfo.type_must_not_null);
		SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
		
		String name = CommonUtil.CheckData(param.get("name"), String.class, StateInfo.name_must_not_null);
		Integer sort = CommonUtil.CheckData(param.get("sort"), Integer.class, StateInfo.sort_must_not_null);
		Integer parentId = CommonUtil.CheckData(param.get("parentId"), Integer.class, StateInfo.parentId_must_not_null);
		Integer type = CommonUtil.CheckData(param.get("type"), Integer.class, StateInfo.type_must_not_null);
		
		if(!parentId.equals(dept.getParentId())){//如果变动了上级部门
			SysDept parentDept = sysDeptMapper.selectByPrimaryKey(parentId);
			dept.setParentId(parentId);
			dept.setPath(parentDept.getPath()+dept.getId()+"/");
		}
		dept.setName(name);
		dept.setType(type);
		dept.setSort(sort);
		dept.setUpdater(UserUtil.currentUser().getId());
		dept.setUpdateTime(new Date());
		sysDeptMapper.updateByPrimaryKeySelective(dept);
		
		//更新servletContext中的部门信息
		List<SysDept> depts = sysDeptMapper.getAllDept();
		DeptUtil.updateServletContextDept(depts);
	}

	@Override
	public void deleteDept(Map<String, Object> param) throws ServiceException {
		Integer deptId = CommonUtil.CheckData(param.get("id"), Integer.class, StateInfo.type_must_not_null);
		SysDept dept = sysDeptMapper.selectByPrimaryKey(deptId);
		dept.setState(1);
		dept.setUpdater(UserUtil.currentUser().getId());
		dept.setUpdateTime(new Date());
		sysDeptMapper.updateByPrimaryKeySelective(dept);
		
		//更新servletContext中的部门信息
		List<SysDept> depts = sysDeptMapper.getAllDept();
		DeptUtil.updateServletContextDept(depts);
	}
	
	
}
