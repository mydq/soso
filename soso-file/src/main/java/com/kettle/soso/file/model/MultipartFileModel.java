package com.kettle.soso.file.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Author: csz
 * @Date: 2019/1/3 16:02
 */
@Data
public class MultipartFileModel implements Serializable {
    private static final long serialVersionUID = 5281491882044114827L;
    /**
     *     用户id
      */
    private String uid;
    /**
     * 任务ID
     */
    private String id;
    /**
     * 总分片数量
     */
    private int chunks;
    /**
     * 当前为第几块分片
     */
    private int chunk;
    /**
     * 当前分片大小
     */
    private long size = 0L;
    /**
     * 文件名
     */
    private String name;
    /**
     * 分片对象
     */
    private MultipartFile file;
    /**
     * MD5
     */
    private String md5;
}
