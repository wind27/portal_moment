package com.wind.mongo;

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

import com.wind.commons.Constant;
import com.wind.commons.Constant.DeleteStatus;
import com.wind.commons.Constant.IsDelete;
import com.wind.commons.ServiceResult;
import com.wind.entity.Article;
import com.wind.mongo.service.ArticleService;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:target/classes/applicationContext*.xml"})
public class ArticleTest {
    @Resource
    ArticleService articleService;
    
    @Test
    public void main() {
//    	article article1 = createarticle1();
//    	boolean flag = articleMongoService.create(article1);
//    	if(flag==true) {
//    		System.out.println("插入成功");
//    	}
    	
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("skip", 0);
//    	params.put("limit", 1);
//    	List<article> articleList = articleMongoService.find(params);
//        JSONArray array = JSONArray.fromObject(articleList);
//        System.out.println(array.toString());
    	
//    	article article2 = createarticle2();
//    	Bson filter = new BsonDocument()
//    			.append("id", new BsonInt64(1));
//    	Document document = DocumentMongoUtil.article2Document(article2);
//    	articleMongoService.replace(filter, document);
    	
    	List<Article> articleList = createarticleList(1000);
    	ServiceResult<Article> serviceResult= articleService.batchCreate(articleList);
    	if(serviceResult.isSuccess()==false) {
    		System.out.println("批量插入失败");
    	} else {
    		System.out.println("批量插入成功");
    	}
    	
//    	int pstart = 10;
//    	int plimit = 100;
//    	List<Long> uidList = new ArrayList<>();
//    	uidList.add(1l);
//    	ServiceResult result = articleService.findByUids(uidList, pstart, plimit);
//    	if(result.isSuccess()) {
//    		System.out.println(JSONArray.fromObject(result.getData()).toString());
//    	}
        
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("skip", 0);
//    	params.put("limit", 10);
//    	params.put("bson", filter);
//    	List<article> articleList = articleMongoService.find(params);
//        JSONArray array = JSONArray.fromObject(articleList);
//        System.out.println(array.toString());
    }
    
    public Article createarticle1() {
        Article article = new Article();
        JSONArray emptyArrayJson = JSONArray.fromObject(new ArrayList<>());
        article.setUid(1001l);
        article.setId(0l);
        article.setViewNum(0);
        article.setIsDel(IsDelete.NO);
        article.setTitle("创建第0个此刻");
        article.setDesc("今天创建第0个此刻");
        article.setContent("今天创建第0个此刻");
        article.setPraiseUid(emptyArrayJson.toString());
        article.setCollectionUid(emptyArrayJson.toString());
        article.setCreateTime(System.currentTimeMillis());
        article.setUpdateTime(System.currentTimeMillis());
        article.setPublishTime(System.currentTimeMillis());
        article.setStatus(Constant.ArticleStatus.PUBLISH);
        return article;
    }
    
    public List<Article> createarticleList(int size) {
    	List<Article> articleList = new ArrayList<Article>();
    	for(int i=1; i<=size; i++) {
    		Article article = new Article();
    		JSONArray emptyArrayJson = JSONArray.fromObject(new ArrayList<>());
    		article.setUid(1001l);
    		article.setViewNum(0);
    		article.setIsDel(IsDelete.NO);
    		article.setTitle("创建第"+i+"个此刻");
    		article.setDesc("今天创建第"+i+"个此刻");
    		article.setContent("今天创建第"+i+"个此刻");
    		article.setPraiseUid(emptyArrayJson.toString());
    		article.setCollectionUid(emptyArrayJson.toString());
    		article.setCreateTime(System.currentTimeMillis());
    		article.setUpdateTime(System.currentTimeMillis());
    		article.setPublishTime(System.currentTimeMillis());
    		article.setStatus(Constant.ArticleStatus.PUBLISH);
    		articleList.add(article);
    	}
        return articleList;
    }
}    