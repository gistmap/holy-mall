package com.gistmap.common.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonListResponseEntity<T> {

    public int code;
    public String msg;
    public ListData<T> data;

    public JsonListResponseEntity() {
        this.code = 0;
        this.data = new ListData<>();
    }

    public static <T> JsonListResponseEntity<T> of(){
        return new JsonListResponseEntity<>();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public class ListData<T> {
        public Boolean more;
        @JsonProperty("more_params")
        public MoreParams moreParams;
        public Map<String, Object> extras;
        public List<T> content;

        public ListData() {
            this.more = false;
            this.content = Lists.newArrayList();
        }

        public ListData(List<T> content) {
            this.more = false;
            this.content = content;
        }

        public ListData(List<T> content, Boolean more, String order, String flag) {
            this.more = more;
            if (order != null || flag != null) {
                this.moreParams = new MoreParams(order, flag);
            }
            this.content = content;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private class MoreParams {
        public String order;
        public String flag;

        public MoreParams(String order, String flag) {
            this.order = order;
            this.flag = flag;
        }
    }

    @JsonIgnore
    public JsonListResponseEntity setContent(List<T> content) {
        this.data = new ListData<>(content);
        return this;
    }

    @JsonIgnore
    public void setContent(List<T> data, Boolean more, String order, String flag) {
        this.data = new ListData<>(data, more, order, flag);
    }

    @JsonIgnore
    public JsonListResponseEntity addExtra(String key, String value) {
        if (this.data.extras == null) {
            this.data.extras = Maps.newHashMap();
        }
        this.data.extras.put(key, value);
        return this;
    }

    @JsonIgnore
    public void setExtras(Map<String, Object> extras) {
        if (data == null) {
            this.data = new ListData<>();
        }
        this.data.extras = extras;
    }
}
