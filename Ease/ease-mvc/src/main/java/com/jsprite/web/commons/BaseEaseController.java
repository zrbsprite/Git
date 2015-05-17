package com.jsprite.web.commons;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public abstract class BaseEaseController {

	private Logger logger = Logger.getLogger(getClass());
	
	protected final void outputJSON(HttpServletResponse response, String jsonString){
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		output(response, jsonString);
	}
	
	protected final void outputHtml(HttpServletResponse response, String html){
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		output(response, html);
	}
	
	protected final void outputText(HttpServletResponse response, String text){
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		output(response, text);
	}

	protected void output(HttpServletResponse response, String str) {
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			logger.error("向视图输出数据错误，IO异常");
		}
	}
}
