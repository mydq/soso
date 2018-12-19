package com.kettle.soso.common.exceptions;

/**
 * @Author: csz
 * @Date: 2018/12/19 16:31
 */
public class BaseException extends RuntimeException {

    /**
     * 基本异常的代号
     */
    public String code = "200";

    public BaseException() {
        super("base exception");
    }

    public BaseException(String message) {
        super(message);
    }
}
