package com.kettle.soso.common;

import com.kettle.soso.common.model.KettleModel;
import com.kettle.soso.common.utils.BuildCommandUtil;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void searchBug(){
//        HashMap<Object, Object> objectObjectHashMap = new HashMap<>(1);

//        Collections.addAll();

        ArrayList<Number> objects = new ArrayList<>(1);
        objects.add(10);

        List<Number> integers = Arrays.asList(1, 2, 3, 4 ,5, null, Integer.MAX_VALUE + 1);

        boolean b = objects.addAll(integers);

        Collections.addAll(objects, 1, 2);

        System.out.println(b);

        objects.forEach(System.out::println);

//        ArrayList<Object> c = new ArrayList<>(1);
//
//        List<Integer> elements = Arrays.asList(1, 2, 3);
//
//        boolean result = false;
//        for (Integer element : elements)
//            result |= c.add(element);
//        System.out.println(result);
//        c.forEach(System.out::print);
//
//        System.out.println(result |= false);
    }
}
