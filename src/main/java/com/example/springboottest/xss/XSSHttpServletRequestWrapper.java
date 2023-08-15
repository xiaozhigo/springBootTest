package com.example.springboottest.xss;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private static final Logger logger = LoggerFactory.getLogger(XSSHttpServletRequestWrapper.class);

    private HttpServletRequest request;
    /**
     * 请求体 RequestBody
     */
    private String reqBody;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public XSSHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        logger.info("---xss XSSHttpServletRequestWrapper created-----");
        this.request = request;
        reqBody = getBodyString();
    }


    @Override
    public String getQueryString() {
        return StringEscapeUtils.escapeHtml4(super.getQueryString());
    }

    /**
     * The default behavior of this method is to return getParameter(String
     * name) on the wrapped request object.
     *
     * @param name
     */
    @Override
    public String getParameter(String name) {
        logger.info("---xss XSSHttpServletRequestWrapper work  getParameter-----");
        String parameter = request.getParameter(name);
        if (StringUtils.isNotBlank(parameter)) {
            logger.info("----filter before--name:{}--value:{}----", name, parameter);
            parameter = StringEscapeUtils.escapeHtml4(parameter);
            logger.info("----filter after--name:{}--value:{}----", name, parameter);
        }
        return parameter;
    }

    /**
     * The default behavior of this method is to return
     * getParameterValues(String name) on the wrapped request object.
     *
     * @param name
     */
    @Override
    public String[] getParameterValues(String name) {
        logger.info("---xss XSSHttpServletRequestWrapper work  getParameterValues-----");
        String[] parameterValues = request.getParameterValues(name);
        if (parameterValues != null && parameterValues.length > 0) {
            // 经 “@Belief_7” 指正 这种方式不能更改parameterValues里面的值，要换成下面👇的写法
            //for (String value : parameterValues) {
            //    logger.info("----filter before--name:{}--value:{}----", name, value);
            //    value = StringEscapeUtils.escapeHtml4(value);
            //    logger.info("----filter after--name:{}--value:{}----", name, value);
            // }
            for (int i = 0; i < parameterValues.length; i++)
            {
                parameterValues[i] = StringEscapeUtils.escapeHtml4(parameterValues[i]);
            }
        }
        return parameterValues;
    }

    /**
     * The default behavior of this method is to return getParameterMap() on the
     * wrapped request object.
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        logger.info("---xss XSSHttpServletRequestWrapper work  getParameterMap-----");
        Map<String, String[]> map = request.getParameterMap();
        if (map != null && !map.isEmpty()) {
            for (String[] value : map.values()) {
                /*循环所有的value*/
                for (String str : value) {
                    logger.info("----filter before--value:{}----", str, str);
                    str = StringEscapeUtils.escapeHtml4(str);
                    logger.info("----filter after--value:{}----", str, str);
                }
            }
        }
        return map;
    }

    /*重写输入流的方法，因为使用RequestBody的情况下是不会走上面的方法的*/
    /**
     * The default behavior of this method is to return getReader() on the
     * wrapped request object.
     */
    @Override
    public BufferedReader getReader() throws IOException {
        logger.info("---xss XSSHttpServletRequestWrapper work  getReader-----");
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * The default behavior of this method is to return getInputStream() on the
     * wrapped request object.
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        logger.info("---xss XSSHttpServletRequestWrapper work  getInputStream-----");
        /*创建字节数组输入流*/
        final ByteArrayInputStream bais = new ByteArrayInputStream(reqBody.getBytes(StandardCharsets.UTF_8));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }


    /**
     * 获取请求体
     *
     * @return 请求体
     */
    private String getBodyString() {
        StringBuilder builder = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = request.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            logger.error("-----get Body String Error:{}----", e.getMessage(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("-----get Body String Error:{}----", e.getMessage(), e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("-----get Body String Error:{}----", e.getMessage(), e);
                }
            }
        }
        return builder.toString();
    }

}
