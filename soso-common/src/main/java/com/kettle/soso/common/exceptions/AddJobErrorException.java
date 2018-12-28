package com.kettle.soso.common.exceptions;

import com.kettle.soso.common.constants.ExceptionEnum;

/**
 * 添加job异常
 * @Author: csz
 * @Date: 2018/12/19 16:22
 */
public class AddJobErrorException extends BaseException {

    public String code = ExceptionEnum.ADD_JOB_ERROR.getCode();


    public AddJobErrorException() {
        super("job add  error");
    }


    public AddJobErrorException(String message) {
        super(message);
    }

    public AddJobErrorException(String message, String code) {
        super(message);
        this.code = code;
    }
}
