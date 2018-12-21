package com.kettle.soso.common.dto;

import lombok.Data;

/**
 * 上传文件
 * @Author: csz
 * @Date: 2018/12/19 11:06
 */
@Data
public class UploadFileDto {
    /**
     * 数据类型
     */
    private String dataCode;

    /**
     * 上传者代号
     */
    private String organizationCode;

    /**
     * 上传者名称
     */
    private String organizationName;

    /**
     * 定时表达式
     */
    private String expression;

}
