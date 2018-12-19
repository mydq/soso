package com.kettle.soso.restful;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @Author: csz
 * @Date: 2018/12/19 16:56
 */
public class TestFileNameUtil {

    @Test
    public void test(){
        String fileName = "test.txt";
        String extension = FilenameUtils.getExtension(fileName);
        System.out.println(extension);

        String baseName = FilenameUtils.getBaseName(fileName);
        System.out.println(baseName);

        UUID uuid = UUID.randomUUID();
        String join = StringUtils.join(uuid,".", extension);
        System.out.println(join);
    }

    @Test
    public void  testMd5(){
        String s = DigestUtils.md5DigestAsHex("123456".getBytes());
        System.out.println(s);
    }
}
