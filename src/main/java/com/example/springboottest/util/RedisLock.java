package com.example.springboottest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketTimeoutException;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

public class RedisLock {

    private static Logger logger = LoggerFactory.getLogger("redis-lock");

    private final static String RESULT_OK = "OK";

    /**
     * 默认过期时间，单位s
     */
    protected final static int DEFAULT_SENCOND = 10;

    /**
     * 锁名
     */
    protected String key;

    /**
     * 锁值
     */
    protected String value;

    /**
     * 过期时间，单位s
     */
    protected Integer ttl;

    /**
     * 是否支持锁重入
     */
    protected boolean reentrant;

    /**
     * 默认缓存值
     */
    protected final static String DEF_VAL = "1";

    RedisUtil redisUtil;
    /**
     * 续租线程
     */
    RenewTask renewalTask;

    /**
     * 是否已经持有锁
     */
    protected final AtomicBoolean hold = new AtomicBoolean(false);

    /**
     * 活跃重入锁
     */
    protected static final ThreadLocal<HashSet<String>> holdLocks = new ThreadLocal<HashSet<String>>() {
        @Override
        protected HashSet<String> initialValue() {
            return new HashSet<String>();
        }
    };

    public RedisLock(String key, String value, boolean reentrant) {
        this.key = key;
        this.value = value;
        this.ttl = DEFAULT_SENCOND;
        this.reentrant = reentrant;
    }

    public RedisLock(RedisUtil redisUtil, String key, boolean reentrant) {
        this(key, DEF_VAL, reentrant);
        this.redisUtil = redisUtil;
    }

    protected boolean lock()
            throws Exception {
        try {
            // 抢锁
            if (RESULT_OK.equals(redisUtil.setNxPx(key, value, this.ttl))) {
                // 上报监控
                // NxMonitor.sum(30, 1);
                renewalTask = new RenewTask(new IRenewalHandler() {
                    @Override
                    public void callBack()
                            throws Exception {
                        // 刷新值
                        redisUtil.expire(key, ttl <= 0 ? 10 : ttl);
                    }
                }, ttl);
                renewalTask.setDaemon(true);
                renewalTask.start();
                hold.set(true);
            } else {
                hold.set(false);
            }
        } catch (Exception e) {
            hold.set(false);
            Throwable cause = e.getCause();
            if (cause instanceof SocketTimeoutException) {
                return hold.get();
            }
            logger.error("Error encountered when attempting to acquire lock", e);
            throw e;
        } finally {
            if (reentrant && hold.get()) {
                holdLocks.get().add(key);
            }
        }
        return hold.get();
    }


    public void release()
            throws Exception {
        if (hold.get()) {
            try {
                hold.set(false);
                redisUtil.delete(key, value);
                holdLocks.get().remove(key);
            } finally {
                if (renewalTask != null) {
                    renewalTask.close();
                }
            }
        }
    }

    protected Object getClient() {
        return redisUtil;
    }
}
