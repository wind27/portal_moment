package com.wind.mongo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.wind.dao.impl.MomentDao;
import com.wind.entity.Moment;
import com.wind.mongo.doc.utils.DocumentMongoUtil;
import com.wind.utils.MongodbUtil;

@Service
public class MomentMongoService {
	@Resource
	MongodbUtil momentdbUtil;
	
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
	 * 查询
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:21:06
	 * @param params
	 * @return
	 */
	public List<Moment> find(Map<String, Object> params) {
		MongoCollection<Document> coll = getColl();
		List<Document> docList = momentdbUtil.find(coll, params);
		if(docList==null || docList.size()==0) {
			return null;
		}
		List<Moment> momentList = DocumentMongoUtil.doc2Moment(docList);
		return momentList;
	}
	
	/**
	 *  插入
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:20:25
	 * @param moment
	 * @return
	 */
	public boolean create(Moment moment) {
		MongoCollection<Document> coll = getColl();
		Document doc = DocumentMongoUtil.moment2Document(moment);
		if(doc==null) {
			return false;
		}
		return momentdbUtil.insert(coll, doc);
	}

	/**
	 *  批量插入
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:20:25
	 * @param moment
	 * @return
	 */
	public boolean batchCreate(List<Moment> momentList) {
		MongoCollection<Document> coll = getColl();
		List<Document> docList = DocumentMongoUtil.moment2Document(momentList);
		if(docList==null || docList.size()==0) {
			return false;
		}
		return momentdbUtil.batchInsert(coll, docList);
	}
	
	/**
	 * 更新
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:32:23
	 * @param params
	 * @return
	 */
	public Moment update(Bson filter, Document document) {
		MongoCollection<Document> coll = getColl();
		coll.findOneAndUpdate(filter, document);
		if(filter==null || document==null) {
			return null;
		}
		document = momentdbUtil.update(coll, filter, document);
		return null;
	}
	//-----------------------------------------------------------
}
