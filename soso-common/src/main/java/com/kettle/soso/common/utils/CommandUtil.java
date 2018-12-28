package com.kettle.soso.common.utils;

/**
 * @Author: csz
 * @Date: 2018/12/19 18:49
 */

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CommandUtil {


    /**
     * 运行linux命令，不打印，返回执行状态
     * @param command
     * @param isShell
     * @return
     */
    public static boolean runLinux(String command, boolean isShell){
        Process process = null;
        int i = 99;
        //等待命令执行完成
        try {
            process = run(command, isShell);
            i = process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (process != null) {
                process.destroy();
            }
        }
        return i == 0;
    }


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

        //等待命令执行完成
        try {
            process = run(command, isShell);
            process.waitFor(10, TimeUnit.SECONDS);
            InputStream is = process.getInputStream();
            input = new Scanner(is);
            while (input.hasNextLine()) {
                result += input.nextLine() + "\n";
            }
            //加上命令本身，打印出来
            result = command + "\n" + result;
        } catch (Exception e) {
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


    /**
     * 执行命令
     * @param command
     * @param isShell
     * @return
     * @throws Exception
     */
    private static Process run(String command, boolean isShell) throws Exception{
        Process process;
        if (isShell){
            String[] cmd = { "/bin/sh", "-c", command };
            process = Runtime.getRuntime().exec(cmd);
        }else {
            process = Runtime.getRuntime().exec(command);
        }
       return process;
    }



}