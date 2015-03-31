package com.jsprite.core.impl;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.jsprite.core.JsonOperation;

public class FastJsonOpreation extends JsonOperation {

	private JSONSerializer serializer;
	
	{
		serializer = new JSONSerializer();
	}
	
	@Override
	public String object2JsonString(Object object) {
		return JSON.toJSONString(object);
	}

	@Override
	public String list2JsonString(List<? extends Object> list) {
		return JSON.toJSONString(list);
	}

	@Override
	public String map2JsonString(Map<String, Object> map) {
		return JSON.toJSONString(map);
	}

	@Override
	public <T> T json2Object(String json) {
		T obj = JSON.parseObject(json, new TypeReference<T>(){});
		return obj;
	}

	@Override
	public String object2JsonString(Object object, final String[] filters) {
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				for(String filter : filters){
					if(name.equals(filter)){
						return true;
					}
				}
				return false;
			}
		};
		serializer.getPropertyFilters().add(filter);
		SerializeWriter writer = serializer.getWriter();
		serializer.write(writer);
		return writer.toString();
	}

	@Override
	public <T> List<T> json2List(String json) {
		return JSON.parseObject(json, new TypeReference<List<T>>(){}.getType());
	}

	@Override
	public <T> Map<String, T> json2Map(String json) {
		return JSON.parseObject(json, new TypeReference<Map<String, T>>(){}.getType());
	}

}
