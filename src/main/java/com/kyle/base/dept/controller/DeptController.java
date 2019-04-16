package com.kyle.base.dept.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.base.dept.service.DeptService;
import com.kyle.utils.ResponseResult;
import com.kyle.utils.ServiceException;

@RestController
@RequestMapping("dept")
public class DeptController {

	@Resource
	private DeptService deptServiceImpl;
	
	@RequestMapping(value="list" ,method=RequestMethod.POST)
	public ResponseResult list(@RequestBody Map<String,Object> param){
		ResponseResult result = new ResponseResult();
		Map<String, Object> deptTree = deptServiceImpl.getDeptTree(param);
		result.setData(deptTree);
		return result;
	}
	
	@RequestMapping(value="add" ,method=RequestMethod.POST)
	public ResponseResult add(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		deptServiceImpl.addDept(param);
		return result;
	}
	
	@RequestMapping(value="update" ,method=RequestMethod.POST)
	public ResponseResult update(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		deptServiceImpl.updateDept(param);
		return result;
	}
	
	@RequestMapping(value="delete" ,method=RequestMethod.POST)
	public ResponseResult delete(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		deptServiceImpl.deleteDept(param);
		return result;
	}
	
	
	
}
