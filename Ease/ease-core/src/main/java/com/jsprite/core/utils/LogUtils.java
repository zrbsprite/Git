package com.jsprite.core.utils;

import org.apache.log4j.Logger;

public class LogUtils {

	private static Logger init(Class<? extends Object> clazz){
		if(null == clazz){
			return Logger.getLogger(LogUtils.class);
		}else{
			return Logger.getLogger(clazz);
		}
	}
	
	public static void debug(String message, Class<? extends Object> clazz){
		init(clazz).debug(message);
	}
	
	public static void debug(String message){
		debug(message, null);
	}
	
	public static void info(String message, Class<? extends Object> clazz){
		init(clazz).info(message);
	}
	
	public static void info(String message){
		info(message, null);
	}
	
	public static void warn(String message, Class<? extends Object> clazz){
		init(clazz).warn(message);
	}
	
	public static void warn(String message){
		warn(message, null);
	}
	
	public static void error(String message, Class<? extends Object> clazz){
		init(clazz).error(message);
	}
	
	public static void error(String message){
		error(message, null);
	}
}
