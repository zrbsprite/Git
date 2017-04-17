package com.ldyt.boot.generator.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * <b>描述：</b> service代码生成器<br/>
 * <b>作者：</b>Bob <br/>
 * <b>修改日期：</b>2016年5月19日 - 下午5:02:48<br/>
 */
public class ServiceCreater {

	private String outputPath;
	
	private String entityPath;

	private String daoPath;

	private String servicePath;
	
	private String readDaoFolder;
	
	private String readDaoNames;

	private Configuration configuration;

	private String configPath;
	
	private String basePath;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public ServiceCreater(){
		this(null);
	}
	
	public ServiceCreater(String config){
		this.configPath = config;
		loadConfigurationFromBin(basePath);
	}
	
	{
		logger.info("Service代码生成器开始初始化配置……");
		try{
			basePath = ServiceCreater.class.getResource("/").toURI().getPath();
			File bin = new File(basePath);
			String templatePath = bin.getParent() + "\\src\\main\\java\\com\\zyht\\common\\generator";
			configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			FileTemplateLoader loader;
			loader = new FileTemplateLoader(new File(templatePath));
			configuration.setTemplateLoader(loader);
		} catch (Exception e){
			e.printStackTrace();
		}
		logger.info("Service代码生成器初始化配置完成……");
	}
	
	public void config(String entityPath, String daoPath, String servicePath){
		this.entityPath = entityPath;
		this.daoPath = daoPath;
		this.servicePath = servicePath;
	}
	
	public void loadConfigurationFromBin(String path){
		Properties prop = new Properties();
		if(null!=this.configPath){
			path = this.configPath;
		}
		path = path.concat(File.separator).concat("serviceCreator.bin");
		try{
			InputStream inStream = new FileInputStream(path);
			prop.load(inStream);
			this.daoPath = prop.getProperty("output_dao_dir", "");
			this.entityPath = prop.getProperty("output_entity_dir", "");
			//this.outputPath = prop.getProperty("target_dir", "");
			try{
				this.outputPath = getClass().getResource("/").toURI().getPath();
				File outputFolder = new File(outputPath);
				this.outputPath = outputFolder.getParentFile().getAbsolutePath();
				String baseFolder = prop.getProperty("base_folder", "");
				this.outputPath = this.outputPath.concat("/src/main/java/").concat(baseFolder);
			} catch (URISyntaxException e){
				e.printStackTrace();
			}
			this.readDaoFolder = prop.getProperty("read_dao_folder", "");
			this.readDaoNames = prop.getProperty("read_dao_names", "");
			this.servicePath = prop.getProperty("output_service_dir", "");
			inStream.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
			logger.error("找不到serviceCreator.bin文件，请将该文件至于others下！");
		} catch (IOException e){
			e.printStackTrace();
			logger.error("读取serviceCreator.bin异常！");
		}
		
	}

	public static void main(String[] args){
		String path;
		try{
			path = ServiceCreater.class.getResource("").toURI().getPath();
			ServiceCreater creator = new ServiceCreater(path);
			creator.process();
		} catch (URISyntaxException e){
			e.printStackTrace();
			System.out.println("获取配置文件路径出错！");
		}
	}
	
	public void process(){
		logger.info("Service代码生成器开始生成代码>:<");
		
		String path = null;
		try{
			path = ServiceCreater.class.getResource("/").toURI().getPath();
		} catch (URISyntaxException e1){
			e1.printStackTrace();
			System.exit(1);
		}
		File bin = new File(path);
		String parent = bin.getParent();
		
		String outputPath = this.outputPath.concat(File.separator);
		String daoFolder = parent.concat("/src/main/java/").concat(this.readDaoFolder); 
		String daoNames = this.readDaoNames.concat(",");
		File dic = new File(daoFolder);
		if(dic.isDirectory()){
			File[] files = dic.listFiles();
			for(File file:files){
				String fileName = file.getName();
				fileName = fileName.replace(".java","");
				if(daoNames.indexOf(fileName)!=-1){
					String mapperName = fileName;
					String mapperVarName = mapperName.toLowerCase().charAt(0) + mapperName.substring(1);
					String entityName = fileName.substring(0,fileName.indexOf("Mapper"));
					String exampleName = entityName + "Example";
					String serviceName = entityName + "Service";
					String serviceVarName = serviceName.toLowerCase().charAt(0)
							+ serviceName.substring(1);
					String serviceImplName = serviceName + "Impl";
					Map<String, String> map = new HashMap<String, String>();
					map.put("mapperName",mapperName);
					map.put("mapperVarName",mapperVarName);
					map.put("entityName",entityName);
					map.put("exampleName",exampleName);
					map.put("serviceName",serviceName);
					map.put("serviceVarName",serviceVarName);
					map.put("serviceImplName",serviceImplName);
					map.put("entityPath", this.entityPath);
					map.put("daoPath", this.daoPath);
					map.put("servicePath", this.servicePath);
					try{
						renderServiceFile(outputPath+ "service/" + serviceName + ".java",map);
						renderServiceImplFile(outputPath + "service/impl/" + serviceImplName + ".java",map);
						renderDaoFile(outputPath +"dao/"+ fileName + ".java",map);
					} catch (IOException e){
						e.printStackTrace();
					}
					logger.info("ServiceCreator deal one done:"+fileName);
				}
			}
		}
		logger.info("Service代码生成器生成代码OK！");
	}

	private void renderServiceFile(String fileName,Map<String, String> map)
			throws IOException{

		Template template = configuration.getTemplate("Service.ftl");
		File file = new File(fileName);
		if(file.exists()){
			Path path = Paths.get(file.getAbsolutePath());
			Files.deleteIfExists(path);
		}
		file.getParentFile().mkdirs();
		FileOutputStream out = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(out);
		try{
			template.process(map,osw);
		} catch (TemplateException e){
			e.printStackTrace();
		} finally{
			osw.close();
			out.close();
		}
	}

	private void renderServiceImplFile(String fileName,Map<String, String> map)
			throws IOException{

		File file = new File(fileName);
		if(file.exists()){
			Path path = Paths.get(file.getAbsolutePath());
			Files.deleteIfExists(path);
		}
		Template template = configuration.getTemplate("ServiceImpl.ftl");
		FileOutputStream out = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(out);
		try{
			template.process(map,osw);
		} catch (TemplateException e){
			e.printStackTrace();
		} finally{
			osw.close();
			out.close();
		}
	}

	private void renderDaoFile(String fileName,Map<String, String> map) throws IOException{

		File file = new File(fileName);
		if(file.exists()){
			Path path = Paths.get(file.getAbsolutePath());
			Files.deleteIfExists(path);
		}
		Template template = configuration.getTemplate("Dao.ftl");
		FileOutputStream out = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(out);
		try{
			template.process(map,osw);
		} catch (TemplateException e){
			e.printStackTrace();
		} finally{
			osw.close();
			out.close();
		}
	}
	
	public void setEntityPath(String entityPath){
		this.entityPath = entityPath;
	}

	public void setDaoPath(String daoPath){
		this.daoPath = daoPath;
	}
	
	public void setServicePath(String servicePath){
		this.servicePath = servicePath;
	}
	
	public void setOutputPath(String outputPath){
	
		this.outputPath = outputPath;
	}

	public void setReadDaoFolder(String readDaoFolder){
	
		this.readDaoFolder = readDaoFolder;
	}
	public void setReadDaoNames(String readDaoNames){
	
		this.readDaoNames = readDaoNames;
	}
}
