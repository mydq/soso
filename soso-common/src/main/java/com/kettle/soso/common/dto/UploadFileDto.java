package com.kettle.soso.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 上传文件
 * @Author: csz
 * @Date: 2018/12/19 11:06
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UploadFileDto implements Serializable {
    @JsonIgnore
    private static final long serialVersionUID = -3272941343495450040L;
    /**
     * 数据类型
     */
    private String dataCode;

    /**
     * 上传者代号
     */
    private String organizationCode;

    /**
     * 定时表达式
     */
    private String expression;

}
