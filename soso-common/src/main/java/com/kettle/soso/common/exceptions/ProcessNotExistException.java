package com.kettle.soso.common.exceptions;

import com.kettle.soso.common.constants.ExceptionEnum;

/**
 * 流程不存在异常
 * @Author: csz
 * @Date: 2018/12/19 16:22
 */
public class ProcessNotExistException extends BaseException {

    public String code = ExceptionEnum.PROCESS_NO_EXIST.getCode();


    public ProcessNotExistException() {
        super("process not exist");
    }


    public ProcessNotExistException(String message) {
        super(message);
    }

    public ProcessNotExistException(String message, String code) {
        super(message);
        this.code = code;
    }
}
