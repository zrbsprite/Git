package com.cmcc.slience.json.jackson;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONUtil {

	private ObjectMapper mapper;
	
	private JSONUtil(){}
	
	public static JSONUtil init(){
		JSONUtil util = new JSONUtil();
		util.mapper = new ObjectMapper();
		util.mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return util;
	}
	
	public String entity2Json(Object obj){
		String result=null;
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public String map2Json(Map map){
		String result = null;
		try {
			result = mapper.writeValueAsString(map);
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public String list2Json(List list){
		String result = null;
		try {
			result = mapper.writeValueAsString(list);
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public <T> T json2Entity(String json,Class<T> clazz){
		T obj = null;
		try {
			obj = mapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}
	
	public List<String> json2List(String json){
		List<String> list = null;
		try {
			TypeReference<List<String>> re = new TypeReference<List<String>>(){};
			list = json2List(json, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public <T> List<T> json2List(String json,  TypeReference<List<T>> re){
		List<T> list = null;
		try {
			list = mapper.readValue(json, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public <T, K>Map<T,K> json2Map(String json, TypeReference<Map<T, K>> re){
		Map<T,K> map = null;
		try {
			map = mapper.readValue(json, re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Map<String, String> json2Map(String json){
		Map<String, String> map = null;
		try {
			TypeReference<Map<String, String>> re = new TypeReference<Map<String,String>>(){};
			map = json2Map(json,re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public <T> T[] json2Array(String json,Class<T[]> clazz){
		T[] array = null;
		try {
			array = mapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	/**
	 * Copy an entity's properties to specially one
	 * @param fromValue
	 * @param clazz
	 * @return
	 */
	public <T> T copyEntity(T fromValue,Class<T> clazz){
		return mapper.convertValue(fromValue, clazz);
	}
}
