/**
 * File Name:FreemarkerUtil.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年6月5日上午11:05:57
 */
package com.penzias.utils;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年6月5日上午11:05:57 <br>
 * E-mail:  <br> 
 */
public class FreemarkerUtilBean {

	private String basePath = "WEB-INF/template";
	
	private String baseClassTemplatePath = "/template";
	
	private String baseDirPath = "/template";
	
	public String renderFromFile(ServletContext servletContext, String templateName, Map<String, Object> data){
		StringWriter sw = new StringWriter();
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setServletContextForTemplateLoading(servletContext, basePath);
		Template template;
		try {
			template = configuration.getTemplate(templateName, "UTF-8");
			template.process(data, sw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	public <T> String renderFromFile(Class<T> resourceLoaderClass, String templateName, Map<String, Object> data){
		StringWriter sw = new StringWriter();
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setOutputEncoding("utf-8");
		configuration.setClassForTemplateLoading(resourceLoaderClass, baseClassTemplatePath);
		Template template;
		try {
			template = configuration.getTemplate(templateName, "UTF-8");
			template.process(data, sw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	public String renderFromFile(String dirPath, String templateName, Map<String, Object> data){
		StringWriter sw = new StringWriter();
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setOutputEncoding("utf-8");
		Template template;
		try {
			configuration.setDirectoryForTemplateLoading(new File(dirPath));
			template = configuration.getTemplate(templateName, "UTF-8");
			template.process(data, sw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	public String renderFromFile(String templateName, Map<String, Object> data){
		StringWriter sw = new StringWriter();
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setOutputEncoding("utf-8");
		Template template;
		try {
			configuration.setDirectoryForTemplateLoading(new File(baseDirPath));
			template = configuration.getTemplate(templateName, "UTF-8");
			template.process(data, sw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	public void renderTemplate(ServletContext servletContext, Writer writer,String templateName, Map<String, Object> data){
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setOutputEncoding("utf-8");
		configuration.setServletContextForTemplateLoading(servletContext, basePath);
		Template template;
		try {
			template = configuration.getTemplate(templateName);
			template.process(data, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public <T> void renderTemplate(Class<T> resourceLoaderClass, Writer writer,String templateName, Map<String, Object> data){
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setOutputEncoding("utf-8");
		configuration.setClassForTemplateLoading(resourceLoaderClass, baseClassTemplatePath);
		Template template;
		try {
			template = configuration.getTemplate(templateName);
			template.process(data, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void renderTemplate(String dirPath, Writer writer,String templateName, Map<String, Object> data){
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		configuration.setOutputEncoding("utf-8");
		Template template;
		try {
			configuration.setDirectoryForTemplateLoading(new File(dirPath));
			template = configuration.getTemplate(templateName);
			template.process(data, writer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public void setBaseClassTemplatePath(String baseClassTemplatePath) {
		this.baseClassTemplatePath = baseClassTemplatePath;
	}

	public void setBaseDirPath(String baseDirPath) {
		this.baseDirPath = baseDirPath;
	}
}