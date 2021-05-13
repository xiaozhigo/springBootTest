package com.example.springboottest.service.impl;

import com.example.springboottest.dto.ElasticEntity;
import com.example.springboottest.dto.TbUserDto;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.util.List;


public interface BaseElasticService {

    public boolean isExistsIndex(String index) throws Exception;

    boolean indexExist(String idxName) throws Exception;

    void createIndex(String idxName, String idxSql) throws Exception;

    void deleteIndex(String index);

    <T> List<T> search(String idxName, SearchSourceBuilder sourceBuilder, Class<T> c);

    void insertOrUpdateOne(String index, ElasticEntity elasticEntity);
}
