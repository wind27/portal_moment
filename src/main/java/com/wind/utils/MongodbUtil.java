package com.wind.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

@Component
public class MongodbUtil {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(MongodbUtil.class);
    
    @Resource
    private MongoClient mongoClient;
    
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
    		if(db!=null) {
    			coll = db.getCollection(collection);
    		}
    		if(coll==null) {
    			logger.error("获取 mongodb 表失败");
    		}
		} catch (Exception e) {
			if(mongoClient!=null) {
				mongoClient.close();
			}
			logger.error("连接 mongodb 失败！！！");
		}
    	
    	return coll;
    }
    
    /**
     * 查询
     * @param coll
     * @param params
     * @return
     */
    public List<Document> find(MongoCollection<Document> coll, Map<String, Object> params) {
    	List<Document> momentList = new ArrayList<Document>();
    	Bson bson = null;
    	Integer skip = 0;
    	Integer limit = 0;

    	FindIterable<Document> iterator = null;
    	if(params!=null && params.get("bson")!=null) {
    		bson = (Bson) params.get("bson");
    		iterator = coll.find(bson);
    	}
    	
    	if(params!=null && params.get("skip")!=null && params.get("limit")!=null) {
    		skip = (Integer) params.get("skip");
    		limit = (Integer) params.get("limit");
    		iterator.skip(skip).limit(limit);
    	}

    	MongoCursor<Document> cursor = iterator.iterator();
    	if(cursor!=null) {
    		while (cursor.hasNext()) {
    			Document doc = cursor.next();
				if(doc!=null) {
					momentList.add(doc);
				}
			}
    	}
    	return momentList;
    }
    
    /**
     * 插入一条
     * @param coll
     * @param doc
     */
    public boolean insert(MongoCollection<Document> coll, Document doc) {
    	try {
    		coll.insertOne(doc);
    		return true;
		} catch (Exception e) {
			logger.error("插入失败！！！", e);
			return false;
		}
    }
    
    /**
     * 批量插入
     * @param coll
     * @param docList
     */
    public boolean batchInsert(MongoCollection<Document> coll, List<Document> docList) {
    	try {
    		coll.insertMany(docList);
    		return true;
		} catch (Exception e) {
			logger.error("批量插入失败！！！", e);
			return false;
		}
    }
    
    public boolean update(MongoCollection<Document> coll, Bson filter, Bson update) {
    	try {
    		UpdateResult result = coll.updateOne(filter, update);
			return true;
		} catch (Exception e) {
			logger.error("修改失败！！！", e);
			return false;
		}
    }
    
    public boolean batchUpdate(MongoCollection<Document> coll, Bson filter, Bson update) {
    	try {
    		UpdateResult result = coll.updateMany(filter, update);
    		return true;
    	} catch (Exception e) {
    		logger.error("批量修改失败！！！", e);
			return false;
		}
    }
}
