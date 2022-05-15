package com.zhr.service.order.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * json工具类
 *
 * @author zhr_java@163.com
 * @date 2022/5/15 00:07
 */
public class JsonUtils {

    private static final ObjectMapper objectMappper = new ObjectMapper();

    //json字符串转JsonNode对象的方法
    public static JsonNode str2JsonNode(String str) {

        try {
            return objectMappper.readTree(str);
        } catch (IOException e) {
            return null;
        }
    }

}
