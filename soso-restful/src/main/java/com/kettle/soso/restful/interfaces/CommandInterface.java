package com.kettle.soso.restful.interfaces;

import com.kettle.soso.common.exceptions.BaseException;
import com.kettle.soso.common.model.ReturnResult;
import com.kettle.soso.common.utils.CommandUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该接口为紧急远程使用命令行接口，慎用
 * @Author: csz
 * @Date: 2018/12/19 19:28
 */
@RestController
@RequestMapping("command")
public class CommandInterface {


    /**
     * 执行接口
     * @param command
     * @param isShell
     * @return
     */
    @RequestMapping(value = "/run",method = RequestMethod.GET)
    public ReturnResult<String> runCommand(String command, String isShell){
        ReturnResult<String> returnResult;
        try {
            String s = CommandUtil.runLinuxPrint(command, Boolean.valueOf(isShell));
            returnResult = new ReturnResult<>(s);
        }catch (BaseException e){
            returnResult = new ReturnResult<>(null, "fail", e.code, e.getMessage());
        }catch (Exception e){
            returnResult = new ReturnResult<>(null, "fail", "500", e.getMessage(),"命令执行失败");
        }
        return returnResult;
    }


}
