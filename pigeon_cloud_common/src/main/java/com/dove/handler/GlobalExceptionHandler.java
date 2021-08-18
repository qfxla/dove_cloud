package com.dove.handler;

import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import org.springframework.stereotype.Component;

@Component
public class GlobalExceptionHandler extends AbstractExceptionHandler {
	@Override
	public Result handle(Exception e) {
		GlobalException ge = (GlobalException) e;
		return new Result(ge.isFlag(), ge.getStatusCode(), ge.getMessage(), null);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		load(GlobalException.class, this);
	}
}
