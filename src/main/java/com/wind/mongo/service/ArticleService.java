package com.wind.mongo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.wind.commons.Constant.ServiceMsg;
import com.wind.commons.ServiceResult;
import com.wind.entity.Article;
import com.wind.mongo.doc.utils.DocumentArticleTransfer;
import com.wind.utils.MongodbUtil;

@Service
public class ArticleService {
	@Resource
	MongodbUtil articledbUtil;
	@Resource
	IdsService idsService;
	
	
	/**
	 * 获取连接
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:20:49
	 * @return
	 */
	public MongoCollection<Document> getColl() {
		return articledbUtil.getMongoCollection("wind", "article");
	}
	//-----------------------------------------------------------
	/**
	 *  插入
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:20:25
	 * @param article
	 * @return
	 */
	public ServiceResult<Article> create(Article article) {
		ServiceResult<Article> result = new ServiceResult<Article>();
		if(article==null) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		
		//获取并插入自增主键id
		MongoCollection<Document> coll = getColl();
		long id = idsService.getNextIndex("article");
		if(id==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.ID_INCREMENT_ERROR);
			return result;
		}
		article.setId(id);
		
		//插入
		Document doc = DocumentArticleTransfer.article2Document(article);
		boolean flag = articledbUtil.insert(coll, doc);
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
	 * @param article
	 * @return
	 */
	public ServiceResult<Article> batchCreate(List<Article> articleList) {
		ServiceResult<Article> result = new ServiceResult<Article>();
		
		if(articleList==null || articleList.size()==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		MongoCollection<Document> coll = getColl();
		for(int i=0; i<articleList.size(); i++) {
			Article article = articleList.get(i);
			if(article!=null) {
				long id = idsService.getNextIndex("article");
				if(id==0) {
					result.setSuccess(false);
					result.setMsg(ServiceMsg.ID_INCREMENT_ERROR);
					return result;
				}
				article.setId(id);
			}
		}
		
 		List<Document> docList = DocumentArticleTransfer.article2Document(articleList);
		if(docList==null || docList.size()==0) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		boolean flag = articledbUtil.batchInsert(coll, docList);
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
	public ServiceResult<Article> updateById(long id, Article article) {
		ServiceResult<Article> result = new ServiceResult<Article>();
		if(id==0 || article==null) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.PARAMS_ERROR);
			return result;
		}
		MongoCollection<Document> coll = getColl();
		BsonDocument filter = new BsonDocument().append("id", new BsonInt64(id));
		Document document = DocumentArticleTransfer.article2Document(article);
		coll.findOneAndUpdate(filter, document);
		document = articledbUtil.update(coll, filter, document);
		if(document==null) {
			result.setSuccess(false);
			result.setMsg(ServiceMsg.SUCCESS);
		} else {
			result.setSuccess(true);
			result.setMsg(ServiceMsg.FAIL);
		}
		return result;
	}
	
	//---------------------------- 查询数据 -----------------------------------
	/**
	 * 查询
	 * 
	 * @author qianchun  @date 2016年3月3日 下午2:21:06
	 * @param params
	 * @return
	 */
	public ServiceResult<Article> find(Map<String, Object> params) {
		ServiceResult<Article> result = new ServiceResult<Article>();
		List<Article> articleList = null;
		MongoCollection<Document> coll = getColl();
		List<Document> docList = articledbUtil.find(coll, params);
		if(docList!=null) {
			articleList = DocumentArticleTransfer.document2Article(docList);
		}
		result.setSuccess(true);
		result.setList(articleList);
		return result;
	}
	/**
	 * 根据uids查询
	 * 
	 * @author qianchun  @date 2016年3月14日 下午2:53:59
	 * @param uidList
	 * @param pstart
	 * @param plimit
	 * @return
	 */
	public ServiceResult<Article> findByUids(List<Long> uidList, int pstart, int plimit) {
		ServiceResult<Article> result = new ServiceResult<Article>();

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
		List<Document> docList = articledbUtil.find(coll, params);
		List<Article> articleList = null;
		if(docList!=null) {
			articleList = DocumentArticleTransfer.document2Article(docList);
		}
		result.setSuccess(true);
		result.setList(articleList);
		return result;
	}
	/**
	 * 根据id查询
	 * 
	 * @author qianchun  @date 2016年3月14日 下午2:55:03
	 * @param id
	 * @return
	 */
	public ServiceResult<Article> findById(long id) {
		ServiceResult<Article> result = new ServiceResult<Article>();
		Article article = null;
		MongoCollection<Document> coll = getColl();
		Document document = articledbUtil.findById(coll, id);
		if(document!=null) {
			article = DocumentArticleTransfer.document2Article(document);
		}
		result.setSuccess(true);
		result.setObject(article);
		return result;
	}
	//-----------------------------------------------------------
}
