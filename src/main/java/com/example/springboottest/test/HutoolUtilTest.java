package com.example.springboottest.test;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

public class HutoolUtilTest {

    @Test
    public void test1(){
        BigDecimal decimal = new BigDecimal(10.21);
        String chinese = Convert.digitToChinese(decimal);
        System.out.println(chinese);
    }

    @Test
    public void dateTest(){
        String now = DateUtil.now();
        Date date = DateUtil.date();
        String today = DateUtil.today();
        DateTime time = DateUtil.parse(today);
        String format = DateUtil.format(time, "yyyy/MM/dd");
        DateTime dateTime = DateUtil.offset(time, DateField.DAY_OF_MONTH, 2);
        DateTime dateTime1 = DateUtil.offsetDay(date, 10);
        long between = DateUtil.between(dateTime1, date, DateUnit.DAY);
        System.out.println(now);
        System.out.println(date);
        System.out.println(today);
        System.out.println(time);
        System.out.println(format);
        System.out.println(dateTime);
        System.out.println(dateTime1);
        System.out.println(between);
    }

    @Test
    public void hexTest(){
        String str = "呼呼哈哈";
        String encodeHexStr = HexUtil.encodeHexStr(str);
        String decodeHexStr = HexUtil.decodeHexStr(encodeHexStr);
        System.out.println(encodeHexStr);
        System.out.println(decodeHexStr);
    }

    @Test
    public void hashTest(){
        String url1 = "baidu";
        String url2 = "google";
        int i1 = HashUtil.rotatingHash(url1, 10);
        int i2 = HashUtil.rotatingHash(url2, 10);
        System.out.println(i1);
        System.out.println(i2);
    }

    @Test
    public void idTest(){
        String id = IdUtil.objectId();
        System.out.println(id);
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        System.out.println(snowflake.nextId());
    }

    @Test
    public void httpUtilTest(){
        /*String blibli = HttpUtil.get("https://www.baidu.com");
        System.out.println(blibli);*/
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("netNo", "wechat");
        paramMap.put("token", "f06c44a1b2d74bf9b882f85c339dcfa01635140277996");
        paramMap.put("fundType", "0");
        Gson gson = new Gson();
        String json = gson.toJson(paramMap);
        String body = HttpRequest.post("http://10.134.0.135/api/query/queryCustFundInfoSimple").body(json).execute().body();
        System.out.println(body);
    }

    @Test
    public void QrCodeUtilTest(){
        QrCodeUtil.generate("https://www.fundhaiyin.com/etrading/", 300, 300, FileUtil.file("d:/qrcode.jpg"));
        String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcode.jpg"));
        System.out.println(decode);
    }

    @Test
    public void CaptchaTest(){
        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(300, 100,6,6);
        shearCaptcha.write("d:/ui-lgreen.jpg");
        //输出code
        Console.log(shearCaptcha.getCode());
        //验证图形验证码的有效性，返回boolean值
        boolean verify = shearCaptcha.verify("01si8");
        System.out.println(verify);
    }
}
