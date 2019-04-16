package com.kyle.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kyle.base.dept.entity.SysDept;

/**
 * 部门树构建相关的工具类
 * @author kyle
 *
 */
public class DeptUtil {

	//构建部门树
	public static List<SysDept> buildDeptTree(List<SysDept> list){
		//由于可能出现多个树，所以先将所有的根节点找出来然后进行树的构建
		List<SysDept> topNodes = findTopNodes(list);
		if(topNodes.isEmpty()){
			return topNodes;
		}
		
		//构建部门树
		for(SysDept dept : topNodes){
			buildDeptTree(list,dept);
		}
		
		return topNodes;
	}
	
	//找到顶级节点
	private static List<SysDept> findTopNodes(List<SysDept> list){
		List<SysDept> topNodes = new LinkedList<SysDept>();
		if(list == null || list.isEmpty()){
			return topNodes;
		}
		
		for(SysDept dept : list){
			boolean isTopNode = true;//判断该节点是否为最上级节点
			for(SysDept tDept : topNodes){
				if(dept.getPath().contains(tDept.getPath())){
					isTopNode = false; //如果当前节点的父节点存在，则说明该节点不是最上级节点
				}
			}
			if(isTopNode == true){
				topNodes.add(dept);
			}
		}
		
		return topNodes;
	}
	
	/**
	 * 递归构建部门树
	 * @param list 已经排好序的部门列表
	 * @param pDept 父节点
	 * @return
	 */
	private static void buildDeptTree(List<SysDept> list,SysDept pDept){
		
		for(SysDept dept : list){
			if(dept.getParentId().intValue() == pDept.getId().intValue()){
				pDept.getChildren().add(dept);
				buildDeptTree(list,dept);
			}
		}
	}
	
	/**
	 * 根据部门id查询所有子部门id
	 * @param deptId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> getSubDeptId(Integer deptId){
		String dept_str = "/"+deptId+"/";
		List<Integer> deptIds = new ArrayList<>();
		ServletContext serveltContext = UserUtil.getServeltContext();
		List<SysDept> depts = (List<SysDept>)serveltContext.getAttribute(Constant.ALL_DEPT);
		for(SysDept dept : depts ){
			if(dept.getPath().contains(dept_str)){
				deptIds.add(dept.getId());
			}
		}
		return deptIds;
	}
	
	/**
	 * 获取所有的部门id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Integer> getAllDeptId(){
		ServletContext serveltContext = UserUtil.getServeltContext();
		List<SysDept> depts = (List<SysDept>)serveltContext.getAttribute(Constant.ALL_DEPT);
		List<Integer> deptIds = new ArrayList<>();
		for(SysDept dept : depts ){
			deptIds.add(dept.getId());
		}
		return deptIds;

	}
	
	/**
	 * 获取当前用户的当前请求的权限部门
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Set<Integer> getRequestPermissionDept(){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		Object attribute = request.getAttribute(Constant.PERMISSION_DEPTS);
		Set<Integer> depts = null;
		if(attribute != null){
			depts = (Set<Integer>)attribute;
		}
		return depts;
	}
	
	/**
	 * 更新servletContext中的部门信息
	 * @param depts
	 */
	public static void updateServletContextDept(List<SysDept> depts){
		ServletContext servletContext = UserUtil.getServeltContext();
		servletContext.setAttribute(Constant.ALL_DEPT, depts);
	}
}
