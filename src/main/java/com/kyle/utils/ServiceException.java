package com.kyle.utils;

//定义系统业务异常
public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	private int code;
	
	private String msg;
	
	public ServiceException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public ServiceException(StateInfo e) {
		this.code = e.getCode();
		this.msg = e.getInfo();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
