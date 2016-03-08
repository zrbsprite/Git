package com.penzias.main.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class ServiceCreater {
	
	private static Configuration configuration;
	
	static {
		try{
			String path = ServiceCreater.class.getResource("/").toURI().getPath();
			File bin = new File(path);
			String templatePath = bin.getParent() + "\\src\\com\\penzias\\main";
			configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			FileTemplateLoader loader;
			loader = new FileTemplateLoader(new File(templatePath));
			configuration.setTemplateLoader(loader);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		String path = null;
		try{
			path = ServiceCreater.class.getResource("/").toURI().getPath();
		} catch (URISyntaxException e1){
			e1.printStackTrace();
			System.exit(0);
		}
		File bin = new File(path);
		String parent = bin.getParent();
		String mapperPath = parent.concat("\\src\\com\\penzias\\dao");
		String serviceOutPath = parent.concat("\\src\\com\\penzias\\service\\");
		String serviceImplOutPath = parent.concat("\\src\\com\\penzias\\service\\impl\\");
		File dic = new File(mapperPath);
		if(dic.isDirectory()){
			File[] files = dic.listFiles();
			for(File file : files){
				String fileName = file.getName();
				fileName = fileName.replace(".java","");
				String mapperName = fileName;
				String mapperVarName = mapperName.toLowerCase().charAt(0) + mapperName.substring(1);
				String entityName = fileName.substring(0, fileName.indexOf("Mapper"));
				String exampleName = entityName + "Example";
				String serviceName = entityName + "Service";
				String serviceVarName = serviceName.toLowerCase().charAt(0) + serviceName.substring(1);
				String serviceImplName = serviceName + "Impl";
				Map<String, String> map = new HashMap<String, String>();
				map.put("mapperName", mapperName);
				map.put("mapperVarName", mapperVarName);
				map.put("entityName", entityName);
				map.put("exampleName", exampleName);
				map.put("serviceName", serviceName);
				map.put("serviceVarName", serviceVarName);
				map.put("serviceImplName", serviceImplName);
				try{
					renderServiceFile(serviceOutPath + serviceName + ".java",map);
					renderServiceImplFile(serviceImplOutPath + serviceImplName + ".java", map);
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void renderServiceFile(String fileName, Map<String, String> map) throws IOException{
		Template template = configuration.getTemplate("Service.ftl");
		File file = new File(fileName);
		if(file.exists()){
			return;
		}
		FileOutputStream out = new FileOutputStream(file);
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
	
	public static void renderServiceImplFile(String fileName, Map<String, String> map) throws IOException{
		File file = new File(fileName);
		if(file.exists()){
			return;
		}
		Template template = configuration.getTemplate("ServiceImpl.ftl");
		FileOutputStream out = new FileOutputStream(file);
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
