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
	 * ��������: listValue<br>
	 * �������������ݿ�   <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��26������5:46:10<br>
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
	 * ��������: getCollectionByName<br>
	 * ������ �������ƻ�ȡcollection  <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��26������5:43:13<br>
	 * @param db
	 * @param name
	 * @return<br>
	 */
	private static MongoCollection<Document> getCollectionByName(MongoDatabase db, String name){
		return db.getCollection(name);
	}
	
	/**
	 * ��������: getCollections<br>
	 * ������������е�collection����   <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��26������5:25:10<br>
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
	 * ��������: getDB<br>
	 * ��������ȡָ�������ݿ�   <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��26������5:11:33<br>
	 * @param mongo
	 * @param dbName
	 * @return<br>
	 */
	private static MongoDatabase getDB(MongoClient mongo, String dbName){
		return mongo.getDatabase(dbName);
	}
	
	
	/**
	 * ��������: printDBNames<br>
	 * ������ ������ݿ������  <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��26������5:05:43<br>
	 * @param mongo<br>
	 */
	private static void printDBNames(MongoClient mongo){
		List<String> dbNames = mongo.getDatabaseNames();
		for(String name: dbNames){
			System.out.println(name);
		}
	}
}
