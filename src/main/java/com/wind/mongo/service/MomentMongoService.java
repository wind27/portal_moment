package com.wind.mongo.service;


import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.wind.commons.Constant.DeleteStatus;
import com.wind.entity.Moment;
import com.wind.utils.MongodbUtil;

import net.sf.json.JSONArray;

public class MomentMongoService {
//	@Resource
	MongodbUtil momentdbUtil;
	
	MongoCollection<Document> coll = null;
	
//	FindIterable<Document> cur = coll.find();
//	coll.insertOne(document);
//	while (cur.iterator().hasNext()) {
//		Document doc = cur.iterator().next();
//		long id = doc.getLong("id");
//		System.out.println(id);
//	}
//	String result = net.sf.json.JSONArray.fromObject(coll).toString();
//	System.out.println(result);
	
	public MomentMongoService() {
		momentdbUtil = new MongodbUtil();
		this.coll = momentdbUtil.getMongoCollection("wind", "moment");
	}
	
	public void create(Moment moment) {
		Document doc  = new Document();
		doc.put("collection_uid", moment.getCollectionUid());
		doc.put("content", moment.getContent());
		doc.put("create_time", moment.getCreateTime());
		doc.put("id", moment.getId());
		doc.put("praise_uid", moment.getPraiseUid());
		doc.put("publish_time", moment.getPublishTime());
		doc.put("status", moment.getStatus());
		doc.put("title", moment.getTitle());
		doc.put("uid", moment.getUid());
		doc.put("update_time", moment.getUpdateTime());
		doc.put("view_num", moment.getViewNum());
		
		coll.insertOne(doc);
	}
	
	public static Moment createMoment() {
        Moment moment = new Moment();
        JSONArray emptyArrayJson = JSONArray.fromObject(new ArrayList<>());
        
        moment.setId(1l);
        moment.setTitle("创建第一个此刻");
        moment.setStatus(DeleteStatus.NO);
        moment.setContent("今天创建第一个此刻");

        moment.setUid(1l);
        moment.setCreateTime(System.currentTimeMillis());
        moment.setUpdateTime(System.currentTimeMillis());
        moment.setPublishTime(System.currentTimeMillis());

        moment.setPraiseUid(emptyArrayJson.toString());
        moment.setCollectionUid(emptyArrayJson.toString());
        return moment;
    }
	
	public static void main(String[] args) {
		MomentMongoService momentMongoService = new MomentMongoService();
		Moment moment = createMoment();
		momentMongoService.create(moment);
		
		
	}
}
