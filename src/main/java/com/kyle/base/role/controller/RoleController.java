package com.kyle.base.role.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.base.role.service.RoleService;
import com.kyle.utils.ResponseResult;
import com.kyle.utils.ServiceException;

@RestController
@RequestMapping("role")
public class RoleController {

	@Resource
	private RoleService roleService;
	
	@RequestMapping(value="list",method=RequestMethod.POST)
	public ResponseResult list(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		Map<String, Object> listRole = roleService.listRole(param);
		result.setData(listRole);
		return result;
	} 
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResponseResult add(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		roleService.add(param);
		return result;
		
	}
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	public ResponseResult getRole(@RequestParam Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		Map<String,Object> roleInfo = roleService.getRole(param);
		result.setData(roleInfo);
		return result;
		
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public ResponseResult updateRole(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		roleService.updateRole(param);
		return result;
		
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public ResponseResult deleteRole(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		roleService.deleteRole(param);
		return result;
		
	}
	
	
}
