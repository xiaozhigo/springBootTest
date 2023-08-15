package com.example.springboottest.xss;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;

public class XssJacksonSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(StringEscapeUtils.escapeHtml4(value));
    }
}
