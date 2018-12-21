package com.kettle.soso.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: csz
 * @Date: 2018/12/20 12:07
 */
@Data
public class KettleModel implements Serializable {
    private static final long serialVersionUID = 6729270547042945812L;

    private final String command = "kitchen.sh";


    /**
     * 资源库名称, 要连接的资源库名称
     */
    private String rep;

    /**
     * 资源库用户, 要连接的资源库用户
     */
    private String user;

    /**
     * 资源库用户密码, 要连接的资源库用户密码
     */
    private String pass;

    /**
     *要启动的作业名称
     */
    private String job;

    /**
     * 资源库里的路径,指定资源库路径
     */
    private String dir = "/";

    /**
     * 要启动的文件名（转换文件）
     */
    private String file;

    /**
     * 日志级别
     */
    private String level;

    /**
     * 要写入的日志文件
     */
    private String log;

    /**
     *列出指定存储中的目录结构
     */
    private String listdir;

    /**
     *列出指定目录下的所有任务
     */
    private String listjobs;

    /**
     *列出所有的存储
     */
    private String listrep;

    /**
     * 不要将日志写到资料库中
     */
    private String norep;

    /**
     * 显示转换的版本，校订和创建日期
     */
    private String version;

    /**
     * 列出转换里已经设置好的参数
     */
    private String listparam;

    /**
     * 把作业依赖的所有资源导出到一个zip文件里
     */
    private String export;

    /**
     * 内存中保存日志的最大日志行数
     */
    private String maxloglines;

    /**
     * 内存中保存日志的最长时间
     */
    private String maxlogtimeout;

    /**
     * Linux: ./kitchen.sh-file:job.kjb -param:files.dir=/opt/files -param:max.date=2010-06-02
     * Windows: Kitchen.bat -file:job.kjb “-param:files.dir=/opt/files”“-param:max.date=2010-06-02″
     */
    private Map<String, String> param = new HashMap<>();


    /**
     * 作业存储在文件
     *
     * kitchen.sh-file=/home/job/huimin.kjb >> /home/ log/kettle.log
     *
     * 作业存储在数据库
     *
     * ./kitchen.sh -rep=kettle1 -user=admin -pass=admin -level=Basic -job=job
     */


}
