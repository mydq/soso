package com.kettle.soso.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件元数据
 * @Author: csz
 * @Date: 2018/12/27 14:44
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
public class FileDataModel implements Serializable {
    @JsonIgnore
    private static final long serialVersionUID = -2995833989446498240L;

    private String originalFilename;

    private String contentType;

    private long size;

    private String md5;

    private String uuid;

    private String filePath;


}
