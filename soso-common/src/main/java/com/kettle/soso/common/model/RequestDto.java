package com.kettle.soso.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: csz
 * @Date: 2018/12/27 11:23
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RequestDto <T> implements Serializable {

    private static final long serialVersionUID = 7214672826389405471L;

    private String systemId;

    private String token;

    private String timestamp;

    private Map<String, String> params;

    private List<T> data;
}
