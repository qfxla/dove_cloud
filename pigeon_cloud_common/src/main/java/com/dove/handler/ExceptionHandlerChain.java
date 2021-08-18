package com.dove.handler;

import com.dove.entity.Result;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理链
 */
@Component
public class ExceptionHandlerChain implements Serializable {

	// 存放异常处理器
	private Map<Class, AbstractExceptionHandler> handlerNodeMap = new HashMap<>(8);


	public Result execute(Exception e) {
		AbstractExceptionHandler h = handlerNodeMap.get(e.getClass());
		return h == null ? Result.error(e.getMessage()) : h.handle(e);
	}

	public Map<Class, AbstractExceptionHandler> getHandlerNodeList() {
		return handlerNodeMap;
	}

}
