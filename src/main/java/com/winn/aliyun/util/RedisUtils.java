package com.winn.aliyun.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Collections;

public class RedisUtils {

    private static Logger log = LogManager.getLogger(RedisUtils.class);

    private static Jedis jedis;

    public RedisUtils() {
        JedisPool pool = getPool();
        jedis=pool.getResource();
    }

    private JedisPool getPool(){
        JedisPool pool=null;
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt("50"));
        config.setMaxTotal(Integer.parseInt("1000"));
        config.setMaxWaitMillis((long)Integer.parseInt("3000"));
        config.setTestOnBorrow(Boolean.parseBoolean("true"));
        config.setTestOnReturn(Boolean.parseBoolean("true"));
        config.setTimeBetweenEvictionRunsMillis(-1L);
        String ip="";
        int port=0;
        String password="";
        pool=new JedisPool(config,ip,port,2000,password);
        return pool;
    }

    //TODO
    private static String host = "";
    private static int port = 0;
    private static String auth = ""; //密码

    private static final String LOCK_SUCCESS = "OK";

    private static final String SET_IF_NOT_EXIST = "NX";

    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * Description: 获取锁
     *
     * @Date: 2020/6/11
     */
    public static boolean getDistributedLock(String lockKey, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = new Jedis(host, port);
            jedis.auth(auth);
            String result = jedis.set(lockKey, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
        } finally {
            if (null != jedis && jedis.isConnected()) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * Description: 释放锁
     * @Date: 2020/6/11
     */
    public static boolean releaseLock(String lockKey, String value) {
        Jedis jedis = null;
        try {
            jedis = new Jedis(host, port);
            jedis.auth(auth);
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(value));
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
        } finally {
            if (null != jedis && jedis.isConnected()) {
                jedis.close();
            }
        }
        return false;
    }
}
