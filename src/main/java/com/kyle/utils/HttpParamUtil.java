package com.kyle.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

/**
 * 处理http请求和响应的
 * @author kyle
 *
 */
public class HttpParamUtil {

	/**
	 * 将request请求参数转化为map
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getRequestParam(HttpServletRequest request) throws IOException{
		return getRequestParam(request,Map.class);
	}
	
	/**
	 * 将request请求参数转化为指定对象
	 * @param request
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static <T> T  getRequestParam(HttpServletRequest request,Class<T> clazz) throws IOException{
		BufferedReader reader = request.getReader();
		char[] cbuf = new char[1024];
		int len;
		StringBuilder sb = new StringBuilder();
		while((len = reader.read(cbuf)) != -1){
			sb.append(cbuf, 0, len);
		}
		String s = sb.toString();
		if(s.length() == 0){
			s = "{}";
		}
		T res = JSON.parseObject(s,clazz);
		return res;
	}
	
	/**
	 * 获取get请求中的请求参数
	 * @return
	 */
	public static Map<String,Object> getGetRequestParam(HttpServletRequest request){
		Map<String,Object> res = new HashMap<>();
		String queryString = request.getQueryString();
		if(queryString != null && queryString.length() > 0){
			String[] arr = queryString.split("&");
			try {
				for(String param : arr){
					String[] kv = param.split("=");
					res.put(kv[0], URLDecoder.decode(kv[1], "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
