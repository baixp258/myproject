package com.huawei.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取配置文件中配置的属性值
 * 获取配置文件中配置的属性值
 * 
 * @author  hwx230076
 * @version  [版本号, 2014-12-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BasePropertiesUtils
{
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePropertiesUtils.class);
    
    private static Properties pro = new Properties();
    static
    {
        pro = loadProperties("baseConfig.properties");
    }
    
    /**
     * 获取对应key的值
     * 
     * @param key
     * @return
     */
    public static String getValue(String key)
    {
        return pro.getProperty(key);
    }
    
    /**
     * 获取对应key的值
     * 
     * @param key
     * @param defaultValue 默认值
     * @return
     */
    public static String getValue(String key, String defaultValue)
    {
        return pro.getProperty(key, defaultValue);
    }
    
    /**
     * 加载配置文件
     * 加载配置文件
     * @param path
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Properties loadProperties(String path)
    {
        InputStream in = null;
        /**构建配置文件对象*/
        Properties p = new Properties();
        try
        {
            /**加载配置文件到文件流*/
            if (null != BasePropertiesUtils.class.getClassLoader())
            {
                in = BasePropertiesUtils.class.getClassLoader().getResourceAsStream(path);
                /**加载配置文件*/
                p.load(in);
            }
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage());
        }
        finally
        {
            /**处理异常*/
            if (null != in)
            {
                IOUtils.closeQuietly(in);
            }
        }
        return p;
    }
}
