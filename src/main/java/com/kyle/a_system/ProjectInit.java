package com.kyle.a_system;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import com.kyle.base.dept.dao.SysDeptMapper;
import com.kyle.base.dept.entity.SysDept;
import com.kyle.base.resource.dao.SysResourceMapper;
import com.kyle.base.resource.entity.SysResource;
import com.kyle.utils.Constant;

/**
 * 系统初始化的时候要执行的一些方法
 * @author kyle
 *
 */
@Component
public class ProjectInit {
	
	@Resource
	private SysResourceMapper sysResourceMapper;
	@Resource
	private ServletContext servletContext;
	@Resource
	private SysDeptMapper sysDeptMapper;
	
	/**
	 * 将不需要进行权限校验的资源统一加载到内存中，来进行校验
	 */
	@PostConstruct
	public void loadNoPermissionsRequiredResource(){
		List<SysResource> resources =  sysResourceMapper.getNoPermissionsRequiredResource();
		servletContext.setAttribute("NoPermissionsRequiredResource", resources);
	}
	
	/**
	 * 项目启动的时候将所有的部门信息加载到系统中，免得一次次去查
	 */
	@PostConstruct
	public void loadAllDept(){
		List<SysDept> depts = sysDeptMapper.getAllDept();
		servletContext.setAttribute(Constant.ALL_DEPT, depts);
	}

}
