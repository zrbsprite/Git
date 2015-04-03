package com.jsprite.core.impl;

import java.util.List;
import java.util.Map;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jsprite.core.JsonOperation;

public class GsonOperation extends JsonOperation{

	private Gson gson;
	
	{
		//gson = new Gson();
		//表示启用@Expose注解
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
	
	@Override
	public String object2JsonString(Object object) {
		return gson.toJson(object);
	}

	@Override
	public String object2JsonString(Object object, final String[] filters) {
		Gson gsonWithFilter = new GsonBuilder().addDeserializationExclusionStrategy(new ExclusionStrategy() {
			public boolean shouldSkipField(FieldAttributes attr) {
				for(String filter : filters){
					if(filter.equals(attr.getName())){
						return true;
					}
				}
				return false;
			}
			
			public boolean shouldSkipClass(Class<?> clazz) {
				return false;
			}
		}).create();
		return gsonWithFilter.toJson(object);
	}

	@Override
	public String list2JsonString(List<? extends Object> list) {
		return gson.toJson(list);
	}

	@Override
	public String map2JsonString(Map<String, Object> map) {
		return gson.toJson(map);
	}

	@Override
	public <T> T json2Object(String json) {
		return gson.fromJson(json, new TypeToken<T>(){}.getType());
	}

	@Override
	public <T> List<T> json2List(String json) {
		return gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
	}

	@Override
	public <T> Map<String, T> json2Map(String json) {
		return gson.fromJson(json, new TypeToken<Map<String, T>>(){}.getType());
	}

}
