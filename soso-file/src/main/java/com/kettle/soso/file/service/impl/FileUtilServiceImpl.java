//package com.kettle.soso.file.service.impl;
//
//import com.kettle.soso.file.model.MultipartFileModel;
//import com.kettle.soso.file.old.Constants;
//import com.kettle.soso.file.old.MultipartFileParam;
//import com.kettle.soso.file.service.FileUtilService;
//import com.kettle.soso.mybatis.dal.bo.CreditFileStatusBo;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.FileUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.util.FileSystemUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
///**
// * 文件操作工具
// * @Author: csz
// * @Date: 2019/1/3 15:38
// */
//@Slf4j
//public class FileUtilServiceImpl implements FileUtilService {
//
//    @Value("${break-file.dir}")
//    private String dirPath;
//    /**
//     * 这个必须与前端设定的值一致
//     */
//    @Value("${break-file.chunkSize}")
//    private long CHUNK_SIZE;
//
//    @Autowired
//    private CreditFileStatusBo creditFileStatusBo;
//
//
//    /**
//     * 移除所有过程数据
//     */
//    @Override
//    public void removeAll() {
//        try {
//            FileSystemUtils.deleteRecursively(Paths.get(dirPath));
//            creditFileStatusBo.deleteAll();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 初始化
//     */
//    @Override
//    public void init() {
//        try {
//            Files.createDirectory(Paths.get(dirPath));
//            creditFileStatusBo.deleteAll();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void uploadFileRandomAccessFile(MultipartFileModel model) throws IOException {
//        String fileName = model.getName();
//        String tempDirPath = dirPath + model.getMd5();
//        String tempFileName = fileName + "_tmp";
//        File tmpDir = new File(tempDirPath);
//        File tmpFile = new File(tempDirPath, tempFileName);
//        if (!tmpDir.exists()) {
//            tmpDir.mkdirs();
//        }
//
//        RandomAccessFile accessTmpFile = new RandomAccessFile(tmpFile, "rw");
//        long offset = CHUNK_SIZE * model.getChunk();
//        //定位到该分片的偏移量
//        accessTmpFile.seek(offset);
//        //写入该分片数据
//        accessTmpFile.write(model.getFile().getBytes());
//        // 释放
//        accessTmpFile.close();
//
//        boolean isOk = checkAndSetUploadProgress(model, tempDirPath);
//        if (isOk) {
//            boolean flag = renameFile(tmpFile, fileName);
//            System.out.println("upload complete !!" + flag + " name=" + fileName);
//        }
//    }
//
//    @Override
//    public void uploadFileByMappedByteBuffer(MultipartFileModel model) throws IOException {
//
//    }
//
//
//    /**
//     * 检查并修改文件上传进度
//     *
//     * @param model
//     * @param uploadDirPath
//     * @return
//     * @throws IOException
//     */
//    private boolean checkAndSetUploadProgress(MultipartFileModel model, String uploadDirPath) throws IOException {
//        String fileName = model.getName();
//        File confFile = new File(uploadDirPath, fileName + ".conf");
//        RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw");
//        //把该分段标记为 true 表示完成
//        System.out.println("set part " + model.getChunk() + " complete");
//        accessConfFile.setLength(model.getChunks());
//        accessConfFile.seek(model.getChunk());
//        accessConfFile.write(Byte.MAX_VALUE);
//
//        //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
//        byte[] completeList = FileUtils.readFileToByteArray(confFile);
//        byte isComplete = Byte.MAX_VALUE;
//        for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
//            //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
//            isComplete = (byte) (isComplete & completeList[i]);
//            System.out.println("check part " + i + " complete?:" + completeList[i]);
//        }
//
//        accessConfFile.close();
//        if (isComplete == Byte.MAX_VALUE) {
//            stringRedisTemplate.opsForHash().put(Constants.FILE_UPLOAD_STATUS, model.getMd5(), "true");
//            stringRedisTemplate.opsForValue().set(Constants.FILE_MD5_KEY + model.getMd5(), uploadDirPath + "/" + fileName);
//            return true;
//        } else {
//            if (!stringRedisTemplate.opsForHash().hasKey(Constants.FILE_UPLOAD_STATUS, model.getMd5())) {
//                stringRedisTemplate.opsForHash().put(Constants.FILE_UPLOAD_STATUS, model.getMd5(), "false");
//            }
//            if (stringRedisTemplate.hasKey(Constants.FILE_MD5_KEY + model.getMd5())) {
//                stringRedisTemplate.opsForValue().set(Constants.FILE_MD5_KEY + model.getMd5(), uploadDirPath + "/" + fileName + ".conf");
//            }
//            return false;
//        }
//    }
//
//
//
//
//
//    /**
//     * 文件重命名
//     *
//     * @param toBeRenamed   将要修改名字的文件
//     * @param toFileNewName 新的名字
//     * @return
//     */
//    private boolean renameFile(File toBeRenamed, String toFileNewName) {
//        //检查要重命名的文件是否存在，是否是文件
//        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
//            log.info("File does not exist: " + toBeRenamed.getName());
//            return false;
//        }
//        String p = toBeRenamed.getParent();
//        File newFile = new File(p + File.separatorChar + toFileNewName);
//        //修改文件名
//        return toBeRenamed.renameTo(newFile);
//    }
//
//}
