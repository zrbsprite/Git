/**
 * File Name:ExportUtils.java
 * @Description: 
 * Copyright 2015 EASE Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年4月16日下午1:33:41
 */
package com.jsprite.web.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年4月16日下午1:33:41 <br>
 * E-mail:  <br> 
 */
public class ExportUtils {

	/**
	 * 方法名称: makeExcelStream<br>
	 * 描述：浏览器导出excel时header信息			<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2015年4月16日下午1:46:00<br/>
	 * @param response
	 * @param fileName
	 * @return
	 */
	public static OutputStream makeExcelStream(HttpServletResponse response, String fileName){
		response.setContentType("octets/stream");
		try {
			fileName = new String(fileName.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        response.addHeader("Content-Disposition", "attachment;filename="+fileName);
        OutputStream os;
		try {
			os = response.getOutputStream();
		} catch (IOException e) {
			os = null;
		}
        return os;
	}
}
