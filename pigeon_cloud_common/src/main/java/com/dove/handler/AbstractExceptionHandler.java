package com.dove.handler;

import com.dove.entity.Result;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 抽象的异常处理节点
 */
public abstract class AbstractExceptionHandler implements Serializable, InitializingBean {

	@Autowired
	private ExceptionHandlerChain chain; //异常处理链



	public AbstractExceptionHandler() {
	}

	/**
	 * 将异常类处理器加载进异常处理器中容器中
	 *
	 * @param handler
	 */
	public void load(Class c, AbstractExceptionHandler handler) {
		chain.getHandlerNodeList().put(c, handler);
	}

	/**
	 * 处理异常的抽象方法
	 *
	 * @return
	 */
	public abstract Result handle(Exception e);

}
