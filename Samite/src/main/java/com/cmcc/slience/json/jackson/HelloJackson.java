package com.cmcc.slience.json.jackson;

import java.util.HashMap;
import java.util.Map;

import com.cmcc.slience.test.entity.Cat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 使用JackSon实现Json和Object的互转
 * @author Administrator
 *
 */
public class HelloJackson {

	public static void main(String[] args) {
		Cat cat = new Cat("limao", "limao");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);//是否格式化
		String out="";
		try {
			out = mapper.writeValueAsString(cat);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		System.out.println(out);
		
		try {
			//Cat必须有无参数的构造方法否则报错
			//com.fasterxml.jackson.databind.JsonMappingException: 
			//No suitable constructor found for type [simple type, class com.cmcc.slience.test.entity.Cat]: 
			//can not instantiate from JSON object (need to add/enable type information?)
			cat = mapper.readValue(out, Cat.class);
			System.out.println(cat.getName());
			
			Cat ca = mapper.convertValue(cat, Cat.class);
			System.out.println(ca.getName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//测试JSONUtil
		JSONUtil util = JSONUtil.init();
		Cat at = util.json2Entity(out, Cat.class);
		System.out.println(at.getName());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", "1212");
		map.put("name", "zhangsan");
		String mapJson = util.map2Json(map);
		System.out.println(mapJson);
		
		map = util.json2Map(mapJson);
		System.out.println(map.get("id"));
		
		Map<String, Cat> mapCat = new HashMap<String, Cat>();
		mapCat.put("cat01", cat);
		String mapCatJson = util.map2Json(mapCat);
		TypeReference<Map<String, Cat>> re = new TypeReference<Map<String,Cat>>(){};
		mapCat = util.json2Map(mapCatJson, re);
		System.out.println(mapCat.get("cat01").getName());
	}
	
}
