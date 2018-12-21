package com.kettle.soso.common;

import com.kettle.soso.common.model.KettleModel;
import com.kettle.soso.common.utils.BuildCommandUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @Author: csz
 * @Date: 2018/12/20 13:18
 */
public class TestCommandUtil {


    @Test
    public void testUtil(){
        KettleModel kettleModel = new KettleModel();
        kettleModel.setJob("1111");
        kettleModel.setRep("1111");
        kettleModel.setUser("cui");
        kettleModel.setPass("ssss");
        Map<String, String> param = kettleModel.getParam();
        param.put("file","/usr/data");
        param.put("name","hengshui");

        String kitchenLinux = BuildCommandUtil.buildKitchenLinux("./", kettleModel);
        System.out.println(kitchenLinux);
    }
}
