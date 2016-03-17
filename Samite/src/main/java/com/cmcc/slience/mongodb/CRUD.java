package com.cmcc.slience.mongodb;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

public class CRUD {
	
	private Logger logger = Logger.getLogger(getClass());

	private MongoClient mongo;
	
	private MongoDatabase db;
	
	private MongoCollection<Document> collection;
	
	private String collectionName = "foo";
	
	public final String DB_TEST = "test";
	
	public CRUD(){
		mongo = new MongoClient();
		db = defaultDB();
	}

	public CRUD(MongoClient client){
		mongo = client;
		db = defaultDB();
	}
	
	public CRUD(MongoClient client, String dbName){
		mongo = client;
		db = getDBByName(dbName);
	}
	
	public void configMongoClient(MongoClient client){
		mongo = client;
	}
	
	private MongoDatabase defaultDB(){
		return mongo.getDatabase(DB_TEST);
	}
	
	public MongoDatabase getDBByName(String dbName){
		return mongo.getDatabase(dbName);
	}
	
	public void configDB(MongoDatabase db){
		this.db = db;
	}
	
	public void configCollection(String collectionName){
		this.collectionName = collectionName;
	}
	
	private void initCollection(){
		collection = db.getCollection(this.collectionName);
	}
	
	public void destroy(){
		mongo.close();
	}
	
	public void add(){
		Document object = new Document();
		object.append("name", "lisi");
		object.append("age", 25);
		object.append("sex", "famale");
		initCollection();
		collection.insertOne(object);
		logger.info("����ɹ���");
	}
	
	public void delete(){
		initCollection();
		DeleteResult result = collection.deleteOne(new Document("name","lisi"));
		System.out.println(result.getDeletedCount());
	}
	
	public void queryFirst(){
		initCollection();
		Document doc = collection.find().first();
		System.out.println(doc);
	}
	
	public void queryOne(){
		initCollection();
		Document doc = collection.find(new Document("name","zhangsan")).first();
		System.out.println(doc);
	}
	
	/**
	 * ��������: queryAge<br>
	 * ������  mongodb�߼���ѯ <br>
	 * $gt ��ʾ> $lt��ʾ< $ne��ʾ!= $gte ��ʾ>= $lte ��ʾ<= <br/>
	 * $mod ȡģ("$mod",[5,1])  $nin ������ $in ����  $exists(�÷�:"$exists",true/false)  �ֶδ���  $all ȫ������ (�÷�:"$all",[2,3]) $not ��<br/>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��28������3:12:56<br><br>
	 */
	public void queryAge(){
		initCollection();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("$gt",20);
		map.put("$lt", 30);
		MongoCursor<Document> cursor = collection.find(new Document("age",new Document(map))).iterator();
		//MongoCursor<Document> cursor = collection.find(new Document("age",new Document("$gt",20))).iterator();
		try {
			while(cursor.hasNext()){
				System.out.println(cursor.next());
			}
		}finally{
			cursor.close();
		}
	}
	
	public void queryList(){
		initCollection();
		MongoCursor<Document> cursor = collection.find().iterator();
		try {
			while(cursor.hasNext()){
				System.out.println(cursor.next());
			}
		}finally{
			cursor.close();
		}
	}
	
	public static void main(String[] args) {
		CRUD demo = new CRUD();
		try {
			//demo.add();
			//demo.queryOne();
			//demo.queryList();
			//demo.delete();
			demo.queryAge();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			demo.destroy();
		}
	}
}
