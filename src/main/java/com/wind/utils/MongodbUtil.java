package com.wind.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.result.UpdateResult;

import net.sf.json.JSONObject;

@Service
public class MongodbUtil {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(MongodbUtil.class);
    
    @Resource
    private MongoClient mongoClient;
    
    /**
     * 获取 database 连接
     * 
     * @author qianchun  @date 2016年3月3日 下午2:23:06
     * @param databaseName
     * @param collection
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
    			logger.error("获取 mongodb 连接失败");
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
     * 
     * @author qianchun  @date 2016年3月3日 下午2:22:42
     * @param coll
     * @param params
     * @return
     */
    public List<Document> find(MongoCollection<Document> coll, Map<String, Object> params) {
    	if(coll==null) {
    		return null;
    	}
    	try {
    		List<Document> momentList = new ArrayList<Document>();
    		Bson bson = null;
    		Integer skip = 0;
    		Integer limit = 0;
    		
    		FindIterable<Document> iterator = null;
    		if(params!=null && params.get("bson")!=null) {
    			bson = (Bson) params.get("bson");
    			iterator = coll.find(bson);
    		} else {
    			iterator = coll.find();
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
		} catch (Exception e) {
			logger.error("查询异常");
			return null;
		}
    }
    
    /**
     * 插入
     * 
     * @author qianchun  @date 2016年3月3日 下午2:22:27
     * @param coll
     * @param doc
     * @return
     */
    public boolean insert(MongoCollection<Document> coll, Document doc) {
    	if(coll==null) {
    		return false;
    	}
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
     * 
     * @author qianchun  @date 2016年3月3日 下午2:22:08
     * @param coll
     * @param docList
     * @return
     */
    public boolean batchInsert(MongoCollection<Document> coll, List<Document> docList) {
    	if(coll==null) {
    		return false;
    	}
    	for(int i=0; i<docList.size(); i++) {
    		Document doc = docList.get(i);
    		if(doc==null) {
    			docList.remove(doc);
    			i--;
    		}
    		long nextIndex = findNextIndex();
    		if(nextIndex==0) {
    			return false;
    		}
    		doc.put("id", nextIndex);
    	}
    	try {
    		coll.insertMany(docList);
    		return true;
		} catch (Exception e) {
			logger.error("批量插入失败！！！", e);
			return false;
		}
    }
    
    /**
     * 修改
     * 
     * @author qianchun  @date 2016年3月3日 下午2:21:59
     * @param coll
     * @param filter
     * @param update
     * @return
     */
    @SuppressWarnings("unused")
	public Document update(MongoCollection<Document> coll, Bson filter, Document document) {
    	if(coll==null) {
    		return null;
    	}
    	try {
    		document = new Document("$set", document);
    		UpdateResult result = coll.updateOne(filter, document);
    		return document;
		} catch (Exception e) {
			logger.error("修改失败！！！", e);
			return null;
		}
    }
    //----------------------------------------------------------------------------------
    /**
     * 批量修改
     * 
     * @author qianchun  @date 2016年3月3日 下午2:21:50
     * @param coll
     * @param filter
     * @param update
     * @return
     */
    @SuppressWarnings("unused")
	private Document batchUpdate(MongoCollection<Document> coll, Bson filter, Document document) {
    	if(coll==null) {
    		logger.error("连接 mongo 库失败!!!");
    		return null;
    	}
    	try {
    		UpdateResult result = coll.updateMany(filter, document);
    		return document;
    	} catch (Exception e) {
    		logger.error("批量修改失败！！！", e);
			return null;
		}
    }
    
    /**
     * 根据条件替换
     * 
     * @author qianchun  @date 2016年3月3日 下午4:23:11
     * @param coll
     * @param filter
     * @param update
     * @return
     */
    @SuppressWarnings("unused")
	private boolean replace(MongoCollection<Document> coll, Bson filter, Document document) {
    	if(coll==null) {
    		return false;
    	}
    	try {
    		Document doc = coll.findOneAndReplace(filter, document);
    		JSONObject obj = JSONObject.fromObject(doc);
    		System.out.println(obj.toString());
    		return true;
    	} catch (Exception e) {
    		logger.error("批量修改失败！！！", e);
			return false;
		}
    }
    
    /**
     * 获取下一个主键id
     * 
     * @author qianchun  @date 2016年3月3日 下午6:35:16
     * @return
     */
    public long findNextIndex() {
    	MongoCollection<Document> coll = getMongoCollection("wind", "ids");
    	if(coll==null) {
    		return 0;
    	}
//    	Document doc = new Document();
//    	doc.put("_id", "moment_id");
//    	doc.put("seq", 0l);
//    	coll.insertOne(doc);
    	
    	try {
    		BsonDocument filter = new BsonDocument().append("_id", new BsonString("moment_id"));
    		
    		BsonDocument seqBson = new BsonDocument().append("seq", new BsonInt32(1));
    		BsonDocument document = new BsonDocument().append("$inc", seqBson);
    		FindOneAndUpdateOptions option = new FindOneAndUpdateOptions();
    		option.upsert(true);
    		Document nextIndexDoc = coll.findOneAndUpdate(filter, document, option);
    		if(nextIndexDoc==null) {
    			logger.error("获取下一个主键 id失败！！！");
    			return 0;
    		}
    		long nextIndex = nextIndexDoc.getLong("seq");
    		return nextIndex;
		} catch (Exception e) {
			logger.error("获取下一个主键 id异常！！！", e);
			return 0;
		}
    }
}
