package com.wind.commons;


/**
 * 常量
 * 
 * @author qianchun
 * @date 2015年11月19日 上午11:06:19
 */
public class Constant {

    /**
     * 请求状态
     * 
     * @author qianchun
     * @date 2015年11月19日 上午11:06:45
     */
    public class RequestStatus {
        public static final String SUCCESS_CODE = "0";
        public static final String PARAMS_ERROR_CODE = "1001";
        public static final String SYSTEM_ERROR_CODE = "1002";
        public static final String GROUP_MSG_NO_CODE = "1004";
        public static final String SYSTEM_NOT_LOGIN_CODE = "1005";
        public static final String SYSTEM_USER_NO_RIGHT_CODE = "1006";

        public static final String SUCCESS_MSG = "成功";
        public static final String PARAMS_ERROR_MSG = "参数不正确";
        public static final String SYSTEM_ERROR_MSG = "系统错误";
        public static final String GROUP_MSG_NO_MSG = "目前没有群聊信息";
        public static final String SYSTEM_NOT_LOGIN_MSG = "请先登录";
        public static final String SYSTEM_USER_NO_RIGHT_MSG = "该用户没权限";
    }

    /**
     * redis缓存时间（单位秒）
     * 
     * @author qianchun
     * @date 2015年11月19日 上午11:43:37
     */
    public class RedisTimeout {
        public static final int SECONDS_TEN = 10;
        public static final int SECONDS_FIFTEEN = 15;
        public static final int SECOND_THIRTY = 30;

        public static final int MINUTES_TEN = 10 * 60;
        public static final int MINUTES_FIFTEEN = 15 * 60;
        public static final int MINUTES_THIRTY = 30 * 60;

        public static final int HOURS_ONE = 1 * 60 * 60;
        public static final int HOURS_TWO = 2 * 60 * 60;
        public static final int HOURS_THREE = 30 * 60;
        public static final int HOURS_SIX = 6 * 60 * 60;
        public static final int HOURS_TEN = 10 * 60 * 60;
        public static final int HOURS_TWELVE = 12 * 60 * 60;
        public static final int HOURS_EIGHTEEN = 18 * 60 * 60;

        public static final int DAY_ONE = 1 * 24 * 60 * 60;
        public static final int DAY_TWO = 2 * 24 * 60 * 60;
        public static final int DAY_THREE = 3 * 24 * 60 * 60;
        public static final int DAY_SEVEN = 7 * 24 * 60 * 60;
    }
    
    /**
     * 状态
     * 
     * @author qianchun
     * @date 2016年1月25日 下午4:53:31
     */
    public class Success {
        public static final int FALSE = 0;
        public static final int TRUE = 1;
    }
    
    public class Single {
        public static final int FALSE = 0;
        public static final int TRUE = 1;
    } 
    
    public class UserStatus {
        public static final int DISABLE = 0;
        public static final int ENABLE = 1;
    }
    
    public class Status {
        public static final int DELETE_YES = 0;
        public static final int DELETE_NO = 1;
    }
    
    public class Sex {
        public static final int FEMALE = 0;
        public static final int MALE = 1;
    }
    
    //耗时日志模板
    public static final String LOG_UID_URL_DESC_COSTTIME = "用户uid:{}; 请求url:{}; 接口描述:{}; 耗时{}毫秒";
    
}
