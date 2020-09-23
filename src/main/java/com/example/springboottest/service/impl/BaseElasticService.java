package com.example.springboottest.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


public interface BaseElasticService {

    public boolean isExistsIndex(String index) throws Exception;

    boolean indexExist(String idxName) throws Exception;

    void createIndex(String idxName, String idxSql) throws Exception;

    void deleteIndex(String index);
}
