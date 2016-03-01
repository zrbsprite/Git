package com.penzias.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public abstract class AjaxConroller extends BaseController{

	protected final void writeJson(HttpServletResponse response, String json) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		write(response.getWriter(), json);
	}
	
	protected final void writeHtml(HttpServletResponse response, String str) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		write(response.getWriter(), str);
	}
	
	private void write(PrintWriter pw, String str){
		pw.write(str);
		pw.flush();
	}
	
	protected final void writeText(HttpServletResponse response, String str) throws IOException{
		write(response.getWriter(), str);
	}
	
	protected final void writeText(PrintWriter writer, String str){
		write(writer,str);
	}
}
