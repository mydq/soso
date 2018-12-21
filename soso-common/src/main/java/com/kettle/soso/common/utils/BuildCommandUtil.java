package com.kettle.soso.common.utils;

import com.kettle.soso.common.model.KettleModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: csz
 * @Date: 2018/12/20 12:51
 */
public class BuildCommandUtil {
    private static final Logger logger = LoggerFactory.getLogger(BuildCommandUtil.class);

    /**
     * 构建命令
     * @param basePath
     * @param kettleModel
     * @return
     */
    public static String buildKitchenLinux(String basePath, KettleModel kettleModel) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append(basePath).append(kettleModel.getCommand()).append(" ");
            for (Field declaredField : kettleModel.getClass().getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (StringUtils.equals("serialVersionUID", declaredField.getName()) || StringUtils.equals("command", declaredField.getName()) || Objects.isNull(declaredField.get(kettleModel))){
                    continue;
                }
                if (String.class.equals(declaredField.getType())){
                    stringBuilder.append("-").append(declaredField.getName()).append("=");
                    stringBuilder.append(declaredField.get(kettleModel)).append(" ");
                }else if (Map.class == declaredField.getType()){
                    String name = declaredField.getName();
                    Map map = (Map) declaredField.get(kettleModel);
                    for (Object o : map.keySet()) {
                        stringBuilder.append("-").append(name).append(":").append(o).append("=").append(map.get(o)).append(" ");
                    }
                }
            }
        }catch (Exception e){
            LoggerFactory.getLogger("my_info").warn("构建命令行错误",e);
        }
        return stringBuilder.toString();
    }


}
