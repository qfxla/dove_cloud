package com.dove.processing.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Excel文件处理工具类
 */
@Component
public class ExcelUtil {

	public static void write(HttpServletResponse response, String fileName, Class header, String sheet, List<?> records) {
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
			EasyExcel.write(response.getOutputStream(), header).sheet(sheet).doWrite(records);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void read(MultipartFile file, Class header, ReadListener readListener) {
		InputStream is = null;
		try {
			is = file.getInputStream();
			EasyExcel.read(is, header, readListener).sheet().doRead();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			StreamUtil.close(is);
		}
	}

}
