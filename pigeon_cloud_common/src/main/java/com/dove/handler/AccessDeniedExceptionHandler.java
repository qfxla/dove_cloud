package com.pigeon.handler;


import com.pigeon.entity.Result;
import com.pigeon.entity.StatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;


/**
 * 访问权限不足异常处理器
 */
@Component
public class AccessDeniedExceptionHandler extends AbstractExceptionHandler {

	@Override
	public Result handle(Exception e) {
		return Result.error(StatusCode.ACCESSERROR, "访问权限不足");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		load(AccessDeniedException.class, this);
	}
}
