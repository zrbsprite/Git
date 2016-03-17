package com.cmcc.slience.mongodb;

import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class HelloMongo {

	public static void main(String[] args) {
		MongoClient mongo = new MongoClient();
		try {
			printDBNames(mongo);
			
			MongoDatabase db = getDB(mongo, "test");
			
			getCollections(db);
			
			MongoCollection<Document> fooCollection = getCollectionByName(db, "foo");
			listValue(fooCollection);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			mongo.close();
		}
	}

	/**
	 * 方法名称: listValue<br>
	 * 描述：遍历数据库   <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年2月26日下午5:46:10<br>
	 * @param fooCollection<br>
	 */
	private static void listValue(MongoCollection<Document> fooCollection){
		FindIterable<Document> fIt =  fooCollection.find();
		MongoCursor<Document> cursor = fIt.iterator();
		while(cursor.hasNext()){
			Document obj = cursor.next();
			//sSystem.out.println(cursor.next());
			System.out.println(obj.size());
			Set<Entry<String, Object>> set = obj.entrySet();
			for(Entry<String, Object> en : set){
				System.out.println(en.getKey()+" is :" +en.getValue());
			}
		}
	}
	
	/**
	 * 方法名称: getCollectionByName<br>
	 * 描述： 根据名称获取collection  <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年2月26日下午5:43:13<br>
	 * @param db
	 * @param name
	 * @return<br>
	 */
	private static MongoCollection<Document> getCollectionByName(MongoDatabase db, String name){
		return db.getCollection(name);
	}
	
	/**
	 * 方法名称: getCollections<br>
	 * 描述：输出所有的collection名称   <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年2月26日下午5:25:10<br>
	 * @param db<br>
	 */
	private static void getCollections(MongoDatabase db){
		MongoIterable<String> itNames = db.listCollectionNames();
		MongoCursor<String> it = itNames.iterator();
		while(it.hasNext()){
			String name = it.next();
			System.out.println(name);
		}
	}
	
	/**
	 * 方法名称: getDB<br>
	 * 描述：获取指定的数据库   <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年2月26日下午5:11:33<br>
	 * @param mongo
	 * @param dbName
	 * @return<br>
	 */
	private static MongoDatabase getDB(MongoClient mongo, String dbName){
		return mongo.getDatabase(dbName);
	}
	
	
	/**
	 * 方法名称: printDBNames<br>
	 * 描述： 输出数据库的名称  <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年2月26日下午5:05:43<br>
	 * @param mongo<br>
	 */
	private static void printDBNames(MongoClient mongo){
		List<String> dbNames = mongo.getDatabaseNames();
		for(String name: dbNames){
			System.out.println(name);
		}
	}
}
