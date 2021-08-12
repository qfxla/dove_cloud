package com.pigeon.entity;

/**
 * 响应状态枚举类
 */
public enum StatusCode {
	OK(20000), //成功
	ERROR(20001), //失败
	LOGINERROR(20002), //用户名或密码错误
	ACCESSERROR(20003), //权限不足
	REMOTEERROR(20004), //远程调用失败
	REPERROR(20005), //重复操作
	UPLOADERROT(20006), //图片上传失败
	DUPLICATE(20007); //重复值

	private int code;

	private StatusCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}


}
