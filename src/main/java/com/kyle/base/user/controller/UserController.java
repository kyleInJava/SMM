package com.kyle.base.user.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.base.user.service.UserService;
import com.kyle.utils.ResponseResult;
import com.kyle.utils.ServiceException;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Resource
	private UserService userService;

	@RequestMapping(value="list",method=RequestMethod.POST)
	public ResponseResult listUser(@RequestBody Map<String,Object> paraMap) throws ServiceException{
		ResponseResult result = new ResponseResult();
		Map<String,Object> users = userService.listUser(paraMap);
		result.setData(users);
		return result;
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResponseResult addUser(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		userService.addUser(param);
		return result;
	}
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	public ResponseResult getUser(@RequestParam Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		Map<String, Object> userInfo = userService.getUserBasicInfo(param);
		result.setData(userInfo);
		return result;
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public ResponseResult updateUser(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		userService.updateUserInfo(param);
		return result;
	}
	
	@RequestMapping(value="changePwd",method=RequestMethod.POST)
	public ResponseResult changePwd(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		userService.changePwd(param);
		return result;
	}
	
	/**
	 * 改变该人员的状态，删除或者禁用
	 * @param param
	 * @return
	 * @throws ServiceException 
	 */
	@RequestMapping(value="changeState",method=RequestMethod.POST)
	public ResponseResult changeState(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		userService.changeState(param);
		return result;
	}
	
}
