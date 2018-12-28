package com.kettle.soso.common.constants;

/**
 * 各类异常代号
 * @Author: csz
 * @Date: 2018/12/28 17:35
 */
public enum ExceptionEnum {
    //文件错误异常
    FILE_ERROR("1001"),
    //流程不存在异常
    PROCESS_NO_EXIST("1002"),
    //定时任务添加异常
    JOB_AND_FAIL("1003"),
    //添加job异常
    ADD_JOB_ERROR("1004"),
    //orgNo不存在异常
    ORGNO_NO_EXIST("1005"),
    //Linux命令运行失败
    LINUX_COMMAND_RUN_FAIL("1006")
    ;

    private final String code;



    ExceptionEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }}
