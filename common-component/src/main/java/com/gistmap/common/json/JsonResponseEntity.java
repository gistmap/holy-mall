package com.gistmap.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponseEntity<T> {

    public int code;
    public String msg;
    public T data;

    public JsonResponseEntity() {

    }
}
