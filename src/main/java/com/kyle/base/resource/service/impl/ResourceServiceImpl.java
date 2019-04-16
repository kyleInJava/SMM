package com.kyle.base.resource.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.kyle.base.resource.dao.SysResourceMapper;
import com.kyle.base.resource.entity.SysResource;
import com.kyle.base.resource.service.ResourceService;
import com.kyle.utils.CommonUtil;
import com.kyle.utils.Constant;
import com.kyle.utils.DeptUtil;
import com.kyle.utils.ServiceException;
import com.kyle.utils.StateInfo;
import com.kyle.utils.UserUtil;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Resource
	private SysResourceMapper sysResourceMapper;
	@Resource
	private ServletContext servletContext;
	@Resource
	private HttpSession session;
	
	@Override
	public Map<String, Object> getResourceTree(Map<String, Object> param) {
		Map<String,Object> resultMap = new HashMap<>();
		List<SysResource> resources = sysResourceMapper.listResource(param);
		List<SysResource> resourceTree = BuildResourceTree(resources);
		resultMap.put(Constant.DATA_LIST, resourceTree);
		return resultMap;
	}
	
	//构建资源树
	private List<SysResource> BuildResourceTree(List<SysResource> resources){
		List<SysResource> list = new LinkedList<SysResource>();
		for(SysResource resource : resources){
			if(resource.getType() == 0 && resource.getParentId() == 0){//如果当前资源是目录，就要放在列表中用递归构建树
				list.add(resource);
				BuildResourceTree(resources,resource);
			}
		}
		return list;
	}
	
	//递归构建树
	private void BuildResourceTree(List<SysResource> resources,SysResource resource){
		for(SysResource r : resources){
			if(r.getParentId() == resource.getId()){
				resource.getChildren().add(r);
				BuildResourceTree(resources,r);
			}
		}
	}
	
	@Override
	public List<SysResource> getResourceByUserId(Integer userId) {
		List<SysResource> resources = sysResourceMapper.getResourceByUserId(userId);
		return resources;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addResouce(Map<String, Object> param) throws ServiceException {
		String name = CommonUtil.CheckData(param.get("name"), String.class, StateInfo.name_must_not_null);
		Integer type = CommonUtil.CheckData(param.get("type"), Integer.class, StateInfo.type_must_not_null);
		Integer parentId = CommonUtil.CheckData(param.get("parentId"), Integer.class, StateInfo.parentId_must_not_null);
		Integer sort = CommonUtil.CheckData(param.get("sort"), Integer.class, StateInfo.sort_must_not_null);
		String url = CommonUtil.CheckData(param.get("url"), String.class);
		String requestUrl = CommonUtil.CheckData(param.get("requestUrl"), String.class);
		String note = CommonUtil.CheckData(param.get("note"), String.class);
		Integer needPermission = CommonUtil.CheckData(param.get("needPermission"), Integer.class, StateInfo.needPermission_must_not_null);
		
		SysResource resource = new SysResource();
		resource.setName(name);
		resource.setType(type);
		resource.setParentId(parentId);
		resource.setUrl(url);
		resource.setRequestUrl(requestUrl);
		resource.setNote(note);
		resource.setState(0);//初始默认正常
		resource.setSort(sort);
		resource.setNeedPermission("1".equals(needPermission)?true:false);
		resource.setUpdater(UserUtil.currentUser().getId());
		resource.setUpdateTime(new Date());
		sysResourceMapper.insertSelective(resource);
		
		if(resource.getNeedPermission() == false){//如果添加的资源needPermission字段为无需权限，那么就要更新servletContext中的NoPermissionsRequiredResource名单
			List<SysResource> NoPermissionsResource = (List<SysResource>)servletContext.getAttribute("NoPermissionsRequiredResource");
			NoPermissionsResource.add(resource);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateResouce(Map<String, Object> param) throws ServiceException {
		Integer resourceId = CommonUtil.CheckData(param.get("id"), Integer.class, StateInfo.id_must_not_null);
		SysResource resource = sysResourceMapper.selectByPrimaryKey(resourceId);
		Boolean needPermission2 = resource.getNeedPermission();
		
		String name = CommonUtil.CheckData(param.get("name"), String.class, StateInfo.name_must_not_null);
		Integer type = CommonUtil.CheckData(param.get("type"), Integer.class, StateInfo.type_must_not_null);
		Integer parentId = CommonUtil.CheckData(param.get("parentId"), Integer.class, StateInfo.parentId_must_not_null);
		Integer sort = CommonUtil.CheckData(param.get("sort"), Integer.class, StateInfo.sort_must_not_null);
		String url = CommonUtil.CheckData(param.get("url"), String.class);
		String requestUrl = CommonUtil.CheckData(param.get("requestUrl"), String.class);
		String note = CommonUtil.CheckData(param.get("note"), String.class);
		Integer needPermission = CommonUtil.CheckData(param.get("needPermission"), Integer.class, StateInfo.needPermission_must_not_null);
		
		resource.setName(name);
		resource.setType(type);
		resource.setParentId(parentId);
		resource.setUrl(url);
		resource.setRequestUrl(requestUrl);
		resource.setNote(note);
		resource.setSort(sort);
		resource.setNeedPermission("1".equals(needPermission)?true:false);
		resource.setUpdater(UserUtil.currentUser().getId());
		resource.setUpdateTime(new Date());
		sysResourceMapper.updateByPrimaryKeySelective(resource);
		
		//如果修改了资源needPermission字段，那么就要更新servletContext中的NoPermissionsRequiredResource名单
		if(resource.getNeedPermission() == false && needPermission2 == true){
			List<SysResource> NoPermissionsResource = (List<SysResource>)servletContext.getAttribute("NoPermissionsRequiredResource");
			NoPermissionsResource.add(resource);
		}else if(resource.getNeedPermission() == true && needPermission2 == false){
			List<SysResource> NoPermissionsResource = (List<SysResource>)servletContext.getAttribute("NoPermissionsRequiredResource");
			Iterator<SysResource> iterator = NoPermissionsResource.iterator();
			while(iterator.hasNext()){
				SysResource next = iterator.next();
				if(next.getId().intValue() == resource.getId().intValue()){
					iterator.remove();
					break;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteResouce(Map<String, Object> param) throws ServiceException {
		Integer resourceId = CommonUtil.CheckData(param.get("id"), Integer.class, StateInfo.id_must_not_null);
		SysResource resource = sysResourceMapper.selectByPrimaryKey(resourceId);
		resource.setState(1);
		resource.setUpdater(UserUtil.currentUser().getId());
		resource.setUpdateTime(new Date());
		sysResourceMapper.updateByPrimaryKeySelective(resource);
		
		if(resource.getNeedPermission() ==  false){
			List<SysResource> NoPermissionsResource = (List<SysResource>)servletContext.getAttribute("NoPermissionsRequiredResource");
			Iterator<SysResource> iterator = NoPermissionsResource.iterator();
			while(iterator.hasNext()){
				SysResource next = iterator.next();
				if(next.getId().intValue() == resource.getId().intValue()){
					iterator.remove();
					break;
				}
			}
		}
	}

	@Override
	public Map<String, Set<Integer>> getPermissionResourceByUserId(Integer userId) {
		List<Map<String, Object>> permissionResources = sysResourceMapper.getPermissionResourceByUserId(userId);
		Map<String,Set<Integer>> res = new HashMap<>();
		for(Map<String,Object> m :permissionResources){
			if(m.get("request_url") != null){
				String request_url = m.get("request_url").toString();
				Integer deptId = Integer.parseInt(m.get("dept_id").toString());
				Integer data_permission = Integer.parseInt(m.get("data_permission").toString());
				Set<Integer> deptIds = null;
				if(res.containsKey(request_url)){
					deptIds = res.get(request_url);
				}else{
					deptIds = new HashSet<>();
				}
				
				if(data_permission == 0){//数据权限为该部门及其子部门
					List<Integer> subDeptIds = DeptUtil.getSubDeptId(deptId);
					deptIds.addAll(subDeptIds);
				}else if(data_permission == 1){//数据权限为该部门
					deptIds.add(deptId);
				}
				
				res.put(request_url, deptIds);
			}
		}
		return res;
	}

}
