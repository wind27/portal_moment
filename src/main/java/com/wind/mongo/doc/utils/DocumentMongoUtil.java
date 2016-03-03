package com.wind.mongo.doc.utils;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.bson.Document;
import com.wind.entity.Moment;

public class DocumentMongoUtil {
	
	/**
	 * document 2 moment
	 * @param docList
	 * @return
	 */
	public static List<Moment> doc2Moment(List<Document> docList) {
		if(docList==null) {
			return null;
		}
		List<Moment> momentList = new ArrayList<Moment>();
		for(int i=0; i<docList.size(); i++) {
			Document doc = docList.get(i);
			if(doc!=null) {
				Moment moment = new Moment();
				if(doc.getLong("id")!=null) {
					Long tmp = doc.getLong("id");
					moment.setId(tmp!=null?tmp:0);
				}
				if(doc.getLong("uid")!=null) {
					Long tmp = doc.getLong("id");
					moment.setUid(tmp!=null?tmp:0);
				}
				if(doc.getString("title")!=null) {
					String tmp = doc.getString("title");
					moment.setTitle(tmp!=null?tmp:"");
				}
				if(doc.getString("content")!=null) {
					String tmp = doc.getString("content");
					moment.setContent(tmp!=null?tmp:"");
				}
				if(doc.getLong("create_time")!=null) {
					Long tmp = doc.getLong("create_time");
					moment.setCreateTime(tmp!=null?tmp:0);
				}
				if(doc.getLong("update_time")!=null) {
					Long tmp = doc.getLong("update_time");
					moment.setUpdateTime(tmp!=null?tmp:0);
				}
				if(doc.getLong("publish_time")!=null) {
					Long tmp = doc.getLong("publish_time");
					moment.setPublishTime(tmp!=null?tmp:0);
				}
				if(doc.getInteger("status")!=null) {
					Integer tmp = doc.getInteger("status");
					moment.setStatus(tmp!=null?tmp:0);
				}
				if(doc.getInteger("view_num")!=null) {
					Integer tmp = doc.getInteger("view_num");
					moment.setViewNum(tmp!=null?tmp:0);
				}
				if(doc.getString("praise_uid")!=null) {
					String tmp = doc.getString("praise_uid");
					moment.setPraiseUid(tmp!=null?tmp:"");
				}
				if(doc.getString("collection_uid")!=null) {
					String tmp = doc.getString("collection_uid");
					moment.setCollectionUid(tmp!=null?tmp:"");
				}
				momentList.add(moment);
			}
		}
		return momentList;
	}
	
	/**
	 * document 2 moment
	 * @param doc
	 * @return
	 */
	public static Moment doc2Moment(Document doc) {
		if(doc==null) {
			return null;
		}
		Moment moment = new Moment();
		if(doc.getLong("id")!=null) {
			Long tmp = doc.getLong("id");
			moment.setId(tmp!=null?tmp:0);
		}
		if(doc.getLong("uid")!=null) {
			Long tmp = doc.getLong("id");
			moment.setUid(tmp!=null?tmp:0);
		}
		if(doc.getString("title")!=null) {
			String tmp = doc.getString("title");
			moment.setTitle(tmp!=null?tmp:"");
		}
		if(doc.getString("content")!=null) {
			String tmp = doc.getString("content");
			moment.setContent(tmp!=null?tmp:"");
		}
		if(doc.getLong("create_time")!=null) {
			Long tmp = doc.getLong("create_time");
			moment.setCreateTime(tmp!=null?tmp:0);
		}
		if(doc.getLong("update_time")!=null) {
			Long tmp = doc.getLong("update_time");
			moment.setUpdateTime(tmp!=null?tmp:0);
		}
		if(doc.getLong("publish_time")!=null) {
			Long tmp = doc.getLong("publish_time");
			moment.setPublishTime(tmp!=null?tmp:0);
		}
		if(doc.getInteger("status")!=null) {
			Integer tmp = doc.getInteger("status");
			moment.setStatus(tmp!=null?tmp:0);
		}
		if(doc.getInteger("view_num")!=null) {
			Integer tmp = doc.getInteger("view_num");
			moment.setViewNum(tmp!=null?tmp:0);
		}
		if(doc.getString("praise_uid")!=null) {
			String tmp = doc.getString("praise_uid");
			moment.setPraiseUid(tmp!=null?tmp:"");
		}
		if(doc.getString("collection_uid")!=null) {
			String tmp = doc.getString("collection_uid");
			moment.setCollectionUid(tmp!=null?tmp:"");
		}
		return moment;
	}
	
	/**
	 * moment to document
	 * @param momentList
	 * @return
	 */
	public static List<Document> moment2Document(List<Moment> momentList) {
		JSONArray emptyArrayJson = new JSONArray();
		if(momentList==null) {
			return null;
		}
		List<Document> docList = new ArrayList<Document>();
		for(int i=0; i<momentList.size(); i++) {
			Moment moment = momentList.get(i);
			if(moment!=null) {
				Document doc = new Document();
				doc.put("id", moment.getId());
				doc.put("uid", moment.getUid());
				doc.put("status", moment.getStatus());
				doc.put("view_num", moment.getViewNum());
				doc.put("create_time", moment.getCreateTime());
				doc.put("update_time", moment.getUpdateTime());
				doc.put("publish_time", moment.getPublishTime());
				
				doc.put("title", moment.getTitle()!=null ? 
						moment.getTitle() : null);
				doc.put("content", moment.getContent()!=null ? 
						moment.getContent() : null);
				doc.put("praise_uid", moment.getPraiseUid()!=null ? 
						moment.getPraiseUid() : emptyArrayJson);
				doc.put("collection_uid", moment.getCollectionUid()!=null ? 
						moment.getCollectionUid() : emptyArrayJson);

				docList.add(doc);
			}
		}
		return docList;
	}
	
	/**
	 * moment to document
	 * @param moment
	 * @return
	 */
	public static Document moment2Document(Moment moment) {
		JSONArray emptyArrayJson = new JSONArray();
		if(moment==null) {
			return null;
		}
		Document doc = new Document();
		doc.put("id", moment.getId());
		doc.put("uid", moment.getUid());
		doc.put("status", moment.getStatus());
		doc.put("view_num", moment.getViewNum());
		doc.put("create_time", moment.getCreateTime());
		doc.put("update_time", moment.getUpdateTime());
		doc.put("publish_time", moment.getPublishTime());
		
		doc.put("title", moment.getTitle()!=null ? 
				moment.getTitle() : null);
		doc.put("content", moment.getContent()!=null ? 
				moment.getContent() : null);
		doc.put("praise_uid", moment.getPraiseUid()!=null ? 
				moment.getPraiseUid() : emptyArrayJson);
		doc.put("collection_uid", moment.getCollectionUid()!=null ? 
				moment.getCollectionUid() : emptyArrayJson);
		return doc;
	}
}
