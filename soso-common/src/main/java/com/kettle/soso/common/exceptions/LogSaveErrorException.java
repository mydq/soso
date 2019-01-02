package com.kettle.soso.common.exceptions;

import com.kettle.soso.common.constants.ExceptionEnum;

/**
 * kettle_log 保存异常
 * @Author: csz
 * @Date: 2018/12/19 16:22
 */
public class LogSaveErrorException extends BaseException {

    public String code = ExceptionEnum.LOG_SAVE_ERROR.getCode();


    public LogSaveErrorException() {
        super("log save error");
    }


    public LogSaveErrorException(String message) {
        super(message);
    }

    public LogSaveErrorException(String message, String code) {
        super(message);
        this.code = code;
    }
}
