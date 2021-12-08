package com.huawei.utils;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BaseCanstants.java
 * @Description TODO
 * @createTime 2021年10月29日 15:06:00
 */
public class BaseCanstants {
    //------------------------------------------------------------图片上传配置信息--------------------------------------------------------
    /**
     * picture上传服务器路径
     */
    public static final String BASEFILEPATH = BasePropertiesUtils.getValue("baseFilePath");
    /**
     * picture上传项目路径
     */
    public static final String BASEPROJECTMANYPICTUREPATH = BasePropertiesUtils.getValue("baseProjectManyPicturePath");

    /**
     * 上传图片格式
     */
    public static final String[] ALLOWFILES_TYPE = { ".rar", ".doc", ".docx", ".zip",
            ".pdf", ".txt", ".swf", ".xlsx", ".gif", ".png", ".jpg", ".jpeg",
            ".bmp", ".xls", ".mp4", ".flv", ".ppt", ".avi", ".mpg", ".wmv",
            ".3gp", ".mov", ".asf", ".asx", ".vob", ".wmv9", ".rm", ".rmvb" };
    /**
     * 让问图片的mvc匹配路径
     */
    public static final String REQPICTUREPARTEN = BasePropertiesUtils.getValue("reqPictureParten");

    /**
     * 图片存放本地拼接访问路径参数
     */
    public static final String REQPICTUREPROJECTURL = BasePropertiesUtils.getValue("reqPictureProjectUrl");

    /**
     * 图片存放服务器访问路径参数
     */
    public static final String REQPICTURESERVERURL = BasePropertiesUtils.getValue("reqPictureServerUrl");

    //------------------------------------------------------------redis配置信息--------------------------------------------------------
    /**
     * redisIP地址
     */
    public static final String REDIS_HOST = BasePropertiesUtils.getValue("redis.host");

    /**
     * redis的端口
     */
    public static final String REIDS_PORT = BasePropertiesUtils.getValue("reids.port");
    /**
     * redis最大存活数
     */
    public static final String REDIS_MAX_ACTIVE = BasePropertiesUtils.getValue("redis.max.active");
    /**
     * redis最大空闲数
     */
    public static final String REDIS_MAX_IDLE = BasePropertiesUtils.getValue("redis.max.idle");
    /**
     * redis最小空闲数
     */
    public static final String REDIS_MIN_IDLE = BasePropertiesUtils.getValue("redis.min.idle");
    /**
     * redis等待时长
     */
    public static final String REDIS_MAXWAIT = BasePropertiesUtils.getValue("redis.maxWait");
    /**
     * redis最大连接数
     */
    public static final String REDIS_MAXTOTAL = BasePropertiesUtils.getValue("redis.maxTotal");
}
