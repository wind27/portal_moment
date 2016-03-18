package com.wind.mongo.doc.utils;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.bson.Document;

import com.wind.entity.Article;

public class DocumentArticleTransfer {
	
	/**
	 * document 2 article
	 * @param doc
	 * @return
	 */
	public static Article document2Article(Document doc) {
		if(doc==null) {
			return null;
		}
		Article article = new Article();
		if(doc.getLong("id")!=null) {
			Long tmp = doc.getLong("id");
			article.setId(tmp!=null?tmp:0);
		}
		if(doc.getLong("uid")!=null) {
			Long tmp = doc.getLong("id");
			article.setUid(tmp!=null?tmp:0);
		}
		if(doc.getString("title")!=null) {
			String tmp = doc.getString("title");
			article.setTitle(tmp!=null?tmp:"");
		}
		if(doc.getString("content")!=null) {
			String tmp = doc.getString("content");
			article.setContent(tmp!=null?tmp:"");
		}
		if(doc.getLong("create_time")!=null) {
			Long tmp = doc.getLong("create_time");
			article.setCreateTime(tmp!=null?tmp:0);
		}
		if(doc.getLong("update_time")!=null) {
			Long tmp = doc.getLong("update_time");
			article.setUpdateTime(tmp!=null?tmp:0);
		}
		if(doc.getLong("publish_time")!=null) {
			Long tmp = doc.getLong("publish_time");
			article.setPublishTime(tmp!=null?tmp:0);
		}
		if(doc.getInteger("is_del")!=null) {
			Integer tmp = doc.getInteger("is_del");
			article.setIsDel(tmp!=null?tmp:0);
		}
		if(doc.getInteger("view_num")!=null) {
			Integer tmp = doc.getInteger("view_num");
			article.setViewNum(tmp!=null?tmp:0);
		}
		if(doc.getString("praise_uid")!=null) {
			String tmp = doc.getString("praise_uid");
			article.setPraiseUid(tmp!=null?tmp:"");
		}
		if(doc.getString("collection_uid")!=null) {
			String tmp = doc.getString("collection_uid");
			article.setCollectionUid(tmp!=null?tmp:"");
		}
		if(doc.getInteger("status")!=null) {
			Integer tmp = doc.getInteger("status");
			article.setStatus(tmp!=null?tmp:0);
		}
		return article;
	}
	
	/**
	 * article to document
	 * @param article
	 * @return
	 */
	public static Document article2Document(Article article) {
		JSONArray emptyArrayJson = new JSONArray();
		if(article==null) {
			return null;
		}
		Document doc = new Document();
		doc.put("id", article.getId());
		doc.put("uid", article.getUid());
		doc.put("is_del", article.getIsDel());
		doc.put("status", article.getStatus());
		doc.put("view_num", article.getViewNum());
		doc.put("create_time", article.getCreateTime());
		doc.put("update_time", article.getUpdateTime());
		doc.put("publish_time", article.getPublishTime());
		
		doc.put("title", article.getTitle()!=null ? 
				article.getTitle() : "");
		doc.put("content", article.getContent()!=null ? 
				article.getContent() : "");
		doc.put("praise_uid", article.getPraiseUid()!=null ? 
				article.getPraiseUid() : emptyArrayJson);
		doc.put("collection_uid", article.getCollectionUid()!=null ? 
				article.getCollectionUid() : emptyArrayJson);
		return doc;
	}
	
	/**
	 * article to document
	 * @param articleList
	 * @return
	 */
	public static List<Document> article2Document(List<Article> articleList) {
		if(articleList==null) {
			return null;
		}
		List<Document> docList = new ArrayList<Document>();
		for(int i=0; i<articleList.size(); i++) {
			Article article = articleList.get(i);
			Document doc = null;
			if(article!=null) {
				doc = article2Document(article);
			}
			if(doc!=null) {
				docList.add(doc);
			}
		}
		return docList;
	}
	
	/**
	 * document 2 article
	 * @param docList
	 * @return
	 */
	public static List<Article> document2Article(List<Document> docList) {
		if(docList==null) {
			return null;
		}
		Article article = null;
		List<Article> articleList = new ArrayList<Article>();
		for(int i=0; i<docList.size(); i++) {
			Document doc = docList.get(i);
			if(doc!=null) {
				article = document2Article(doc);
			}
			if(article!=null) {
				articleList.add(article);
			}
		}
		return articleList;
	}
}
