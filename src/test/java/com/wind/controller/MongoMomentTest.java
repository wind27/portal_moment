package com.wind.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wind.commons.Constant;
import com.wind.commons.Constant.DeleteStatus;
import com.wind.entity.Moment;
import com.wind.mongo.service.MomentMongoService;
import com.wind.service.IMomentService;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class MongoMomentTest {
    @Resource
    MomentMongoService momentMongoService;
    
    @Test
    public void main() {
    	Moment moment = createMoment();
    	momentMongoService.create(moment);
        JSONArray array = JSONArray.fromObject("");
         System.out.println(array.toString());
    }
    
    public Moment createMoment() {
        Moment moment = new Moment();
        JSONArray emptyArrayJson = JSONArray.fromObject(new ArrayList<>());
        
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
}    