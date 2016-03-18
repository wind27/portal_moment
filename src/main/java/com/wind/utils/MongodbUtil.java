package com.wind.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import net.sf.json.JSONObject;

@Service
public class MongodbUtil {
    private final static Logger logger = LoggerFactory.getLogger(MongodbUtil.class);
    
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
    //---------------------------- 获取数据 -----------------------------------
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
    		List<Document> documentList = new ArrayList<Document>();
    		Bson bson = null;
    		Integer pstart = 0;
    		Integer plimit = 0;
    		
    		FindIterable<Document> iterator = null;
    		if(params!=null && params.get("filter")!=null) {
    			bson = (Bson) params.get("filter");
    			iterator = coll.find(bson);
    		} else {
    			iterator = coll.find();
    		}
    		
    		if(params!=null && params.get("pstart")!=null && params.get("plimit")!=null) {
    			pstart = (Integer) params.get("pstart");
    			plimit = (Integer) params.get("plimit");
    			iterator.skip(pstart).limit(plimit);
    		}
    		if(params!=null && params.get("sort")!=null) {
    			BsonDocument sort = (BsonDocument) params.get("sort");
    			iterator.sort(sort);
    		}
    		
    		MongoCursor<Document> cursor = iterator.iterator();
    		if(cursor!=null) {
    			while (cursor.hasNext()) {
    				Document doc = cursor.next();
    				if(doc!=null) {
    					documentList.add(doc);
    				}
    			}
    		}
    		return documentList;
		} catch (Exception e) {
			logger.error("查询异常");
			return null;
		}
    }
    /**
     * find by id
     * 
     * @author qianchun  @date 2016年3月14日 下午3:03:00
     * @param coll
     * @param id
     * @return
     */
    public Document findById(MongoCollection<Document> coll, long id) {
    	if(coll==null) {
    		return null;
    	}
    	try {
    		Document doc = null;
    		FindIterable<Document> iterator = null;
			BsonDocument filter = new BsonDocument().append("id", new BsonInt32(1));
			iterator = coll.find(filter).skip(0).limit(1);
    		MongoCursor<Document> cursor = iterator.iterator();
    		if(cursor!=null) {
    			while (cursor.hasNext()) {
    				doc = cursor.next();
    			}
    		}
    		return doc;
		} catch (Exception e) {
			logger.error("查询异常");
			return null;
		}
    }
    //---------------------------- 获取结束 -----------------------------------
    
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
}
