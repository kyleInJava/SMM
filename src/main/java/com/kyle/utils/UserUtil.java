package com.kyle.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kyle.base.resource.entity.SysResource;
import com.kyle.base.role.entity.SysRole;
import com.kyle.base.user.entity.SysUser;

/**
 * 获取当前用户相关的一些信息
 * @author kyle
 *
 */
public class UserUtil {
	
	/**
	 * 获取session
	 * @return
	 */
	public static HttpSession getSession(boolean create){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpSession session = requestAttributes.getRequest().getSession(create);
		return session;
	}
	
	/**
	 * 获取servlertContext来缓存全局的数据
	 * @return
	 */
	public static ServletContext getServeltContext(){
		ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		return servletContext;
	}
	
	
	/**
	 * 获取当前登录用户的基本信息
	 * @return
	 */
	public static SysUser currentUser(){
		SysUser user = null;
		HttpSession session = getSession(false);
		Object object = session.getAttribute(Constant.USER);
		if(object != null){
			user = (SysUser)object;
		}
		return user;
	}
	
	/**
	 * 获取当前登录人的角色
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SysRole> getCurrentUserRole(){
		List<SysRole> roles = new ArrayList<>();
		HttpSession session = getSession(false);
		Object o = session.getAttribute(Constant.ROLE);
		if(o != null){
			roles = (List<SysRole>) o;
		}
		return roles;
	}
	
	/**
	 * 获取当前登录人员被允许访问的资源有哪些
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SysResource> getCurrentUserPermittedResource(){
		List<SysResource> resources  = new ArrayList<>();
		HttpSession session = getSession(false);
		Object o = session.getAttribute(Constant.RESOURCE);
		if( o != null){
			resources = (List<SysResource>) o;
		}
		
		return resources;
	}
	
	/**
	 * 获取当前登录人员的资源权限和部门之间的关系
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Set<Integer>> getCurrentUserResourceDeptInfo(){
		Map<String,Set<Integer>> res = new HashMap<>();
		HttpSession session = getSession(false);
		Object o = session.getAttribute(Constant.RESOURCE_DEPTS);
		if( o != null){
			res = (Map<String,Set<Integer>>) o;
		}
		return res;
	}
}
