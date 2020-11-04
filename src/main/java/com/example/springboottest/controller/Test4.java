package com.example.springboottest.controller;

import com.alibaba.excel.EasyExcel;
import com.example.springboottest.config.BloomFilterConfig;
import com.example.springboottest.dto.ExcelDto;
import com.example.springboottest.dto.RedisParam;
import com.example.springboottest.dto.TbUserDto;
import com.example.springboottest.dto.UserDto;
import com.example.springboottest.service.Test4Service;
import com.example.springboottest.service.impl.HttpAPIService;
import com.google.common.hash.BloomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class Test4 {

    @Autowired
    private HttpAPIService httpAPIService;
    @Autowired
    private Test4Service test4Service;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BloomFilterConfig bloomFilterConfig;

    @RequestMapping("/test1")
    public void test1() {
        try {
            //query 检索关键字 tag检索偏好 region 检索行政区划区域 output=json ak百度开发者访问密钥
            String s = httpAPIService.doGet("http://api.map.baidu.com/place/v2/search?query=ATM机&tag=银行&region=北京&output=json&ak=stlbPbu14MAXvcIMoR39D8vfQycPFGil");
            System.out.println(s);
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
        redisTemplate.opsForValue().set(redisParam.getKey(), redisParam.getValue());
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
            Object o = redisTemplate.opsForValue().get(userId);
            if (StringUtils.isEmpty(o)) {
                user = test4Service.queryUserById(userId);
                return user;
            }else{
                user.setUserId(Integer.parseInt(o.toString()));
                return user;
            }
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
        String fileName = "用户统计";
        List<ExcelDto> list = new ArrayList<>();
        ExcelDto excelDto = new ExcelDto();
        excelDto.setUserId("1");
        excelDto.setUserName("二哈");
        list.add(excelDto);
        response.setContentType("application/vnd.ms-exce");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Content-Disposition", "filename=" + fileName + ".xlsx");
        EasyExcel.write(outputStream,ExcelDto.class).sheet("用户sheet").doWrite(list);
    }
}
