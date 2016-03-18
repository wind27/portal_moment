package com.wind.mongo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.wind.commons.Constant.MongoSort;
import com.wind.commons.Constant.ServiceMsg;
import com.wind.commons.ServiceResult;
import com.wind.entity.Comment;
import com.wind.mongo.doc.utils.DocumentCommentTransfer;
import com.wind.utils.MongodbUtil;

@Service
public class CommentService {
	@Resource
	MongodbUtil mongodbUtil;
	@Resource
	IdsService idsService;
	
	/**
	 * 获取连接
	 * 
	 * @author qianchun  @date 2016年3月7日 下午4:11:31
	 * @return
	 */
	public MongoCollection<Document> getColl() {
		return mongodbUtil.getMongoCollection("wind", "comment");
	}
	//-----------------------------------------------------------
	/**
	 * 插入
	 * 
	 * @author qianchun  @date 2016年3月7日 下午6:39:35
	 * @param comment
	 * @return
	 */
	public ServiceResult<Comment> create(Comment comment) {
		ServiceResult<Comment> result = new ServiceResult<Comment>();
		if(comment==null) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		
		//获取并插入自增主键id
		MongoCollection<Document> coll = getColl();
		long id = idsService.getNextIndex("comment");
		if(id==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.ID_INCREMENT_ERROR);
			return result;
		}
		comment.setId(id);
		
		//插入
		Document doc = DocumentCommentTransfer.comment2Document(comment);
		boolean flag = mongodbUtil.insert(coll, doc);
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
	 * 批量插入
	 * 
	 * @author qianchun  @date 2016年3月7日 下午6:39:07
	 * @param commentList
	 * @return
	 */
	public ServiceResult<Comment> batchCreate(List<Comment> commentList) {
		ServiceResult<Comment> result = new ServiceResult<Comment>();
		
		if(commentList==null || commentList.size()==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		MongoCollection<Document> coll = getColl();
		for(int i=0; i<commentList.size(); i++) {
			Comment comment = commentList.get(i);
			if(comment!=null) {
				long id = idsService.getNextIndex("comment");
				if(id==0) {
					result.setSuccess(false);
					result.setMsg(ServiceMsg.ID_INCREMENT_ERROR);
					return result;
				}
				comment.setId(id);
			}
		}
		
 		List<Document> docList = DocumentCommentTransfer.comment2Document(commentList);
		if(docList==null || docList.size()==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		boolean flag = mongodbUtil.batchInsert(coll, docList);
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
	public ServiceResult<Comment> update(Bson filter, Document document) {
		ServiceResult<Comment> result = new ServiceResult<Comment>();
		if(filter==null || document==null) {
			result.setSuccess(false);
			return null;
		}
		MongoCollection<Document> coll = getColl();
		coll.findOneAndUpdate(filter, document);
		document = mongodbUtil.update(coll, filter, document);
		return null;
	}
	
	
	/**
	 * 查询
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:21:06
	 * @param params
	 * @return
	 */
	public ServiceResult<Comment> find(Map<String, Object> params) {
		ServiceResult<Comment> result = new ServiceResult<Comment>();
		List<Comment> commentList = null;
		MongoCollection<Document> coll = getColl();
		List<Document> docList = mongodbUtil.find(coll, params);
		if(docList!=null) {
			commentList = DocumentCommentTransfer.document2Comment(docList);
		}
		result.setSuccess(true);
		result.setList(commentList);
		return result;
	}
	
	/**
	 * 根据targetIds获取comment list
	 * 
	 * @author qianchun  @date 2016年3月7日 下午5:58:38
	 * @param targetIdsList
	 * @return
	 */
	public ServiceResult<Comment> findMapByTargetIds(List<Long> targetIdsList) {
		ServiceResult<Comment> result = new ServiceResult<Comment>();
		List<Comment> commentList = findByTargetIds(targetIdsList);
		Map<Long, List<Comment>> commentMap = new HashMap<Long, List<Comment>>();
		if(commentList!=null) {
			for(int i=0; i<commentList.size(); i++) {
				Comment comment = commentList.get(i);
				if(commentMap!=null && commentMap.get(comment.getTargetId())==null) {
					List<Comment> tmpList = new ArrayList<Comment>();
					tmpList.add(comment);
					commentMap.put(comment.getTargetId(), tmpList);
				} else if(commentMap!=null && commentMap.get(comment.getTargetId())!=null) {
					commentMap.get(comment.getTargetId()).add(comment);
				}
			}
		}
		
		result.setSuccess(true);
		result.setMap(commentMap);
		result.setMap(commentMap);
		return result;
	}
	
	private List<Comment> findByTargetIds(List<Long> targetIdsList) {
		Map<String, Object> params = new HashMap<String, Object>();
		//添加查询条件
		BsonDocument filter = new BsonDocument();
		filter.append("status", new BsonInt32(1));
		
		BsonArray bsonArray = new BsonArray();
		if(targetIdsList!=null && targetIdsList.size()>0) {
			for(int i=0; i<targetIdsList.size(); i++) {
				long targetId = targetIdsList.get(i);
				if(targetId!=0) {
					BsonInt64 bson = new BsonInt64(targetId);
					bsonArray.add(bson);
				}
			} 
			if(bsonArray.size()>0) {
				BsonDocument in = new BsonDocument().append("$in", bsonArray);
				filter.append("target_id", in);
			}
		}
		
		BsonDocument sort = new BsonDocument()
				.append("create_time", new BsonInt32(MongoSort.ASC));
		//查询
		MongoCollection<Document> coll = getColl();
		params.put("filter", filter);
		params.put("sort", sort);
		List<Document> docList = mongodbUtil.find(coll, params);
		List<Comment> commentList = null;
		if(docList!=null) {
			commentList = DocumentCommentTransfer.document2Comment(docList);
		}
		return commentList;
	}
	//-----------------------------------------------------------
}
