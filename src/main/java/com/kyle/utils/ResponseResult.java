package com.kyle.utils;

import com.alibaba.fastjson.JSON;

/**
 * 构建返回的json数据
 * @author kyle
 *
 */
public class ResponseResult {
	
	private int state = StateInfo.success.getCode();
	
	private String info = StateInfo.success.getInfo();
	
	private Object data;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public void setStateInfo(StateInfo si){
		this.state = si.getCode();
		this.info = si.getInfo();
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
	
}
