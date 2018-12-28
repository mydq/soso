package com.kettle.soso.common.exceptions;

import com.kettle.soso.common.constants.ExceptionEnum;

/**
 * Linux命令运行失败
 * @Author: csz
 * @Date: 2018/12/19 16:22
 */
public class LinuxCommandRunFailException extends BaseException {

    public String code = ExceptionEnum.LINUX_COMMAND_RUN_FAIL.getCode();


    public LinuxCommandRunFailException() {
        super("linux command run fail ");
    }


    public LinuxCommandRunFailException(String message) {
        super(message);
    }

    public LinuxCommandRunFailException(String message, String code) {
        super(message);
        this.code = code;
    }
}
