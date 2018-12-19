package com.kettle.soso.common.utils;

import java.io.File;

/**
 * @Author: csz
 * @Date: 2018/12/19 14:08
 */
public class FileBean {

    /**
     * 验证此文件是否存在
     * @param path
     * @return
     */
    public static boolean verifyExists(String path){
        File file = new File(path);
        return file.exists();
    }


    /**
     * 不存在文件夹就创建
     * @param path
     * @return
     * @throws Exception
     */
    public static String notExistsToCreateDir(String path) throws Exception{
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        return path;
    }


    /**
     * 不存在文件就创建
     * @param path
     * @return
     * @throws Exception
     */
    public static String notExistsToCreateFile(String path) throws Exception{
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        return path;
    }

    /**
     * 创建文件
     * @param path
     * @return
     * @throws Exception
     */
    public static String createFile(String path) throws Exception{
        new File(path).createNewFile();
        return path;
    }




}
