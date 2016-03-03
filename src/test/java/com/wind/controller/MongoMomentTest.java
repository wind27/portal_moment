package com.wind.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.commons.Constant.DeleteStatus;
import com.wind.entity.Moment;
import com.wind.mongo.doc.utils.DocumentMongoUtil;
import com.wind.mongo.service.MomentMongoService;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class MongoMomentTest {
    @Resource
    MomentMongoService momentMongoService;
    
    @Test
    public void main() {
//    	Moment moment1 = createMoment1();
//    	boolean flag = momentMongoService.create(moment1);
//    	if(flag==true) {
//    		System.out.println("插入成功");
//    	}
    	
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("skip", 0);
//    	params.put("limit", 1);
//    	List<Moment> momentList = momentMongoService.find(params);
//        JSONArray array = JSONArray.fromObject(momentList);
//        System.out.println(array.toString());
    	
//    	Moment moment2 = createMoment2();
//    	Bson filter = new BsonDocument()
//    			.append("id", new BsonInt64(1));
//    	Document document = DocumentMongoUtil.moment2Document(moment2);
//    	momentMongoService.replace(filter, document);
    	
    	List<Moment> momentList = createMomentList(10);
    	Boolean flag= momentMongoService.batchCreate(momentList);
    	if(flag==false) {
    		System.out.println("批量插入失败");
    	} else {
    		System.out.println("批量插入成功");
    	}
        
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("skip", 0);
//    	params.put("limit", 10);
//    	params.put("bson", filter);
//    	List<Moment> momentList = momentMongoService.find(params);
//        JSONArray array = JSONArray.fromObject(momentList);
//        System.out.println(array.toString());
    }
    
    public Moment createMoment1() {
        Moment moment = new Moment();
        JSONArray emptyArrayJson = JSONArray.fromObject(new ArrayList<>());
        moment.setUid(1l);
        moment.setId(1l);
        moment.setTitle("创建第一个此刻");
        moment.setStatus(DeleteStatus.NO);
        moment.setContent("今天创建第一个此刻");
        moment.setPraiseUid(emptyArrayJson.toString());
        moment.setCollectionUid(emptyArrayJson.toString());
        moment.setCreateTime(System.currentTimeMillis());
        moment.setUpdateTime(System.currentTimeMillis());
        moment.setPublishTime(System.currentTimeMillis());
        return moment;
    }
    
    public List<Moment> createMomentList(int size) {
    	List<Moment> momentList = new ArrayList<Moment>();
    	for(int i=0; i<size; i++) {
    		Moment moment = new Moment();
    		JSONArray emptyArrayJson = JSONArray.fromObject(new ArrayList<>());
    		moment.setUid(1l);
    		moment.setTitle("创建第"+i+"个此刻");
    		moment.setStatus(DeleteStatus.NO);
    		moment.setContent("今天创建第"+i+"个此刻");
    		moment.setPraiseUid(emptyArrayJson.toString());
    		moment.setCollectionUid(emptyArrayJson.toString());
    		moment.setCreateTime(System.currentTimeMillis());
    		moment.setUpdateTime(System.currentTimeMillis());
    		moment.setPublishTime(System.currentTimeMillis());
    		momentList.add(moment);
    	}
        return momentList;
    }
}    