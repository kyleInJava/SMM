package com.kyle.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//一些常规的工具类
public class CommonUtil {
	
	private static final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	/**
	 * 校验参数是否存在或者格式是否正确，存在或者正确就返回true
	 * @param o 要校验的数据
	 * @param type 要校验的类型
	 * 0:校验对象是否为数字
	 * 1:判断是否为正整数
	 * 2:判断是否为手机号
	 * @return
	 */
	public static boolean checkData(Object o , int... types){
		//先校验数据是否存在
		if(o == null || "".equals(o)){
			return false;
		}
		
		if(types.length > 0){
			int type = types[0];
			switch(type){
				case 0 :{
					try{
						Double.parseDouble(o.toString());
						return true;
					}catch(NumberFormatException e){
						return false;
					}
				}
				
				case 1 :{
					try{
						int num = Integer.parseInt(o.toString());
						return num > 0;
					}catch(NumberFormatException e){
						return false;
					}
				}
				
				case 2: {
                    //手机号码匹配
                    Pattern pattern = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
                    Matcher matcher = pattern.matcher(o.toString());
                    return matcher.matches();
                }
				 
				
					
			}
		}
		
		//如果没有types参数，说明只做非空校验
		return true;
	}
	
	/**
	 * 使用MD5进行加密
	 * @param resource 
	 * @return 返回16进制字符串
	 */
	public static String MD5(String resource){
		//使用MD5进行加密
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] digest = md5.digest(resource.getBytes());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			int var = digest[i] & 0xff;
			if (var < 16)
				sb.append("0");
			sb.append(Integer.toHexString(var));
		}
		return sb.toString();
	}
	
	/**
	 * 生成随机长度的字符串
	 * @param length
	 * @return
	 */
	public static String RandomStringGenerator(int length){
		//如果长度小于等于0 则返回空字符串
		if(length < 1) return "";
		StringBuilder sb = new StringBuilder();
		Random random=new Random();
		for(int i = 0 ; i < length; i++ ){
			int num = random.nextInt(62);
			sb.append(str.charAt(num));
		}
		return sb.toString();
	}
	
	/**
	 * 对数据进行校验，并转换为对应的数据类型，如果数据不存在或者无法转换，则抛出异常
	 * @param o
	 * @param clazz
	 * @param exceptionStr
	 * @return
	 * @throws ServiceException 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T CheckData(Object o, Class<T> clazz,StateInfo stateInfo) throws ServiceException{
		
		if(o == null){//如果对象为null
			throw new ServiceException(stateInfo);
		}
		
		String temp = o.toString();
		
		T t = null;
		try{
			if(clazz == String.class){
				t =(T)temp;
			}else if(clazz == Integer.class){
				t = (T)Integer.valueOf(temp);
			}else if(clazz == double.class){
				t = (T)Double.valueOf(temp);
			}else if(clazz == long.class){
				t = (T)Long.valueOf(temp);
			}else if(clazz == float.class){
				t = (T)Float.valueOf(temp);
			}else if(clazz == short.class){
				t = (T)Short.valueOf(temp);
			}else if(clazz == boolean.class){
				t = (T)Boolean.valueOf(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(111,"数据"+o.toString()+"转化失败");
		}
		
		return t;
		
	}
	
	/**
	 * 对数据进行校验，存在就进行类型转换，不存在就返回null
	 * @param o
	 * @param clazz
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T CheckData(Object o, Class<T> clazz) throws ServiceException{
		
		if(o == null){//如果对象为null
			return null;
		}
		
		String temp = o.toString();
		
		T t = null;
		try{
			if(clazz == String.class){
				t =(T)temp;
			}else if(clazz == Integer.class){
				t = (T)Integer.valueOf(temp);
			}else if(clazz == double.class){
				t = (T)Double.valueOf(temp);
			}else if(clazz == long.class){
				t = (T)Long.valueOf(temp);
			}else if(clazz == float.class){
				t = (T)Float.valueOf(temp);
			}else if(clazz == short.class){
				t = (T)Short.valueOf(temp);
			}else if(clazz == boolean.class){
				t = (T)Boolean.valueOf(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(111,"数据"+o.toString()+"转化失败");
		}
		
		return t;
		
	}
	
	
	
}
