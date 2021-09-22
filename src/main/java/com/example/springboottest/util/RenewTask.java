package com.example.springboottest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @类名称 HBRenewTask.java
 * @类描述 续租线程
 * @作者  庄梦蝶殇 linhuaichuan@naixuejiaoyu.com
 * @创建时间 2020年3月28日 下午3:46:20
 * @版本 1.0.0
 *
 * @修改记录
 * <pre>
 *     版本                       修改人 		修改日期 		 修改内容描述
 *     ----------------------------------------------
 *     1.0.0 		庄梦蝶殇 	2020年3月28日             
 *     ----------------------------------------------
 * </pre>
 */
public class RenewTask extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(RenewTask.class);
    
    public volatile boolean isRunning = true;
    
    /**
     * 过期时间，单位s
     */
    private int ttl;
    
    private IRenewalHandler call;
    
    public RenewTask(IRenewalHandler call, int ttl) {
        this.ttl = ttl <= 0 ? 10 : ttl;
        this.call = call;
    }
    
    @Override
    public void run() {
        while (isRunning) {
            try {
                // 1、续租，刷新值
                call.callBack();
                LOGGER.debug("续租成功!");
                // 2、三分之一过期时间续租
                TimeUnit.SECONDS.sleep(this.ttl * 1000 / 3);
            } catch (InterruptedException e) {
                close();
            } catch (Exception e) {
                close();
            }
        }
    }
    
    public void close() {
        isRunning = false;
    }
}
