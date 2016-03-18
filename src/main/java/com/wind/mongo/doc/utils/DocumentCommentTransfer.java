package com.wind.mongo.doc.utils;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.wind.entity.Comment;

public class DocumentCommentTransfer {
	/**
	 * comment 2 document
	 * 
	 * @author qianchun  @date 2016年3月7日 下午5:51:22
	 * @param comment
	 * @return
	 */
	public static Document comment2Document(Comment comment) {
		if(comment==null) {
			return null;
		}
		Document doc = new Document();
		doc.put("id", comment.getId());
		doc.put("pid", comment.getPid());
		doc.put("is_del", comment.getIsDel());
		doc.put("target_id", comment.getTargetId());
		doc.put("target_uid", comment.getTargetUid());
		doc.put("target_type", comment.getTargetType());
		doc.put("operate_uid", comment.getOperateUid());
		doc.put("operate_type", comment.getOperateType());
		doc.put("operate_time", comment.getOperateTime());
		doc.put("create_time", comment.getCreateTime());
		doc.put("content", comment.getContent()!=null?comment.getContent():"");
		return doc;
	}
	
	/**
	 * document 2 comment
	 * 
	 * @author qianchun  @date 2016年3月7日 下午5:48:41
	 * @param doc
	 * @return
	 */
	public static Comment document2Comment(Document doc) {
		if(doc==null) {
			return null;
		}
		Comment comment = new Comment();
		if(doc.getLong("id")!=null) {
			Long tmp = doc.getLong("id");
			comment.setId(tmp!=null?tmp:0);
		}
		if(doc.getLong("pid")!=null) {
			Long tmp = doc.getLong("pid");
			comment.setPid(tmp!=null?tmp:0);
		}
		if(doc.getString("content")!=null) {
			String tmp = doc.getString("content");
			comment.setContent(tmp!=null?tmp:"");
		}
		if(doc.getInteger("operate_type")!=null) {
			Integer tmp = doc.getInteger("operate_type");
			comment.setOperateType(tmp!=null?tmp:0);
		}
		if(doc.getLong("target_id")!=null) {
			Long tmp = doc.getLong("target_id");
			comment.setTargetId(tmp!=null?tmp:0);
		}
		if(doc.getInteger("targetType")!=null) {
			Integer tmp = doc.getInteger("targetType");
			comment.setTargetType(tmp!=null?tmp:0);
		}
		if(doc.getLong("target_uid")!=null) {
			Long tmp = doc.getLong("target_uid");
			comment.setTargetUid(tmp!=null?tmp:0);
		}
		if(doc.getLong("operate_uid")!=null) {
			Long tmp = doc.getLong("operate_uid");
			comment.setOperateUid(tmp!=null?tmp:0);
		}
		if(doc.getLong("create_time")!=null) {
			Long tmp = doc.getLong("create_time");
			comment.setCreateTime(tmp!=null?tmp:0);
		}
		if(doc.getLong("operate_time")!=null) {
			Long tmp = doc.getLong("operate_time");
			comment.setOperateTime(tmp!=null?tmp:0);
		}
		if(doc.getInteger("is_del")!=null) {
			Integer tmp = doc.getInteger("is_del");
			comment.setIsDel(tmp!=null?tmp:0);
		}
		return comment;
	}
	
	/**
	 * comment 2 document
	 * 
	 * @author qianchun  @date 2016年3月7日 下午5:48:27
	 * @param commentList
	 * @return
	 */
	public static List<Document> comment2Document(List<Comment> commentList) {
		if(commentList==null) {
			return null;
		}
		List<Document> docList = new ArrayList<Document>();
		for(int i=0; i<commentList.size(); i++) {
			Comment comment = commentList.get(i);
			Document doc = null;
			if(comment!=null) {
				doc = comment2Document(comment);
			}
			if(doc!=null) {
				docList.add(doc);
			}
		}
		return docList;
	}
	/**
	 * document 2 comment
	 * 
	 * @author qianchun  @date 2016年3月7日 下午5:49:05
	 * @param docList
	 * @return
	 */
	public static List<Comment> document2Comment(List<Document> docList) {
		if(docList==null) {
			return null;
		}
		Comment comment = null;
		Document doc = null;
		List<Comment> commentList = new ArrayList<Comment>();
		for(int i=0; i<docList.size(); i++) {
			doc = docList.get(i);
			if(doc!=null) {
				comment = document2Comment(doc);
			}
			if(comment!=null) {
				commentList.add(comment);
			}
		}
		return commentList;
	}
}
