package com.kettle.soso.common.exceptions;

import com.kettle.soso.common.constants.ExceptionEnum;

/**
 * orgNo不存在异常
 * @Author: csz
 * @Date: 2018/12/19 16:22
 */
public class OrgNoNotExistException extends BaseException {

    public String code = ExceptionEnum.ORGNO_NO_EXIST.getCode();


    public OrgNoNotExistException() {
        super("orgNo not exist");
    }


    public OrgNoNotExistException(String message) {
        super(message);
    }

    public OrgNoNotExistException(String message, String code) {
        super(message);
        this.code = code;
    }
}
