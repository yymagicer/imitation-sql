package com.imitationsql.web.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Description: some description </p>
 *
 * @author : xiaodong.yang
 * @date : 2024/3/15 14:05
 */
@Setter
@Getter
public class CommonResult<T> implements Serializable {

    private static final String CODE_SUCCESS = "0";
    private static final String CODE_ERROR = "-1";
    public static final String SUCCESS_MSG = "请求成功";

    /**
     * 状态码  0:成功 -1:失败
     */
    private String code;
    /**
     * 描述信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;

    public static <T> CommonResult<T> ok() {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(CODE_SUCCESS);
        result.setData(null);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    public static <T> CommonResult<T> ok(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(CODE_SUCCESS);
        result.setData(data);
        result.setMsg(SUCCESS_MSG);
        return result;
    }

    public static <T> CommonResult<T> err(String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(CODE_ERROR);
        result.setMsg(msg);
        return result;
    }

    public static <T> CommonResult<T> err(String code, String msg, T data) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> CommonResult<T> err(String code, String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
