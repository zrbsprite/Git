package com.jsprite.core;

import java.util.List;
import java.util.Map;

public abstract class JsonOperation {

	public abstract String object2JsonString(Object object);
	
	public abstract String object2JsonString(Object object, String[] filters);
	
	public abstract String list2JsonString(List<? extends Object> list);
	
	public abstract String map2JsonString(Map<String, Object> map);
	
	public abstract <T> T json2Object(String json);
	
	public abstract <T> List<T> json2List(String json);	
	
	public abstract <T> Map<String, T> json2Map(String json);	
}