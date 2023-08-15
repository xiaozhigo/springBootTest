package com.example.springboottest.xss;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
/**
 * 反序列化，用来处理请求中的JSON数据
 * 处理RequestBody方式接收的参数
 */
public class XssJacksonDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return StringEscapeUtils.escapeHtml4(jp.getText());
    }

    /**
     * 判断字符串是不是JSON
     *
     * @param str
     * @return
     */
    private boolean isJson(String str) {
        boolean result = false;
        if (StringUtils.isNotBlank(str)) {
            str = str.trim();
            if (str.startsWith("{") && str.endsWith("}")) {
                result = true;
            } else if (str.startsWith("[") && str.endsWith("]")) {
                result = true;
            }
        }
        return result;
    }
}
