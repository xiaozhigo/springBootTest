package com.example.springboottest.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.example.springboottest.config.BloomFilterConfig;
import com.example.springboottest.config.ExcelListener;
import com.example.springboottest.dto.*;
import com.example.springboottest.service.Test4Service;
import com.example.springboottest.service.impl.HttpAPIService;
import com.google.common.hash.BloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@Slf4j
public class Test4 {

    @Autowired
    private HttpAPIService httpAPIService;
    @Autowired
    private Test4Service test4Service;
    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @Autowired
    private BloomFilterConfig bloomFilterConfig;

    @RequestMapping("/test1")
    public void test1() {
        try {
            //query 检索关键字 tag检索偏好 region 检索行政区划区域 output=json ak百度开发者访问密钥
            log.info("~~~~~~~~~日志测试开始~~~~~~~");
            String s = httpAPIService.doGet("http://api.map.baidu.com/place/v2/search?query=ATM机&tag=银行&region=北京&output=json&ak=stlbPbu14MAXvcIMoR39D8vfQycPFGil");
            log.info("~~~~~~~~~日志测试结束~~~~~~~");
            log.info(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务处理设置
     *
     * @return
     */
    @RequestMapping("/transactionTest")
    public void transactionTest(@RequestBody UserDto userDto) {
        test4Service.transactionTest(userDto);
    }

    @RequestMapping("/setValue")
    public void setValue(@RequestBody RedisParam redisParam) {
        /*redisTemplate.opsForValue().set(redisParam.getKey(), redisParam.getValue());*/
    }

    /**
     * 通过布隆过滤器处理缓存穿透
     */
    @RequestMapping("/bloomFilterTest")
    public TbUserDto bloomFilterTest(@RequestBody String userId) {
        TbUserDto user = new TbUserDto();
        BloomFilter bloomFilter = bloomFilterConfig.getBloomFilter();
        boolean flag = bloomFilter.mightContain(userId);
        //布隆过滤器中存在
        if (flag) {
           /* Object o = redisTemplate.opsForValue().get(userId);
            if (StringUtils.isEmpty(o)) {
                user = test4Service.queryUserById(userId);
                return user;
            }else{
                user.setUserId(Integer.parseInt(o.toString()));
                return user;
            }*/
        }
        return user;
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = null;
        try {
        fileName = URLEncoder.encode("用户统计", "UTF-8");
        List<ExcelDto> list = new ArrayList<>();
        ExcelDto excelDto = new ExcelDto();
        excelDto.setUserId("1");
        excelDto.setUserName("二哈");
        list.add(excelDto);
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-Disposition", "attachment;filename="+fileName+".xlsx");
        EasyExcel.write(outputStream,ExcelDto.class).sheet("用户sheet").doWrite(list);
        outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/importExcel")
    public void importExcel(@RequestParam(value = "file") MultipartFile multipartFile){
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //实例化实现了AnalysisEventListener接口的类
        ExcelListener listener = new ExcelListener();
        EasyExcel.read(inputStream,ExcelDto.class,listener).sheet("用户sheet").doRead();
        List<Object> datas = listener.getDatas();
        datas.forEach(e ->{
            System.out.println(e);
        });
    }

    @GetMapping("/timeTest")
    @ResponseBody
    public DateDto timeTest() {
        DateDto order = new DateDto();
        order.setLocalDateTime(LocalDateTime.now());
        order.setDate(new Date());
        return order;
    }
}
