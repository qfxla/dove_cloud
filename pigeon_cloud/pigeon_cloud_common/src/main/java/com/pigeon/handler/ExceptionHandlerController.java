package com.pigeon.handler;

import com.pigeon.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class ExceptionHandlerController {

	@Autowired
	private ExceptionHandlerChain chain;

	@ExceptionHandler
	@ResponseBody
	public Result error(Exception e) {
		e.printStackTrace();
		return chain.execute(e);
	}
}
