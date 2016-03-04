package com.wind.mongo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.codecs.BsonValueCodec;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.wind.commons.Constant.ServiceMsg;
import com.wind.commons.ServiceResult;
import com.wind.entity.Moment;
import com.wind.mongo.doc.utils.DocumentMongoUtil;
import com.wind.utils.MongodbUtil;

@Service
public class MomentService {
	@Resource
	MongodbUtil momentdbUtil;
	@Resource
	IdsService idsService;
	
	
	/**
	 * 获取连接
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:20:49
	 * @return
	 */
	public MongoCollection<Document> getColl() {
		return momentdbUtil.getMongoCollection("wind", "moment");
	}
	//-----------------------------------------------------------
	/**
	 *  插入
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:20:25
	 * @param moment
	 * @return
	 */
	public ServiceResult create(Moment moment) {
		ServiceResult result = new ServiceResult();
		if(moment==null) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		
		//获取并插入自增主键id
		MongoCollection<Document> coll = getColl();
		long id = idsService.getNextIndex("moment");
		if(id==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.ID_INCREMENT_ERROR);
			return result;
		}
		moment.setId(id);
		
		//插入
		Document doc = DocumentMongoUtil.moment2Document(moment);
		boolean flag = momentdbUtil.insert(coll, doc);
		if(flag) {
			result.setSuccess(true);
			return result;
		} else {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.FAIL);
			return result;
		}
	}

	/**
	 *  批量插入
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:20:25
	 * @param moment
	 * @return
	 */
	public ServiceResult batchCreate(List<Moment> momentList) {
		ServiceResult result = new ServiceResult();
		
		if(momentList==null || momentList.size()==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		MongoCollection<Document> coll = getColl();
		for(int i=0; i<momentList.size(); i++) {
			Moment moment = momentList.get(i);
			if(moment!=null) {
				long id = idsService.getNextIndex("moment");
				if(id==0) {
					result.setSuccess(false);
					result.setMsg(ServiceMsg.ID_INCREMENT_ERROR);
					return result;
				}
				moment.setId(id);
			}
		}
		
 		List<Document> docList = DocumentMongoUtil.moment2Document(momentList);
		if(docList==null || docList.size()==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		boolean flag = momentdbUtil.batchInsert(coll, docList);
		if(flag) {
			result.setSuccess(true);
			result.setMsg(ServiceMsg.SUCCESS);
		} else {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.FAIL);
		}
		return result;
	}
	
	/**
	 * 更新
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:32:23
	 * @param params
	 * @return
	 */
	public ServiceResult update(Bson filter, Document document) {
		ServiceResult result = new ServiceResult();
		if(filter==null || document==null) {
			result.setSuccess(false);
			return null;
		}
		MongoCollection<Document> coll = getColl();
		coll.findOneAndUpdate(filter, document);
		document = momentdbUtil.update(coll, filter, document);
		return null;
	}
	
	
	/**
	 * 查询
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:21:06
	 * @param params
	 * @return
	 */
	public ServiceResult find(Map<String, Object> params) {
		ServiceResult result = new ServiceResult();
		List<Moment> momentList = null;
		MongoCollection<Document> coll = getColl();
		List<Document> docList = momentdbUtil.find(coll, params);
		if(docList!=null) {
			momentList = DocumentMongoUtil.doc2Moment(docList);
		}
		result.setSuccess(true);
		result.setData(momentList);
		return result;
	}
	
	public ServiceResult findByUids(List<Long> uidList, int pstart, int plimit) {
		ServiceResult result = new ServiceResult();

		Map<String, Object> params = new HashMap<String, Object>();
		
		//添加分页条件
		if(plimit>0) {
			params.put("pstart", pstart);
			params.put("plimit", plimit);
		}
		
		//添加查询条件
		BsonDocument filter = null;
		BsonArray bsonArray = new BsonArray();
		if(uidList!=null && uidList.size()>0) {
			for(int i=0; i<uidList.size(); i++) {
				long uid = uidList.get(i);
				if(uid!=0) {
					BsonInt64 bson = new BsonInt64(uid);
					bsonArray.add(bson);
				}
			} 
			if(bsonArray.size()>0) {
				BsonDocument in = new BsonDocument().append("$in", bsonArray);
				filter = new BsonDocument().append("uid", in);
			}
		}
		
		//查询
		MongoCollection<Document> coll = getColl();
		params.put("filter", filter);
		List<Document> docList = momentdbUtil.find(coll, params);
		List<Moment> momentList = null;
		if(docList!=null) {
			momentList = DocumentMongoUtil.doc2Moment(docList);
		}
		result.setSuccess(true);
		result.setData(momentList);
		return result;
	}
	
	//-----------------------------------------------------------
}
