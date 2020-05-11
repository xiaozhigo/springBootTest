package com.example.springboottest.service.impl;

import com.example.springboottest.util.Gsons;
import com.example.springboottest.util.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: HU_zhenwei
 * @Date: 2018/10/1811:11 AM
 * @Descripton:
 */
@Service
@Slf4j
public class HttpAPIService {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    private static String encoding = "utf-8";


    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());

    }


    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public ResponseResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (map != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new ResponseResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), encoding));
    }

    /**
     * 带参数的post请求
     *  json格式参数
     * @param url
     * @param objectToJson
     * @return
     * @throws Exception
     */
    public String doJsonPost(String url, Object objectToJson) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);
        String jsonSring = null;
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (objectToJson != null) {
            jsonSring = Gsons.gsons().toJson(objectToJson);
            //装填参数
            StringEntity stringEntity = null;
            stringEntity = new StringEntity(jsonSring,encoding);
            httpPost.setEntity(stringEntity);

        }
        log.info("创建请求httpsPost-URL={},params={}", url, jsonSring);
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-Type", "application/json;charset="+encoding);
        //httpPost.setHeader("Content-Length", params.length() + "");
        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        log.info("返回code={}",response.getStatusLine().getStatusCode());
        if (200 !=response.getStatusLine().getStatusCode()){
            return null;
        }
        return EntityUtils.toString(response.getEntity(), encoding);
    }

    /**
     * 带参数的post请求的文件下载
     *  json格式参数
     * @param url
     * @param objectToJson
     * @return
     * @throws Exception
     */
    public String doJsonPostFileDown(String url, Object objectToJson, HttpServletResponse response, String contentType) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        OutputStream out = null;
        // 加入配置信息
        httpPost.setConfig(config);
        InputStream stream = null;
        String jsonSring = null;
        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        try {
            out = response.getOutputStream();
            if (objectToJson != null) {
                jsonSring = objectToJson.toString();
                //装填参数
                StringEntity stringEntity = null;
                stringEntity = new StringEntity(jsonSring,encoding);
                httpPost.setEntity(stringEntity);
            }
            log.info("创建请求httpsPost-URL={},params={}", url, jsonSring);
            //设置header信息
            httpPost.setHeader("Content-Type", "application/json;charset="+encoding);
            // 发起请求
            CloseableHttpResponse responseBody = this.httpClient.execute(httpPost);
            log.info("返回code={}",responseBody.getStatusLine().getStatusCode());
            if (200 !=responseBody.getStatusLine().getStatusCode()){
                return null;
            }
            stream = responseBody.getEntity().getContent();

            response.setContentType("application/"+contentType+";charset=UTF-8");

            System.out.println("-----------------------application/"+contentType+";charset=UTF-8");

            byte[] b = new byte[1024 * 1024 * 5];
            int len;
            while ((len = stream.read(b)) != -1) {
                out.write(b, 0, len);
            }
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("输出流关闭失败：", e);
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    log.error("输入流关闭失败：", e);
                }
            }
        }


        return "200";
    }


    /**
     * 带json参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public void doJsonGet(String url, Object objectToJson,HttpServletResponse response) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (objectToJson != null) {
            Map<String, Object> map = Gsons.gsons().fromJson(objectToJson.toString(),Map.class);
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        getContentFromUrlByGet(uriBuilder.build().toString(),response);
    }


    /**
       * 根据GET方式请求获取请求内容
       * @param url
       * @return
       */
 private void getContentFromUrlByGet(String url,HttpServletResponse response) throws IOException {
     OutputStream out = null;
     InputStream stream = null;
     try {
     out = response.getOutputStream();
     // 声明 http get 请求
     HttpGet httpGet = new HttpGet(url);

     // 装载配置信息
     httpGet.setConfig(config);


     // 发起请求
     CloseableHttpResponse responseBody = this.httpClient.execute(httpGet);
     stream = responseBody.getEntity().getContent();

     response.setContentType("application/vnd.ms-excel;charset=UTF-8");


     byte[] b = new byte[1024 * 1024 * 5];
     int len;
     while ((len = stream.read(b)) != -1) {
         out.write(b, 0, len);
     }

    /*content = new String(stringBuffer);*/
    //httpGet.releaseConnection();
    } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }finally {
         if (out != null) {
             try {
                 out.close();
             } catch (IOException e) {
                 log.error("输出流关闭失败：", e);
             }
         }
         if (stream != null) {
             try {
                 stream.close();
             } catch (IOException e) {
                 log.error("输入流关闭失败：", e);
             }
         }
 }

 }
    /**
     * 不带参数post请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public ResponseResult doPost(String url) throws Exception {
        return this.doPost(url, null);
    }


    /**
     * 带参数的文件post请求
     *  文件格式参数
     * @param url
     * @param ob
     * @return
     * @throws Exception
     */
    public Object doJsonPostFile(String url, Map<String,Object> ob) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        Map<String, String> resultMap = new HashMap<String, String>();
        String result = "";
    try{
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        String fileName = null;
        //String params = Gsons.gsons().toJson(ob.get("params"));
        MultipartFile multipartFile = (MultipartFile) ob.get("file");
        fileName = multipartFile.getOriginalFilename();
        builder.addBinaryBody("file", multipartFile.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流

        //决中文乱码
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, encoding);

            // 类似浏览器表单提交，对应input的name和value
        builder.addTextBody("params", ob.get("params").toString(), contentType);

        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);


        log.info("返回code={}",response.getStatusLine().getStatusCode());
        // 设置连接超时时间
        int timeout = 70000;
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
        httpPost.setConfig(requestConfig);

        HttpEntity responseEntity = response.getEntity();

        resultMap.put("scode", String.valueOf(response.getStatusLine().getStatusCode()));
        resultMap.put("data", "");
        if (responseEntity != null) {
            // 将响应内容转换为字符串
            result = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName(encoding));
            resultMap.put("data", result);
        }
    } catch (Exception e) {
        resultMap.put("scode", "error");
        resultMap.put("data", "HTTP请求出现异常: " + e.getMessage());

        Writer w = new StringWriter();
        e.printStackTrace(new PrintWriter(w));
        log.error("HTTP请求出现异常: " + w.toString());
    }
    return result;
    }

}

