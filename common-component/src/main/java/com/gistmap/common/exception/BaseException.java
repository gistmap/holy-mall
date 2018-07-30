package com.gistmap.common.exception;

import com.gistmap.common.response.ResultEnum;

/**
 * @author zhangran
 * @date 2018/6/12
 */
public class BaseException extends RuntimeException {
    public Integer code;
    public String msg;


    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public BaseException(Integer code ,String msg) {
        this.msg = msg;
        this.code = code;
    }

    public BaseException(String msg) {
            this(1000, msg);
    }

}
