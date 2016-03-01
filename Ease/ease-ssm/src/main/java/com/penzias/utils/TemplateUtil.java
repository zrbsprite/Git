package com.penzias.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateUtil {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public String getReplacedStr(String src, Map<String, String> params){
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		StringTemplateLoader loader = new StringTemplateLoader();
		loader.putTemplate("urlTemplate", src);
		configuration.setTemplateLoader(loader);
		StringWriter sw = new StringWriter();
		try {
			Template template = configuration.getTemplate("urlTemplate", "UTF-8");
			template.process(params, sw);
			return sw.toString();
		} catch (IOException e) {
			logger.error("读取模板["+src+"]失败！");
		} catch (TemplateException e) {
			logger.error("渲染模板["+src+"]错误！");
		}finally{
			try {
				sw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void renderFile(String templatePath, String templateName,String fileName, Map<String, String> map) throws IOException{
		Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		FileTemplateLoader loader = new FileTemplateLoader(new File(templatePath));
		configuration.setTemplateLoader(loader);
		Template template = configuration.getTemplate(templateName);
		FileOutputStream out = new FileOutputStream(fileName);
		OutputStreamWriter osw = new OutputStreamWriter(out);
		try{
			template.process(map, osw);
		} catch (TemplateException e){
			e.printStackTrace();
		}finally{
			osw.close();
			out.close();
		}
	}
}
