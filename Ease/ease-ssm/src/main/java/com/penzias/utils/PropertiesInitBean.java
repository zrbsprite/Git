package com.penzias.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

public class PropertiesInitBean implements InitializingBean{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final Map<String, String> configuration = new HashMap<String, String>();
	
	private static final String DEFAULTBASEPATH = "configuration.commons.configuration";
	
	private String configBasePath = DEFAULTBASEPATH;
	
	{
		logger.info("配置文件缓存处理器[PropertiesInitBean]初始化完毕...");
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		ResourceBundle rb = ResourceBundle.getBundle(this.configBasePath);
		if(rb.keySet().isEmpty()){
			rb = ResourceBundle.getBundle(DEFAULTBASEPATH);
			if(rb.keySet().isEmpty()){
				return;
			}
		}
		Set<String> set = rb.keySet();
		for(String key : set){
			configuration.put(key, rb.getString(key));
		}
	}

	public static class PropertiesUtils{
		public static String getConfigByKey(String key){
			return configuration.get(key);
		}
		
		public static Map<String, String> getAllConfig(){
			return configuration;
		}
		
		public static boolean hasConfig(){
			return configuration.size()>0;
		}
	}

	public String getConfigBasePath() {
		return configBasePath;
	}

	public void setConfigBasePath(String configBasePath) {
		if(StringUtils.isEmpty(configBasePath)){
			this.configBasePath = configBasePath;
		}
	}
}
