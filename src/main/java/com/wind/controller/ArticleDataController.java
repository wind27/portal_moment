package com.wind.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wind.commons.Constant;
import com.wind.commons.Constant.ArticleStatus;
import com.wind.commons.Constant.MetaCode;
import com.wind.commons.Constant.MetaMsg;
import com.wind.commons.Meta;
import com.wind.commons.ServiceResult;
import com.wind.entity.Article;
import com.wind.entity.Comment;
import com.wind.entity.Param;
import com.wind.entity.User;
import com.wind.mongo.service.ArticleService;
import com.wind.mongo.service.CommentService;
import com.wind.service.IUserService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/article")
public class ArticleDataController {
    @Resource
    IUserService userService;
    @Resource
    ArticleService articleService;
    @Resource
    CommentService commentService;
    
    //---------------------------- 添加数据 -----------------------------------
    /**
     * 添加草稿动态
     * 
     * @author qianchun  @date 2016年3月14日 下午2:48:30
     * @param param
     * @param article
     * @param request
     * @return
     */
    @RequestMapping(value = "/add/draft", method = RequestMethod.POST)
    @ResponseBody
    public Object draftAdd(Param param, Article article, HttpServletRequest request) {
    	Map<String, Object> resultObject = new HashMap<>();
    	if(param==null || article==null) {
    		resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
    		return resultObject;
    	}
    	JSONArray emptyArray = new JSONArray();
    	article.setViewNum(0);
    	article.setUid(param.getUid());
    	article.setIsDel(Constant.IsDelete.NO);
    	article.setPraiseUid(emptyArray.toString());
    	article.setCollectionUid(emptyArray.toString());
    	
    	article.setCreateTime(System.currentTimeMillis());
    	article.setUpdateTime(0);
    	article.setPublishTime(0);
    	article.setStatus(ArticleStatus.DRAFT);
    	resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
    	return resultObject;
    }
    /**
     * 添加私有动态
     * 
     * @author qianchun  @date 2016年3月14日 下午2:47:34
     * @param param
     * @param article
     * @param request
     * @return
     */
    @RequestMapping(value = "/add/private", method = RequestMethod.POST)
    @ResponseBody
    public Object privateAdd(Param param, Article article, HttpServletRequest request) {
    	Map<String, Object> resultObject = new HashMap<>();
    	if(param==null || article==null) {
    		resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
    		return resultObject;
    	}
    	JSONArray emptyArray = new JSONArray();
    	article.setViewNum(0);
    	article.setUid(param.getUid());
    	article.setIsDel(Constant.IsDelete.NO);
    	article.setPraiseUid(emptyArray.toString());
    	article.setCollectionUid(emptyArray.toString());
    	
    	article.setCreateTime(System.currentTimeMillis());
    	article.setUpdateTime(0);
    	article.setPublishTime(0);
    	article.setStatus(ArticleStatus.PRIVATE);
    	resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
    	return resultObject;
    }
    /**
     * 添加发布动态
     * 
     * @author qianchun  @date 2016年3月14日 下午2:48:00
     * @param param
     * @param article
     * @param request
     * @return
     */
    @RequestMapping(value = "/add/publish", method = RequestMethod.POST)
    @ResponseBody
    public Object publishAdd(Param param, Article article, HttpServletRequest request) {
    	Map<String, Object> resultObject = new HashMap<>();
    	if(param==null || article==null) {
    		resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
    		return resultObject;
    	}
    	JSONArray emptyArray = new JSONArray();
    	article.setViewNum(0);
    	article.setUid(param.getUid());
    	article.setIsDel(Constant.IsDelete.NO);
    	article.setPraiseUid(emptyArray.toString());
    	article.setCollectionUid(emptyArray.toString());
    	
    	article.setCreateTime(System.currentTimeMillis());
    	article.setUpdateTime(0);
    	article.setPublishTime(System.currentTimeMillis());
    	article.setStatus(ArticleStatus.PUBLISH);
    	resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
    	return resultObject;
    }
    
