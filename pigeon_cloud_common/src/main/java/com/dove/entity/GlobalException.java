package com.pigeon.entity;


/**
 * 全局异常类
 */
public class GlobalException extends RuntimeException {
	private boolean flag;
	private StatusCode statusCode; //状态码
	private String message; //提示信息

	public GlobalException(Result result) {
		this.flag = result.isFlag();
		this.message = result.getMessage();
		this.statusCode = StatusCode.ERROR;
	}

	public GlobalException(StatusCode statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
		this.flag = false;
	}


	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isFlag() {
		return flag;
	}
}


