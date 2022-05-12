package com.example.springboottest.config;

import cn.hutool.http.HtmlUtil;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.LinkedHashMap;
import java.util.Map;

public class XssHttpRequestWrapper extends HttpServletRequestWrapper {

    private final static Whitelist user_content_filter = Whitelist.relaxed();
    static {
        user_content_filter.addTags("embed", "object", "param", "span", "div");
        user_content_filter.addAttributes(":all", "style", "class", "id", "name");
        user_content_filter.addAttributes("object", "width", "height", "classid", "codebase");
        user_content_filter.addAttributes("param", "name", "value");
        user_content_filter.addAttributes("embed", "src", "quality", "width", "height", "allowFullScreen", "allowScriptAccess", "flashvars", "name", "type", "pluginspage");
    }
    public XssHttpRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    /**
     * 对单一参数值进行过滤
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return cleanXSS(value);
    }
    /**
     * 对字符串数组进行过滤
     *
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        cleanXSS(values); // 传引用
        return values;
    }
    /**
     * hutool->HtmlUtil 过滤parameterMap
     *
     * @return
     */
    @Override
    public Map getParameterMap() {
        Map<String, String[]> parameters = super.getParameterMap();
        Map<String, String[]> map = new LinkedHashMap<>();
        if (parameters != null) {
            for (String key : parameters.keySet()) {
                String[] values = parameters.get(key);
                for (int i = 0; i < values.length; i++) {
                    String value = values[i];
                    if (!StringUtils.isEmpty(value)) {
                        value = HtmlUtil.filter(value);
                    }
                    values[i] = value;
                }
                map.put(key, values);
            }
        }
        return map;
    }
    /**
     * 过滤 header
     *
     * @param name
     * @return
     */
    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return cleanXSS(value);
    }
    /**
     * 对字符串数组进行过滤
     *
     * @param values
     */
    private void cleanXSS(String[] values) {
        if (values != null) {
            for (int num = 0; num < values.length; num++) {
                values[num] = this.cleanXSS(values[num]);
            }
        }
    }
    /**
     * HTML过滤危险内容.
     *
     * @param value
     * @return
     */
    private String cleanXSS(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Jsoup.clean(value, user_content_filter);
    }
}