    //---------------------------- 修改数据 -----------------------------------
    /**
     * 收藏
     * 
     * @author qianchun  @date 2016年2月26日 下午5:29:27
     * @param param
     * @param request
     * @return
     */
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    @ResponseBody
    public Object collect(Param param, HttpServletRequest request) {
        Map<String, Object> resultObject = new HashMap<>();
        
        if(param==null || param.getArticleid()==0) {
            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
            return resultObject;
        }
        long articleId = param.getArticleid();
        ServiceResult<Article> serviceResult = null;
        try {
            serviceResult = articleService.findById(articleId);
            if(!serviceResult.isSuccess() || serviceResult.getObject()==null) {
                resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
                return resultObject;
            }
            Article article = (Article) serviceResult.getObject();
            JSONArray collectionUidsJson = JSONArray.fromObject(article.getCollectionUid());
            if(collectionUidsJson==null) {
                collectionUidsJson = new JSONArray();
            }
            if(!collectionUidsJson.contains(articleId)) {
                collectionUidsJson.add(param.getUid());
                article.setCollectionUid(collectionUidsJson.toString());
                serviceResult = articleService.updateById(articleId, article);
            }
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
            return resultObject;
        }
        if(serviceResult!=null && serviceResult.isSuccess()) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
    
    /**
     * 点赞
     * 
     * @author qianchun  @date 2016年2月26日 下午5:30:22
     * @param param
     * @param request
     * @return
     */
    @RequestMapping(value = "/praise", method = RequestMethod.POST)
    @ResponseBody
    public Object praise(Param param, HttpServletRequest request) {
        Map<String, Object> resultObject = new HashMap<>();
        
        if(param==null || param.getUid()==0 || param.getArticleid()==0) {
            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
            return resultObject;
        }
        long articleId = param.getArticleid();
        long uid = param.getUid();
        ServiceResult<Article> serviceResult = null;
        try {
            serviceResult = articleService.findById(articleId);
            if(serviceResult==null || serviceResult.getObject()==null) {
                resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
                return resultObject;
            }
            Article article = (Article) serviceResult.getObject();
            JSONArray praiseUidsJson = JSONArray.fromObject(article.getPraiseUid());
            if(praiseUidsJson==null) {
                praiseUidsJson = new JSONArray();
            }
            if(!praiseUidsJson.contains(uid)) {
                praiseUidsJson.add(uid);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("praiseUid", praiseUidsJson.toString());
                params.put("id", articleId);
                serviceResult = articleService.updateById(articleId, article);
            }
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
            return resultObject;
        }
        if(serviceResult!=null && serviceResult.isSuccess()) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
    
    /**
     * 取消收藏
     * 
     * @author qianchun  @date 2016年2月26日 下午5:29:27
     * @param param
     * @param request
     * @return
     */
    @RequestMapping(value = "/collect/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Object collectCancel(Param param, HttpServletRequest request) {
        Map<String, Object> resultObject = new HashMap<>();
        
        if(param==null || param.getUid()==0 || param.getArticleid()==0) {
            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
            return resultObject;
        }
        long articleId = param.getArticleid();
        ServiceResult<Article> serviceResult = null;
        try {
            serviceResult = articleService.findById(articleId);
            if(serviceResult==null || serviceResult.getObject()==null) {
                resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
                return resultObject;
            }
            Article article = (Article) serviceResult.getObject();
            JSONArray collectionUidsJson = JSONArray.fromObject(article.getCollectionUid());
            if(collectionUidsJson!=null && collectionUidsJson.contains(articleId)) {
                collectionUidsJson.remove(param.getUid());
                article.setCollectionUid(collectionUidsJson.toString());
                serviceResult = articleService.updateById(articleId, article);
            }
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
            return resultObject;
        }
        if(serviceResult!=null && serviceResult.isSuccess()) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
    
    /**
     * 取消点赞
     * 
     * @author qianchun  @date 2016年2月26日 下午5:30:22
     * @param param
     * @param request
     * @return
     */
    @RequestMapping(value = "/praise/cancel", method = RequestMethod.POST)
    @ResponseBody
    public Object praiseCancel(Param param, HttpServletRequest request) {
        Map<String, Object> resultObject = new HashMap<>();
        
        if(param==null || param.getUid()==0 || param.getArticleid()==0) {
            resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
            return resultObject;
        }
        long articleId = param.getArticleid();
        long uid = param.getUid();
        ServiceResult<Article> serviceResult = null;
        try {
            serviceResult = articleService.findById(articleId);
            if(serviceResult==null || serviceResult.isSuccess()==false || serviceResult.getObject()!=null) {
                resultObject.put("meta", new Meta(MetaCode.PARAMS_ERROR, MetaMsg.PARAMS_ERROR));
                return resultObject;
            }
            Article article = serviceResult.getObject();
            JSONArray praiseUidsJson = JSONArray.fromObject(article.getPraiseUid());
            if(praiseUidsJson!=null && praiseUidsJson.contains(uid)) {
                praiseUidsJson.remove(uid);
                article.setPraiseUid(praiseUidsJson.toString());
                serviceResult = articleService.updateById(articleId, article);
            }
        }
        catch (Exception e) {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
            return resultObject;
        }
        if(serviceResult!=null && serviceResult.isSuccess()) {
            resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
        } else {
            resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
        }
        return resultObject;
    }
    //---------------------------- 查询数据 -----------------------------------
    
	@RequestMapping(value = "/my", method = RequestMethod.GET)
	@ResponseBody
	public Object my(Param param, HttpServletRequest request) {
		Map<String, Object> resultObject = new HashMap<>();
		List<Map<String, Object>> articleMapList = new ArrayList<>();

		//参数处理
		if(param.getPstart()==0) {
			param.setPstart(Constant.DEFAULT_PAGE_START);
		}
		if(param.getPlimit()==0) {
			param.setPlimit(Constant.DEFAULT_PAGE_LIMIT);
		}
		
		//根据用户id获取articleList
		List<Long> uids = new ArrayList<Long>();
		uids.add(param.getUid());
		ServiceResult<Article> articleServiceResult = articleService.findByUids(uids, param.getPstart(), param.getPlimit());
		if(articleServiceResult.isSuccess()==false) {
			resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
			return resultObject;
		}
		List<Article> articleList = (List<Article>) articleServiceResult.getList();
	    
		//获取article中的articleIds
		List<Long> articleIds = new ArrayList<>();
		if(articleList!=null) {
			for(int i=0; i<articleList.size(); i++) {
				Article article = articleList.get(i);
				if(article!=null) {
					articleIds.add(article.getId());
				}
			}
		}

		//获取评论列表
		ServiceResult<Comment> commentServiceResult = commentService.findMapByTargetIds(articleIds);
	    if(commentServiceResult.isSuccess()==false) {
	    	resultObject.put("meta", new Meta(MetaCode.FAIL, MetaMsg.FAIL));
			return resultObject;
	    }
	    Map<Long, List<Comment>> commentMap = commentServiceResult.getMap();
	    
		//获取用户信息map
		Map<Long, User> userMap = null;
		if(uids!=null && uids.size()>0) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("ids", uids);
			userMap = userService.findMap(params);
		}
	
		//封装map
		if(articleList!=null) {
			articleMapList = article2MapList(articleList, commentMap, userMap);
		}
	      
		//封装返回数据
		resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
		resultObject.put("data", articleMapList);
		return resultObject;
	  }
//  
//  @RequestMapping(value = "/all", method = RequestMethod.GET)
//  @ResponseBody
//  public Object all(Param param, HttpServletRequest request) {
//      long uid = param.getUid();
//      Map<String, Object> resultObject = new HashMap<>();
//      List<Map<String, Object>> articleMapList = new ArrayList<>();
//      
//      //根据用户id获取article
//      List<Long> uids = new ArrayList<Long>();
//      Map<String, Object> params = new HashMap<String, Object>();
//      params.put("uid", uid);
//      List<article> articleList = articleSqlService.findList(params);
//      
//      //获取article中的用户ids
//      if(articleList!=null) {
//          for(int i=0; i<articleList.size(); i++) {
//              article article = articleList.get(i);
//              if(article!=null) {
//                  uids.add(article.getUid());
//              }
//          }
//      }
//      
//      //根据用户ids获取用户信息
//      Map<Long, User> userMap = null;
//      if(uids!=null && uids.size()>0) {
//          params.clear();
//          params.put("ids", uids);
//          userMap = userService.findMap(params);
//      }
//
//      //封装map
//      if(articleList!=null) {
//          articleMapList = article2MapList(articleList, userMap);
//      }
//      
//      //封装返回数据
//      resultObject.put("meta", new Meta(MetaCode.SUCCESS, MetaMsg.SUCCESS));
//      resultObject.put("data", articleMapList);
//      return resultObject;
//  }
//  
//  /**
//   * 分頁查詢所有的article
//   * 
//   * @author qianchun  @date 2016年2月3日 下午7:07:05
//   * @param request
//   * @return
//   */
//  @RequestMapping(value = "/list", method = RequestMethod.GET)
//  @ResponseBody
//  public Object findPageList(HttpServletRequest request) {
//      Map<String, Object> params = new HashMap<String, Object>();
//      
//      params.put("uid", 1);
//      List<article> articleList = articleSqlService.findList(params);
//      JSONArray array = JSONArray.fromObject(articleList);
//      System.out.println(array);
//
//      
//      PageBounds pager = new PageBounds(1, 3);
//      PageList<article> articlePageList= (PageList<article>) articleSqlService.findPageList(null, pager);
//      return articlePageList;
//  }
//  //---------------------------- 结果封装map -----------------------------------
//    public List<Map<String, Object>> article2MapAll(List<Article> articleList, Map<Long, User> userMap) {
//        List<Map<String, Object>> articleMapList = new ArrayList<>();
//        if(articleList!=null) {
//            for(int i=0; i<articleList.size(); i++) {
//                Article article = articleList.get(i);
//                if(article!=null) {
//                    Map<String, Object> map = new HashMap<>();
//                    map.put("id", article.getId());
//                    map.put("status", article.getStatus());
//                    map.put("title", article.getTitle());
//                    map.put("content", article.getContent());
//                    
//                    map.put("view_num", article.getViewNum());
//                    map.put("praise_num", article.getViewNum());
//                    map.put("collection_num", article.getViewNum());
//                    
//                    map.put("praise_uid", article.getPraiseUid());
//                    map.put("collection_uid", article.getCollectionUid());
//
//                    map.put("uid", article.getUid());
//                    map.put("uname", article.getUid());
//                    map.put("head_image", article.getUid());
//                    
//                    map.put("create_time", article.getCreateTime());
//                    map.put("update_time", article.getUpdateTime());
//                    map.put("publish_time", article.getPublishTime());
//                }
//            }
//        }
//        return articleMapList;
//    }
    
    public List<Map<String, Object>> article2MapList(
    		List<Article> articleList, 
    		Map<Long, List<Comment>> commentMap, 
    		Map<Long, User> userMap) {
        List<Map<String, Object>> articleMapList = new ArrayList<>();
        if(articleList!=null) {
            for(int i=0; i<articleList.size(); i++) {
            	Article article = articleList.get(i);
                if(article!=null) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", article.getId());
                    map.put("content", article.getContent());
                    
                    map.put("status", article.getStatus());
                    map.put("view_num", article.getViewNum());
                    map.put("praise_num", JSONArray.fromObject(article.getPraiseUid()).size());
                    map.put("collection_num", JSONArray.fromObject(article.getCollectionUid()).size());

                    if(userMap!=null && userMap.containsKey(article.getUid())) {
                        User user = userMap.get(article.getUid());
                        map.put("uid", user.getId());
                        map.put("uname", user.getName());
                        map.put("head_image", user.getHeadImage());
                    } else {
                        map.put("uid", article.getUid());
                        map.put("uname", "");
                        map.put("head_image", "");
                    }
                    if(commentMap!=null && commentMap.containsKey(article.getId())) {
                    	List<Comment> comments = commentMap.get(article.getId());
                    	map.put("comments", comments);
                    } else {
                    	map.put("comments", new ArrayList<>());
                    }
                    
                    map.put("create_time", article.getCreateTime());
                    map.put("update_time", article.getUpdateTime());
                    map.put("publish_time", article.getPublishTime());
                }
            }
        }
        
        return articleMapList;
    }
}
