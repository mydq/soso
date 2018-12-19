package com.kettle.soso.common.utils;

/**
 * @Author: csz
 * @Date: 2018/12/19 18:49
 */
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommandUtil {


    /**
     * Linux
     *
     * 以命令行或者shell子进程的形式运行命令 并且打印输出
     * 命令行形式不支持特殊字符，例如: > , | ...
     * @param command
     * @param isShell
     * @return
     */
    public static String runLinuxPrint(String command, boolean isShell){
        Scanner input = null;
        String result = "";
        Process process = null;
        if (isShell){
            String[] cmd = { "/bin/sh", "-c", command };
            try {
                process = Runtime.getRuntime().exec(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                process = Runtime.getRuntime().exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //等待命令执行完成
        try {
            process.waitFor(10, TimeUnit.SECONDS);
            InputStream is = process.getInputStream();
            input = new Scanner(is);
            while (input.hasNextLine()) {
                result += input.nextLine() + "\n";
            }
            //加上命令本身，打印出来
            result = command + "\n" + result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (input != null) {
                input.close();
            }
            if (process != null) {
                process.destroy();
            }
        }
       return result;
    }





}