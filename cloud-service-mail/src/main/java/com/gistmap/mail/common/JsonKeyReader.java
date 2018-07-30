package com.gistmap.mail.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;


public class JsonKeyReader {

    private JsonNode node;

    public JsonKeyReader(String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            node = mapper.readTree(body);
        } catch (IOException e) {
            throw new RuntimeException("错误的json格式");
        }
    }

    public String readString(final String key, final Boolean nullable) {
        if (node.has(key)) {
            String value = node.get(key).asText();
            if (StringUtils.isBlank(value) && !nullable) {
                throw new RuntimeException(String.format("[%s]字段不能为空", key));
            }
            return node.get(key).asText();
        } else if (nullable) {
            return null;
        } else {
            throw new RuntimeException(String.format("[%s]字段不能为空", key));
        }
    }

    public String readDefaultString(final String key, final String defaultValue) {
        return StringUtils.defaultString(readString(key, true), defaultValue);
    }

    public Integer readInteger(final String key, final Boolean nullable) {
        if (node.has(key)) {
            return node.get(key).isInt() ? node.get(key).asInt() : Integer.valueOf(node.get(key).asText());
        } else if (nullable) {
            return null;
        } else {
            throw new RuntimeException(String.format("[%s]字段不能为空", key));
        }
    }

    public Integer readDefaultInteger(final String key, final Integer defaultValue) {
        Integer value = readInteger(key, true);
        return value == null ? defaultValue : value;
    }

    public Boolean readBoolean(final String key, final Boolean nullable) {
        if (node.has(key)) {
            return node.get(key).isBoolean() ? node.get(key).asBoolean() : Boolean.valueOf(node.get(key).asText());
        } else if (nullable) {
            return null;
        } else {
            throw new RuntimeException(String.format("[%s]字段不能为空", key));
        }
    }

    public Boolean readDefaultBoolean(final String key, final Boolean defaultValue) {
        Boolean value = readBoolean(key, true);
        return value == null ? defaultValue : value;
    }

    public <T> T readObject(final String key, final Boolean nullable, final Class<T> type) {
        if (node.has(key)) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.treeToValue(node.get(key), type);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("错误的json格式");
            }
        } else if (nullable) {
            return null;
        } else {
            throw new RuntimeException(String.format("[%s]字段不能为空", key));
        }
    }
}
