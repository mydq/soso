package com.kettle.soso.common.exceptions;

import com.kettle.soso.common.constants.ExceptionEnum;

/**
 * 文件错误异常
 * @Author: csz
 * @Date: 2018/12/19 16:22
 */
public class FileErrorException extends BaseException {

    public String code = ExceptionEnum.FILE_ERROR.getCode();


    public FileErrorException() {
        super("file error");
    }


    public FileErrorException(String message) {
        super(message);
    }

    public FileErrorException(String message, String code) {
        super(message);
        this.code = code;
    }
}
