package com.wind.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 记录请求日志
 * 
 * @author qianchun
 * @date 2015年11月23日 下午4:13:04
 */
public class BigDataLog {
    private final static Logger logger = LoggerFactory.getLogger(BigDataLog.class);

//    public static void printStatisticsLog(FeedDto feedDto, String url, Date startDate) {
//        String operationType = getOperationType(url);
//        if (Constants.MethodType.VISIT.equals(operationType)) {
//            //  1、日志类型(visit) 2、访问人ID  3、访问起始时间（yyyy-MM-dd hh:mm:ss） 4、请求的URL  
//            //  5、APP类型  6、设备ID 7、OS型号  8、OS版本号 9、网络环境 10、APP版本号
//            StringBuilder sb = new StringBuilder();
//            sb.append(Constants.LogType.VISIT);
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getUid());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(DateUtil.format(startDate, null));
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(url);
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getApp());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getDevice_id());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getDevice_model());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getOsVersion());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getNetwork());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getVersion());
//            logger.info(sb.toString());
//        }
//        else if (Constants.MethodType.BROWSE.equals(operationType)) {
//            //  1、日志类型(browse)  2、用户ID  3、时间（yyyy-MM-dd hh:mm:ss） 4、被查看用户的ID 
//            //  5、被查看对象的ID 6、浏览的内容的类型 7、APP类型 8、设备ID 
//            //  9、OS型号 10、OS版本号 11、网络环境 12、APP版本号 13、请求的URL
//            StringBuilder sb = new StringBuilder();
//            sb.append(Constants.LogType.BROWSE);
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getUid());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(DateUtil.format(startDate, null));
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getOtherId());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getId());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append("unknow");
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getApp());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getDevice_id());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getDevice_model());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getOsVersion());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getNetwork());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getVersion());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(url);
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append("unknow");
//            logger.info(sb.toString());
//        }
//        else if (Constants.MethodType.SEARCH.equals(operationType)) {
//            //  1、日志类型（search）2、用户ID  3、搜索时间（yyyy-MM-dd hh:mm:ss） 
//            //  4、请求的操作（搜索关键词、点击热门标签、点击关键词、点击专家 ） 5、APP类型 
//            //  6、设备ID 7、OS型号 8、OS版本号 9、网络环境 
//            //  10、 APP版本号  11、搜索的类型  12、请求的URL
//
//            String keyword = feedDto.getTagName() == null
//                    ? (feedDto.getKeyword() == null ? "" : feedDto.getKeyword())
//                            : feedDto.getTagName();
//
//            StringBuilder sb = new StringBuilder();
//            sb.append(Constants.LogType.SEARCH);
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getUid());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(DateUtil.format(startDate, null));
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(keyword);
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getApp());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getDevice_id());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getDevice_model());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getOsVersion());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getNetwork());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getVersion());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getSearchfrom());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(url);
//
//            logger.info(sb.toString());
//        }
//        else if (Constants.MethodType.CLICK.equals(operationType)) {
//            //  1、日志类型（chickAfterSearch）2、用户ID  3、点击时间 （yyyy-MM-dd hh:mm:ss）4、请求的操作（点击标题）  
//            //  5、APP类型 6、设备ID  7、OS型号 8、OS版本号 9、网络环境 10、 APP版本号  12、请求的URL
//            StringBuilder sb = new StringBuilder();
//            sb.append(Constants.LogType.CLICK_AFTER_SEARCH);
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getUid());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(DateUtil.format(startDate, null));
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append((!StringUtils.isEmpty(feedDto.getKeyword()) ? feedDto.getKeyword() : url));
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getApp());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getDevice_id());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getDevice_model());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getOsVersion());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getNetwork());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(feedDto.getClient_info().getVersion());
//            sb.append(Constants.LogSeparator.SEPARATOR);
//            sb.append(url);
//            logger.info(sb.toString());
//        }
//    }
//
//    public static String getOperationType(String url) {
//        //----------------------- UserController -----------------------
//        if (url.equals("/user/classify")) {
//            //分类检索
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/contactPeople")) {
//            //分类检索-二级
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/clear")) {
//            //分类检索
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/newIncoming")) {
//            //新的机友
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/newIncomingCount")) {
//            //新的机友--数量
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/relative")) {
//            //群详情结果(相关的人)
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/user/hotUser")) {
//            //发现-热点人物
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/commonClue")) {
//            //转换uids 为关系线索
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/hot")) {
//            //热点人物--运营备选
//            return "";
//        }
//        else if (url.equals("/user/freshUser")) {
//            //发现-新鲜人脉
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/businessCardClue")) {
//            //查看某人超级名片--关系及共同标签
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/chatClue")) {
//            //聊天窗口显示两个用户之间的关系
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/findUserForOper")) {
//            //聊天窗口显示两个用户之间的关系
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/calcInfo")) {
//            //一人与多人间关系
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/circleStat")) {
//            //圈子统计功能 unused util 2015-09-10
//            return "";
//        }
//        else if (url.equals("/user/findForGroup")) {
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/user/enterGroup")) {
//            //发起和进入群聊
//            return Constants.MethodType.VISIT;
//        }
//        //----------------------- FeedController -----------------------
//        else if (url.equals("/feed/friendFeed")) {
//            //用户的feed流信息 - 查看某个人的动态
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/feed/myFeed")) {
//            //我的所有动态
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/feed/hotActivity")) {
//            //热门活动
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/feed/freshFeeds")) {
//            //最新动态
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/feed/successFeeds")) {
//            //成功故事
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/feed/unread")) {
//            //未读动态数量
//            return "";
//        }
//        else if (url.equals("/feed/activityDetail")) {
//            //活动详情
//            return Constants.MethodType.BROWSE;
//        }
//        else if (url.equals("/feed/articleDetail")) {
//            //文章详情
//            return Constants.MethodType.BROWSE;
//        }
//        else if (url.equals("/feed/allFeed")) {
//            //用户的feed流信息 - 查看所有人的动态
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/feed/success/search")) {
//            //用户的feed流信息 - 搜索成功的故事
//            return "";}
//        else if (url.equals("/feed/activity/search")) {
//            //用户的feed流信息 - 热门活动搜索
//            return Constants.MethodType.VISIT;
//        }
//        //----------------------- TagController -----------------------
//        else if (url.equals("/tag/hotTag")) {
//            //热门标签列表 -mysql 用于app读取
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/tag/interestedPerson")) {
//            //感兴趣的人
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/secondHotTag")) {
//            //热门标签二级页面
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/hot")) {
//            //用于运营备选热门标签
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/relative")) {
//            //标签结果页(相关的人)
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/search")) {
//            //搜索
//            return Constants.MethodType.SEARCH;
//        }
//        else if (url.equals("/tag/relativePeople")) {
//            //搜索-相关的人
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/relativeGroup")) {
//            //搜索-相关的群
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/relativeTag")) {
//            //相关标签
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/friendsTogether")) {
//            //好友同在
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/commonTagPeople")) {
//            //同标签的人
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/detail")) {
//            //标签详情
//            return Constants.MethodType.CLICK;
//        }
//        else if (url.equals("/tag/top")) {
//            //热门标签列表 -solr -用于运营选取 unused util 2015-09-10
//            return "";
//        }
//        //-----------------------NoticeController -----------------------
//        else if (url.equals("/notice/new")) {
//            //动态通知
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/notice/new/list")) {
//            //动态通知
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/notice/count")) {
//            //动态通知数量
//            return "";
//        }
//        //--------------------- NavigationController -----------------------
//        else if (url.equals("/navigation/banners")) {
//            //人脉导航栏 banner
//            return Constants.MethodType.VISIT;
//        }
//        //----------------------- FoundController -----------------------
//        else if (url.equals("/found/home")) {
//            //分类检索
//            //            return "/found/home";
//            return Constants.MethodType.VISIT;
//        }
//        //----------------------- GeoController -----------------------
//        else if (url.equals("/geo/findGeo")) {
//            //查找附近的人
//            //            return "/geo/findGeo";
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/geo/sameIndustry")) {
//            //同行业的人
//            //            return "/geo/sameIndustry";
//            return Constants.MethodType.VISIT;
//        }
//        //----------------------- searchController -----------------------
//        else if (url.equals("/search/getStaticResource")) {
//            //静态资源
//            //            return "/search/getStaticResource";
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/search/searchFriends")) {
//            //搜索好友
//            //            return "/search/searchFriends";
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/search/searchusers")) {
//            //搜索用户
//            //            return "/search/searchusers";
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/search/searchsourcebytag")) {
//            //搜索
//            //            return "/search/searchsourcebytag";
//            return Constants.MethodType.VISIT;
//        }
//        else if (url.equals("/search/recommendpeople")) {
//            //推荐用户  (可能认识的人)
//            //            return "/search/recommendpeople";
//            return Constants.MethodType.VISIT;
//        }
//        else {
//            return "";
//        }
//    }
}
