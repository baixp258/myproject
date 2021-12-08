package com.huawei.utils;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName RedisUtils.java
 * @Description TODO
 * @createTime 2021年11月09日 10:53:00
 */
public class RedisUtils {


    /**
     * 私有构造函数
     */
    private RedisUtils(){}

    //声明单例
    private static JedisPool jedisPool;

    //获取redis实例
    public static JedisPool getJedis(){
        if(jedisPool==null){
          synchronized (RedisUtils.class){
              if(jedisPool==null){
                   try {
                       jedisPool = getJedisPool();
                   }catch (Exception e){
                       e.printStackTrace();
                       if(jedisPool!=null){
                           jedisPool.close();
                       }
                   }
              }
          }
        }
        return jedisPool;
    }
    //获取jedispool连接池
    private static JedisPool getJedisPool() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(Integer.valueOf(BaseCanstants.REDIS_MAX_IDLE));
        poolConfig.setMinIdle(Integer.valueOf(BaseCanstants.REDIS_MIN_IDLE));
        poolConfig.setMaxWaitMillis(Integer.valueOf(BaseCanstants.REDIS_MAXWAIT));
        poolConfig.setMaxTotal(Integer.valueOf(BaseCanstants.REDIS_MAXTOTAL));
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestOnCreate(false);
        jedisPool = new JedisPool(poolConfig,BaseCanstants.REDIS_HOST,Integer.valueOf(BaseCanstants.REIDS_PORT));
        return jedisPool;
    }
}
