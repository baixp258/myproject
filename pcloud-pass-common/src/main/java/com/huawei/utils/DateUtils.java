package com.huawei.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName DateUtils.java
 * @Description TODO
 * @createTime 2021年10月29日 15:24:00
 */
public class DateUtils {

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowTime = sdf.format(new Date());
        return nowTime;
    }
}
