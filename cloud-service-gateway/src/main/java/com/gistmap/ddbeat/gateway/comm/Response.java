package com.gistmap.ddbeat.gateway.comm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangran
 * @date 2018/6/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    protected int code = 200;
    protected String msg = "操作成功";
}
