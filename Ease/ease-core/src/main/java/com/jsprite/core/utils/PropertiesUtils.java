package com.jsprite.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.jsprite.core.exception.EaseException;

public class PropertiesUtils {

	public static final String DEFAULT_SEPERATOR = ",";
	
	/**
	 * @param baseName like com.jsprite.core.util.config 
	 * <code>baseName指的是com.jsprite.core.util包下面的config.properties文件</code>
	 * @param key 
	 * @return
	 * @throws EaseException
	 */
	public static String getConfig(String baseName , String key) throws EaseException{
		ResourceBundle bundle;
		try {
			bundle = ResourceBundle.getBundle(baseName );
		} catch (Exception e) {
			throw new EaseException("Can not find the property file !");
		}
		return bundle.getString(key);
	}
	
	public static String[] getConfigArray(String baseName , String key, String split) throws EaseException{
		String value = getConfig(baseName, key);
		if(StringUtils.isEmpty(value)){
			return null;
		}
		return value.split(split);
	}
	
	public static List<String> getConfigList(String baseName , String key, String split) throws EaseException{
		String[] array = getConfigArray(baseName, key, split);
		if(null==array)
			return null;
		return Arrays.asList(array);
	}
	
	public static Map<String, Object> getAllConfig(String baseName) throws EaseException{
		ResourceBundle bundle;
		try {
			bundle = ResourceBundle.getBundle(baseName );
		} catch (Exception e) {
			throw new EaseException("Can not find the property file !");
		}
		Set<String> keys = bundle.keySet();
		Map<String, Object> map = new HashMap<String, Object>();
		for(String key : keys){
			map.put(key, bundle.getObject(key));
		}
		return map;
	}
	
	private Properties properties;
	
	private InputStream stream;
	
	public PropertiesUtils(){
		properties = new Properties();
	}
	
	public PropertiesUtils(InputStream stream){
		properties = new Properties();
		this.stream = stream;
	}
	
	private void init() throws EaseException{
		try {
			properties.load(stream);
		} catch (IOException e) {
			throw new EaseException("Can not find the property file !");
		}
	}
	
	public String getConfigString(String key) throws EaseException{
		init();
		return properties.getProperty(key);
	}
	
	public String[] getConfigArray(String key, String split) throws EaseException{
		String value = getConfigString(key);
		if(StringUtils.isEmpty(value)){
			return null;
		}
		return value.split(split);
	}
	
	public String[] getConfigArray(String key) throws EaseException{
		return getConfigArray(key, DEFAULT_SEPERATOR);
	}
}
