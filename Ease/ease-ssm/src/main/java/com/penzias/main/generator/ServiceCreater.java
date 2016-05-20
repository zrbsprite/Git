package com.penzias.main.generator;

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

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void config(String entityPath, String daoPath, String servicePath){
		this.entityPath = entityPath;
		this.daoPath = daoPath;
		this.servicePath = servicePath;
	}
	
	public void loadConfigurationFromBin(String path){
		Properties prop = new Properties();
		path = path.concat("others/serviceCreator.bin");
		try{
			InputStream inStream = new FileInputStream(path);
			prop.load(inStream);
			this.daoPath = prop.getProperty("output_dao_dir", "");
			this.entityPath = prop.getProperty("output_entity_dir", "");
			this.outputPath = prop.getProperty("target_dir", "");
			this.readDaoFolder = prop.getProperty("read_dao_folder", "");
			this.readDaoNames = prop.getProperty("read_dao_names", "");
			inStream.close();
		} catch (FileNotFoundException e){
			e.printStackTrace();
			logger.error("找不到serviceCreator.bin文件，请将该文件至于others下！");
		} catch (IOException e){
			e.printStackTrace();
			logger.error("读取serviceCreator.bin异常！");
		}
		
	}
	
	{
		try{
			String path = ServiceCreater.class.getResource("/").toURI().getPath();
			File bin = new File(path);
			String templatePath = bin.getParent() + "\\src\\main\\java\\com\\zyht\\common\\generator";
			configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			FileTemplateLoader loader;
			loader = new FileTemplateLoader(new File(templatePath));
			configuration.setTemplateLoader(loader);
			
			loadConfigurationFromBin(path);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		ServiceCreater creator = new ServiceCreater();
		creator.process();
	}
	
	public void process(){
		String path = null;
		try{
			path = ServiceCreater.class.getResource("/").toURI().getPath();
		} catch (URISyntaxException e1){
			e1.printStackTrace();
			System.exit(1);
		}
		File bin = new File(path);
		String parent = bin.getParent();
		
		String outputPath = this.outputPath;
		String daoFolder = parent.concat("/src/main/java/").concat(this.readDaoFolder); 
		String daoNames = this.readDaoNames.concat(",");
		File dic = new File(daoFolder);
		if(dic.isDirectory()){
			File[] files = dic.listFiles();
			for(File file:files){
				String fileName = file.getName();
				if(daoNames.indexOf(fileName)!=-1){
					fileName = fileName.replace(".java","");
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
						renderServiceFile(outputPath + serviceName + ".java",map);
						renderServiceImplFile(outputPath + serviceImplName + ".java",map);
						renderDaoFile(fileName + ".java",map);
					} catch (IOException e){
						e.printStackTrace();
					}
					logger.info("ServiceCreator deal one done:"+fileName);
				}
			}
		}
	}

	private void renderServiceFile(String fileName,Map<String, String> map)
			throws IOException{

		Template template = configuration.getTemplate("Service.ftl");
		File file = new File(fileName);
		if(file.exists()){
			Path path = Paths.get(file.getAbsolutePath());
			Files.deleteIfExists(path);
		}
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
