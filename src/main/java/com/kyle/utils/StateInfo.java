package com.kyle.utils;

//定义返回的状态和异常
public enum StateInfo {
	
	error(99999,"系统发生未知错误，请联系管理员"),
	success(10000,"成功"),
	id_must_not_null(10001,"id不能为空"),
	account_pwd_error(10002,"账号或者密码错误"),
	permission_denied(10003,"权限不足"),
	pageNum_must_not_null(10004,"页码不能为空"),
	pageSize_must_not_null(10005,"页大小不能为空"),
	account_must_not_null(10006,"账号不能为空"),
	userId_must_not_null(10007,"用户id不能为空"),
	password_must_not_null(10008,"密码不能为空"),
	name_must_not_null(10009,"名称不能为空"),
	age_must_not_null(10010,"年龄不能为空"),
	gender_must_not_null(10011,"性别不能为空"),
	dept_must_not_null(10012,"部门不能为空"),
	job_must_not_null(10013,"职位不能为空"),
	code_must_not_null(10014,"编码不能为空"),
	dataPermission_must_not_null(10015,"数据权限部门不能为空"),
	not_login(10016,"请先登录"),
	type_must_not_null(10017,"类型不能为空"),
	parentId_must_not_null(10018,"父id不能为空"),
	sort_must_not_null(10019,"排序号不能为空"),
	needPermission_must_not_null(10020,"是否需要权限控制不能为空")
	
	;
	
	private int code;
	
	private String info;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	private StateInfo(int code, String info) {
		this.code = code;
		this.info = info;
	}
	
	
	
}