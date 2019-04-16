package com.kyle.utils;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 分页查询工具类
 * @author kyle
 *
 */
public class PageUtil {

	//设置查询参数
	public static void  startpage(Map<String,Object> paraMap) throws ServiceException{
		//先判断传入参数是否有pageNum和pageSize
		Object o1 = paraMap.get("pageNum");
		Object o2 = paraMap.get("pageSize");
		if(!CommonUtil.checkData(o1, 1)){
			throw new ServiceException(StateInfo.pageNum_must_not_null);
		}
		if(!CommonUtil.checkData(o2, 1)){
			throw new ServiceException(StateInfo.pageSize_must_not_null);
		}
		Integer pageNum = Integer.parseInt(o1.toString()) ;
		Integer pageSize = Integer.parseInt(o2.toString()) ;
		PageHelper.startPage(pageNum , pageSize);
	}
	
	//包装返回分页数据
	public static <K> void  packPageResult(Map<String,Object> resultMap,List<K> list){
		
		PageInfo<K> page = new PageInfo<K>(list);
		resultMap.put("datalist", page.getList());
		resultMap.put("pageNum", page.getPageNum());
		resultMap.put("pageSize", page.getPageSize());
		resultMap.put("total", page.getTotal());
		resultMap.put("pages", page.getPages());
	}
}
