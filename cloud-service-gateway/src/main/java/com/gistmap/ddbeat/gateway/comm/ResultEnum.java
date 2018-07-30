package com.gistmap.ddbeat.gateway.comm;

import lombok.Getter;

/**
 * @author zhangran
 * @date 2018/6/12
 */
@Getter
public enum ResultEnum {

    SIGNATURE_ERROR(401,"签名错误");

    Integer code;
    String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
