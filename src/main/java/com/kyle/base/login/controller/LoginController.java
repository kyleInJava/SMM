package com.kyle.base.login.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.base.login.service.LoginService;
import com.kyle.utils.ResponseResult;
import com.kyle.utils.ServiceException;

@RestController
@RequestMapping("login")
public class LoginController {

	@Resource
	private LoginService loginService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ResponseResult login(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		Map<String, Object> res = loginService.login(param);
		result.setData(res);
		return result;
	}
	
	@RequestMapping(value="logout",method=RequestMethod.POST)
	public ResponseResult logout(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		Map<String, Object> res = loginService.logout(param);
		result.setData(res);
		return result;
	}
}
