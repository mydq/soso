package com.kettle.soso.common.exceptions;

import com.kettle.soso.common.constants.ExceptionEnum;

/**
 * 定时任务添加异常
 * @Author: csz
 * @Date: 2018/12/19 16:22
 */
public class JobAddFailException extends BaseException {

    public String code = ExceptionEnum.JOB_AND_FAIL.getCode();


    public JobAddFailException() {
        super("job add fail");
    }


    public JobAddFailException(String message) {
        super(message);
    }

    public JobAddFailException(String message, String code) {
        super(message);
        this.code = code;
    }
}
