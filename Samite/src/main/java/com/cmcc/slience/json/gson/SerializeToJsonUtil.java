package com.cmcc.slience.json.gson;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.cmcc.slience.test.entity.Configration;
import com.cmcc.slience.test.entity.ConfigrationItem;
import com.cmcc.slience.test.entity.DateBean;
import com.cmcc.slience.test.entity.Dog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class SerializeToJsonUtil {

	//默认的gson处理
	private static Gson defaultGson = new Gson();
	
	//表示启用@Expose注解
	private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	
	private static GsonBuilder gsonBuilder;
	
	static{
		gsonBuilder = new GsonBuilder();
	}
	
	public static Gson getGson(){
		return gson;
	}
	
	public static Gson getDefaultGson(){
		return defaultGson;
	}
	
	public static String serializeObject(Object obj){
		return gson.toJson(obj);
	}
	
	public static String serializeArray(String[] strs){
		return gson.toJson(strs);
	}
	
	public static <T> String serializeCollection(Collection<T> collection){
		return gson.toJson(collection);
	}
	
	public static <T> String serializeCollectionUseDefault(Collection<T> collection){
		return defaultGson.toJson(collection);
	}
	
	public static <T> String serializeCollectionUseDefaultInType(Collection<T> collection, Type type){
		return defaultGson.toJson(collection, type);
	}
	
	public static <T> List<T> conver2List(String json, TypeToken<List<T>> token){
		return defaultGson.fromJson(json, token.getType());
	}
	
	/**
	 * Map<String, T>
	 * @param json
	 * @param token
	 * @return
	 */
	public static <T> List<T> conver2Map(String json, TypeToken<List<T>> token){
		return defaultGson.fromJson(json, token.getType());
	}
	
	public static <T> T convert2Bean(String json, Class<T> clazz){
		return (T) gson.fromJson(json, clazz);
	}
	
	/**
	 * 针对时间类型做特殊处理
	 * @param obj
	 * @return
	 */
	public static String serializeDateBean(Object obj){
		Gson superGson = gsonBuilder.registerTypeAdapter(Date.class, new DateJsonSerializer()).setDateFormat(DateFormat.LONG).create();
		return superGson.toJson(obj);
	}

	/**
	 * 针对时间类型做特殊处理
	 * @param obj
	 * @return
	 */
	public static <T> T json2DateBean(String str, Class<T> clazz){
		Gson superGson = gsonBuilder.registerTypeAdapter(Date.class, new DateJsonDeserializer()).setDateFormat(DateFormat.LONG).create();
		return superGson.fromJson(str, clazz);
	}
	
	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.setCategry("jinmao");
		dog.setName("huahua");
		dog.setSex("male");
		
		String json = serializeObject(dog);
		
		System.out.println(json);
		
		Dog convertDog = convert2Bean(json, Dog.class);
		
		System.out.println(convertDog.getName());
		
		JsonElement element = gson.toJsonTree(dog);
		JsonObject jObj = element.getAsJsonObject();
		System.out.println(jObj);
		
		String[] strs = {"zhangsan","lisi","chengong","wangwu","wangliu"};
		System.out.println(serializeArray(strs));
		
		int[] ints = {12,13,14,18,16,17};
		System.out.println(gson.toJson(ints));
		
		List<String> list = new ArrayList<String>();
		list.add("zhangsan");
		list.add("lisi");
		list.add("wangwu");
		String out = serializeCollection(list);
		System.out.println(out);
		
		List<Dog> dogs = new ArrayList<Dog>();
		dogs.add(dog);
		out = serializeCollection(dogs);
		System.out.println(out);
		
		List<Configration> configs = new ArrayList<Configration>();
		List<ConfigrationItem> items = new ArrayList<ConfigrationItem>();
		ConfigrationItem item = new ConfigrationItem();
		item.setKey("user");
		item.setParent("person");
		item.setValue("man");
		items.add(item);
		Configration conf = new Configration();
		conf.setType(1);
		conf.setItems(items);
		configs.add(conf);
		out = serializeCollectionUseDefault(items);
		System.out.println("out: "+out);
		
		String configsStr = serializeCollectionUseDefault(configs);
		System.out.println(configsStr);
		
		TypeToken<List<ConfigrationItem>> token =  new TypeToken<List<ConfigrationItem>>(){};
		items = conver2List(out,token);
		System.out.println("token:"+items.get(0).getClass());
		System.out.println(items.get(0).getKey());
		System.out.println(token.getType());
		
		TypeToken<List<Configration>> token3 =  new TypeToken<List<Configration>>(){};
		List<Configration> configList3 = conver2List(configsStr,token3);
		System.out.println(configList3.get(0).getItems().get(0).getKey());
		
		DateBean db = new DateBean();
		db.setId("121212");
		db.setNow(new Date());
		String dJson = serializeDateBean(db);
		System.out.println(dJson);
		
		DateBean dbJson = json2DateBean(dJson, DateBean.class);
		System.out.println(dbJson.getNow());
	}
}
