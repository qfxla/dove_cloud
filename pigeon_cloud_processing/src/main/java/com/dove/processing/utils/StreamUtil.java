package com.dove.processing.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


/**
 * 流处理工具类
 */
@Component
public class StreamUtil {

	public static void close(InputStream is) {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
