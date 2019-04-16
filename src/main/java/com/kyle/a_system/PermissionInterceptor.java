package com.kyle.a_system;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kyle.base.resource.entity.SysResource;
import com.kyle.base.role.entity.SysRole;
import com.kyle.utils.Constant;
import com.kyle.utils.DeptUtil;
import com.kyle.utils.StateInfo;
import com.kyle.utils.UserUtil;

public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		//获取请求的路径
		String pathInfo = request.getServletPath();
		//如果是登录请求不作任何处理,这里其实可以设置一个白名单不需要登录也可以进行访问
		if("/login/login".equalsIgnoreCase(pathInfo)){
			return true;
		}else{//需要登录才能进行访问
			
			//如果没有登录
			if(request.getSession(false) == null){
				buildRes(response,StateInfo.not_login);
				return false;
			}
			
			//判断当前用户是不是超级管理员
			List<SysRole> roles = UserUtil.getCurrentUserRole();
			for(SysRole role : roles){
				if("9999".equals(role.getCode())){//如果是超级管理员会无需校验
					request.setAttribute(Constant.PERMISSION_DEPTS, DeptUtil.getAllDeptId());
					return true;
				}
			}
			
			//登录后查询当前登录人的权限
			List<SysResource> resources = UserUtil.getCurrentUserPermittedResource();
			for(SysResource resource : resources){
				if(pathInfo.equals(resource.getRequestUrl())){//如果具有访问权限
					Map<String, Set<Integer>> resourceDeptInfo = UserUtil.getCurrentUserResourceDeptInfo();
					Set<Integer> depts = resourceDeptInfo.get(pathInfo);
					request.setAttribute(Constant.PERMISSION_DEPTS, depts);//将本次请求的部门权限设置到该次请求中
					return true;
				}
			}
			
			//获取不需要权限的资源
			ServletContext servletContext = request.getServletContext();
			List<SysResource> NoPermissionsResource = (List<SysResource>)servletContext.getAttribute("NoPermissionsRequiredResource");
			for(SysResource resource : NoPermissionsResource){
				if(pathInfo.equals(resource.getRequestUrl())){//如果请求的确实是不需要权限的资源
					return true;
				}
			}
			
			//如果没有访问权限，就提示无权限
			buildRes(response,StateInfo.permission_denied);
			return false;
		}
	}
	
	private void buildRes(HttpServletResponse res,StateInfo stateInfo){
		//如果不具有访问权限
		res.setStatus(HttpStatus.OK.value()); //设置状态码  
		res.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
		res.setCharacterEncoding("UTF-8"); //避免乱码  
		res.setHeader("Cache-Control", "no-cache, must-revalidate");
        try {
        	res.getWriter().write("{\"state\":\""+stateInfo.getCode()+"\",\"info\":\"" + stateInfo.getInfo() + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }  
	}

}
