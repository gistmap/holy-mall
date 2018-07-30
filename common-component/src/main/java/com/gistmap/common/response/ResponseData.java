package com.gistmap.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gistmap.common.exception.BaseException;
import lombok.Data;

/**
 * @author zhangran
 * @date 2018/6/12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseData extends Response {

    private Object data;

    public ResponseData(int code, String msg) {
        super(code,msg);
    }

    public ResponseData(int code, String msg, Object data) {
        super(code,msg);
        this.data = data;
    }

    public static Response of(int code, String msg){
        return new Response(code, msg);
    }

    public static Response of(ResultEnum result){
        return new ResponseData(result.code, result.msg);
    }

    public static ResponseData of(int code, String msg , Object data){
        return new ResponseData(code, msg , data);
    }

    public static Response ok(){
        return new Response(200,"success");
    }

    public static ResponseData ok(Object data){
        return new ResponseData(200,"success", data);
    }

    public static Response fail(ResultEnum resultEnum){
        return new Response(resultEnum.code,resultEnum.msg);
    }
    public static Response fail(BaseException ex){
        return new Response(ex.code , ex.msg);
    }

    public static Response fail(String msg){
        return new Response(999, msg);
    }
}
