package com.example.springboottest.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springboottest.dto.ElasticEntity;
import com.example.springboottest.dto.IdxVo;
import com.example.springboottest.dto.TbUserDto;
import com.example.springboottest.service.impl.BaseElasticService;
import com.example.springboottest.util.ResponseResult;
import com.example.springboottest.util.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class ESController {

    @Autowired
    private BaseElasticService baseElasticService;

    /**
     * @Description 判断索引是否存在；存在-TRUE，否则-FALSE
     * @param index
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/19 18:48
     */
    @GetMapping(value = "/exist/{index}")
    public ResponseResult indexExist(@PathVariable(value = "index") String index){

        ResponseResult response = new ResponseResult();
        try {
            if(!baseElasticService.isExistsIndex(index)){
                log.error("index={},不存在",index);
                response.setCode(ResponseStatus.索引不存在.getStauts());
                response.setMsg(ResponseStatus.索引不存在.getMsg());
            } else {
                response.setMsg(" 索引已经存在, " + index);
            }
        } catch (Exception e) {
            response.setCode(ResponseStatus.调用ElasticSearch失败.getStauts());
            response.setMsg(" 调用ElasticSearch 失败！");
        }
        return response;
    }

    /**
     * @Description 创建Elastic索引
     * @param idxVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/19 11:07
     */
    @RequestMapping(value = "/createIndex",method = RequestMethod.POST)
    public ResponseResult createIndex(@RequestBody IdxVo idxVo){
        ResponseResult response = new ResponseResult();
        try {
            //索引不存在，再创建，否则不允许创建
            if(baseElasticService.indexExist(idxVo.getIdxName())){
                String idxSql = JSONObject.toJSONString(idxVo.getIdxSql());
                log.warn(" idxName={}, idxSql={}",idxVo.getIdxName(),idxSql);
                baseElasticService.createIndex(idxVo.getIdxName(),idxSql);
            } else{
                response.setCode(ResponseStatus.索引已经存在.getStauts());
                response.setMsg("索引已经存在，不允许创建");
            }
        } catch (Exception e) {
            response.setCode(ResponseStatus.调用ElasticSearch失败.getStauts());
            response.setMsg(" 调用ElasticSearch 失败！");
        }
        return response;
    }

    /**
     * 删除索引
     * @param index
     */
    @GetMapping(value = "/deleteIndex/{index}")
    public void deleteIndex(@PathVariable(value = "index") String index){
        baseElasticService.deleteIndex(index);
    }

    /**
     * 新增数据
     * @param index
     * @param elasticEntity
     */
    @PostMapping(value = "/insertOrUpdateOne/{index}")
    public void insertOrUpdateOne(@PathVariable(value = "index") String index,@RequestBody ElasticEntity elasticEntity){
        baseElasticService.insertOrUpdateOne(index,elasticEntity);
    }


    /**
     * 查询
     * @param elasticEntity
     * @return
     */
    @PostMapping(value = "/queryMatch")
    public String queryMatch(@RequestBody ElasticEntity elasticEntity){
        LinkedHashMap map = (LinkedHashMap) elasticEntity.getData();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("userName.keyword", map.get("userName"));
		//MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        sourceBuilder.query(termQueryBuilder);
//		//分页
//		sourceBuilder.from();
//		sourceBuilder.size();
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        List<TbUserDto> tbUserDtoList = baseElasticService.search("testindex2", sourceBuilder, TbUserDto.class);
        return JSONObject.toJSONString(tbUserDtoList);
    }
}
