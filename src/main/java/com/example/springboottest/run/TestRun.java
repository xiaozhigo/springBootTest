package com.example.springboottest.run;

import com.example.springboottest.config.BloomFilterConfig;
import com.google.common.hash.BloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(-2147483647)
@Slf4j
public class TestRun implements ApplicationRunner{

    @Autowired
    private BloomFilterConfig bloomFilterConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BloomFilter bloomFilter = bloomFilterConfig.getBloomFilter();
        bloomFilter.put("1");
        if(bloomFilter.mightContain("1")){
           log.info("存在");
        }else{
            log.info("不存在");
        }
    }
}
