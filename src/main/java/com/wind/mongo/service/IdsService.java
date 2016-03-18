package com.wind.mongo.service;

import javax.annotation.Resource;

import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonString;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.wind.commons.ServiceResult;
import com.wind.utils.MongodbUtil;

@Service
public class IdsService {
	private final static Logger logger = LoggerFactory.getLogger(IdsService.class);
	
	@Resource
	MongodbUtil mongodbUtil;
	
	/**
	 * 获取连接
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:20:49
	 * @return
	 */
	public MongoCollection<Document> getColl() {
		return mongodbUtil.getMongoCollection("wind", "ids");
	}
	
	/**
	 * 初始化mongodb自增的主键id
	 * 
	 * @author qianchun  @date 2016年3月4日 上午11:35:25
	 * @return
	 */
	public ServiceResult initMongodbIds() {
		ServiceResult result = new ServiceResult();
		try {
			boolean articleFlag = false;
			boolean commentFlag = false;
			MongoCollection<Document> coll = getColl();
			if(coll==null) {
				result.setSuccess(true);
				return result;
			}
			
			MongoCursor<Document> cursor = coll.find().iterator();
			if(cursor!=null) {
				while (cursor.hasNext()) {
					Document doc = cursor.next();
					if(doc.getString("name").equals("article")) {
						articleFlag = true;
					}
					if(doc.getString("name").equals("comment")) {
						commentFlag = true;
					}
				}
			}

			if(!articleFlag) {
				Document doc = new Document();
				doc.put("name", "article");
				doc.put("next_index", 0l);
				coll.insertOne(doc);
			}
			if(!commentFlag) {
				Document doc = new Document();
				doc.put("name", "comment");
				doc.put("next_index", 0l);
				coll.insertOne(doc);
			}
			result.setSuccess(true);
			return result;
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("mongodb 创建 Ids 失败");
			return result;
		}
	}
	
	
	/**
     * 获取下一个主键id
     * 
     * @author qianchun  @date 2016年3月3日 下午6:35:16
     * @return
     */
    public long getNextIndex(String collectionName) {
    	MongoCollection<Document> coll = getColl();
    	if(coll==null) {
    		return 0;
    	}
    	
    	try {
    		BsonDocument filter = new BsonDocument().append("name", new BsonString(collectionName));
    		
    		BsonDocument seqBson = new BsonDocument().append("next_index", new BsonInt32(1));
    		BsonDocument document = new BsonDocument().append("$inc", seqBson);
    		FindOneAndUpdateOptions option = new FindOneAndUpdateOptions();
    		option.upsert(true);
    		Document nextIndexDoc = coll.findOneAndUpdate(filter, document, option);
    		if(nextIndexDoc==null) {
    			logger.error("获取下一个主键 id失败！！！");
    			return 0;
    		}
    		long nextIndex = nextIndexDoc.getLong("next_index");
    		return nextIndex;
		} catch (Exception e) {
			logger.error("获取下一个主键 id异常！！！", e);
			return 0;
		}
    }
	//-----------------------------------------------------------
}
