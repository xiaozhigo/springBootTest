package com.example.springboottest.run;

import com.example.springboottest.config.BloomFilterConfig;
import com.example.springboottest.mysql.TestDao;
import com.google.common.hash.BloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 启动
 */
@Component
@Order(-2147483647)
@Slf4j
public class TestRun implements ApplicationRunner{

    @Autowired
    private BloomFilterConfig bloomFilterConfig;
    @Autowired
    private TestDao testDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BloomFilter bloomFilter = bloomFilterConfig.getBloomFilter();
        List<Map<String, Object>> maps = testDao.queryAllUser();
        maps.forEach(map ->{
            bloomFilter.put(map.get("user_id"));
        });
    }
}
