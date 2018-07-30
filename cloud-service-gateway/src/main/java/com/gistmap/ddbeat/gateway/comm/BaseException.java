package com.gistmap.ddbeat.gateway.comm;

/**
 * @author zhangran
 * @date 2018/6/12
 */
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public BaseException(Integer code ,String msg) {
        super(msg);
        this.code = code;
    }

}
