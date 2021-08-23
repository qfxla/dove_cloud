package com.dove.processing.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DataUtil {

	/**
	 * 构造一个Class对象的实例子，并将source中的值赋值过去
	 *
	 * @param source    值来源对象
	 * @param c         Class对象
	 * @param allowNull 是否允许source中的null值进行赋值
	 * @param key       要增加赋值的key
	 * @param val       要增加赋值的val
	 * @return
	 * @throws Exception
	 */
	public static Object copyProperties(Object source, Class c, boolean allowNull, String key, Object val) {
		HashMap<String, Object> map = new HashMap<>(2);
		map.put(key, val);
		return copyProperties(source, c, allowNull, map);
	}


	public static Object copyProperties(Object source, Class c, boolean allowNull, Map<String, Object> fieldsMap) {
		if (source == null) {
			return null;
		}
		Object target = null;
		try {
			target = c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(c);
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
								readMethod.setAccessible(true);
							}
							Object value = readMethod.invoke(source);
							if (allowNull && value == null) {
								continue;
							}
							if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
								writeMethod.setAccessible(true);
							}
							writeMethod.invoke(target, value);
						} catch (Throwable t) {
							throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", t);
						}
					}
				} else if (!fieldsMap.isEmpty() && fieldsMap.containsKey(targetPd.getName())) {
					Object val = fieldsMap.get(targetPd.getName());
					if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
						writeMethod.setAccessible(true);
					}
					if (allowNull && val == null) {
						continue;
					}
					try {
						writeMethod.invoke(target, val);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return target;
	}


	public static Object copyProperties(Object source, Class c, boolean allowNull) {
		return copyProperties(source, c, allowNull, null, null);
	}

	public static <T extends Object> Page getPage(int current, int size, long total, Future<T> future) {
		Page<T> page = new Page<>(current, size);
		page.setTotal(total);
		page.setPages(total / size + total % size == 0 ? 0 : 1);
		try {
			page.setRecords((List<T>) future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return page;
	}

	public static <T extends Object> Page getPage(int current, int size,Future<T> future) {
		Page<T> page = new Page<>(current, size);
		try {
			page.setRecords((List<T>) future.get());
			page.setTotal(((List<?>) future.get()).size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return page;
	}
}





