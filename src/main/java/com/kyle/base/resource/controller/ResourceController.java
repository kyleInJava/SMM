package com.kyle.base.resource.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kyle.base.resource.service.ResourceService;
import com.kyle.utils.ResponseResult;
import com.kyle.utils.ServiceException;

@RestController
@RequestMapping("resource")
public class ResourceController {
	
	@Resource
	private ResourceService resourceService;
	
	
	@RequestMapping(value="tree",method=RequestMethod.POST)
	public ResponseResult listResouces(@RequestBody Map<String,Object> param){
		ResponseResult result = new ResponseResult();
		Map<String, Object> listResource = resourceService.getResourceTree(param);
		result.setData(listResource);
		return result;
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResponseResult addResouce(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		resourceService.addResouce(param);
		return result;
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public ResponseResult updateResouce(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		resourceService.updateResouce(param);
		return result;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public ResponseResult deleteResouce(@RequestBody Map<String,Object> param) throws ServiceException{
		ResponseResult result = new ResponseResult();
		resourceService.deleteResouce(param);
		return result;
	}
	
	
	
}
