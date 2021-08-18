package com.pigeon.handler;


import com.pigeon.entity.Result;
import com.pigeon.entity.StatusCode;
import com.pigeon.entity.Result;
import com.pigeon.entity.StatusCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UsernameNotFoundExceptionHandler extends AbstractExceptionHandler {
	@Override
	public Result handle(Exception e) {
		return Result.error(StatusCode.LOGINERROR, "用户名找不到");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		load(UsernameNotFoundException.class, this);
	}
}



