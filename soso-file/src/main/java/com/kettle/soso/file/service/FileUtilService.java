package com.kettle.soso.file.service;

import com.kettle.soso.file.model.MultipartFileModel;
import com.kettle.soso.file.old.MultipartFileParam;

import java.io.IOException;

/**
 * 文件操作工具
 * @Author: csz
 * @Date: 2019/1/3 15:36
 */
public interface FileUtilService {

    /**
     * 移除所有过程数据
     */
    void removeAll();

    /**
     * 初始化方法
     */
    void init();

    /**
     * 上传文件方法1
     *
     * @param model
     * @throws IOException
     */
    void uploadFileRandomAccessFile(MultipartFileModel model) throws IOException;

    /**
     * 上传文件方法2
     * 处理文件分块，基于MappedByteBuffer来实现文件的保存
     *
     * @param model
     * @throws IOException
     */
    void uploadFileByMappedByteBuffer(MultipartFileModel model) throws IOException;
}
