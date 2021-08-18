package com.dove.handler;


import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
public class BadCredentialsExceptionHandler extends AbstractExceptionHandler {
	@Override
	public Result handle(Exception e) {
		return Result.error(StatusCode.LOGINERROR, "密码错误");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		load(BadCredentialsException.class, this);
	}
}
