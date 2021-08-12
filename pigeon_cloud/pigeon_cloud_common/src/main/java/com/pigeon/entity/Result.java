package com.pigeon.entity;

import java.util.Map;

/**
 * 返回结果的统一实体类
 */
public class Result {
	private boolean flag; //是否成功
	private int statusCode; //状态码
	private String message; //提示信息
	private Object data; //数据

	public Result() {
	}

	public Result(boolean flag, StatusCode statusCode, String message, Object data) {
		this.flag = flag;
		this.statusCode = statusCode.getCode();
		this.message = message;
		this.data = data;
	}

	public Result(boolean flag, int statusCode, String message, Object data) {
		this.flag = flag;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public static Result success() {
		return new Result(true, StatusCode.OK, "执行成功", null);
	}

	public static Result success(String message) {
		return new Result(true, StatusCode.OK, message, null);
	}


	public static Result error() {
		return new Result(false, StatusCode.ERROR, "执行出错", null);
	}

	public static Result error(String message) {
		return new Result(false, StatusCode.ERROR, message, null);
	}

	public static Result error(StatusCode statusCode, String message) {
		return new Result(false, statusCode, message, null);
	}


	public Result data(Object data) {
		this.data = data;
		return this;
	}

	public Result data(Map<String, Object> map) {
		this.data = map;
		return this;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Result setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode.getCode();
		return this;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public String getMessage() {
		return message;
	}

	public Result message(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public boolean isFlag() {
		return flag;
	}
}
