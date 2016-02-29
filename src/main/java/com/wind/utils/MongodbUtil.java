package com.wind.utils;

import org.bson.Document;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class MongodbUtil {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(MongodbUtil.class);
    
    private String ip;
    private int port;
    private MongoClient mongoClient;
    
    public MongodbUtil() {
    	ip = "192.168.217.128";
    	port = 27017;
    	mongoClient = new MongoClient(ip, port);
	}
    
    /**
     * 获取 database
     * @param databaseName
     * @return
     */
    public MongoCollection<Document> getMongoCollection(String databaseName, String collection) {
    	MongoDatabase db = null;
    	MongoCollection<Document> coll = null;
    	try {
    		db = mongoClient.getDatabase(databaseName);
		} catch (Exception e) {
			logger.error("连接 mongodb 失败！！！");
		}
    	
    	if(db!=null) {
    		coll = db.getCollection(collection);
    	}
    	
    	if(coll==null) {
    		logger.error("获取 mongodb 表失败");
    	}
    	return coll;
    }
    
    public void main(String[] args) {
    	MongodbUtil m = new MongodbUtil();
    	m.getMongoCollection("admin", "moment");
    	System.out.println("连接成功！！！");
	}
    

}
