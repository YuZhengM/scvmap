package com.spring.boot.util.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JSON 与 POJO 类对象之间的转化
 *
 * @author Zhengmin Yu
 */
public class JsonUtil {

    /**
     * 定义 jackson 对象
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成 json 字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     *
     * @param data data
     * @return String
     */
    public static String objectToJson(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return NullUtil.STRING_NULL;
    }

    /**
     * @param jsonData json 数据
     * @param beanType 对象中的 object 类型
     * @param <T>      T
     * @return <T>
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NullUtil.tNull();
    }

    /**
     * 将 json 数据转换成 pojo 对象 list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData json数据
     * @param beanType 对象中的 object 类型
     * @return <T>List<T>
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NullUtil.tNull();
    }

    /**
     * 将 json 数据转换成 Map 对象
     * <p>Title: jsonToMap</p>
     * <p>Description: </p>
     *
     * @param jsonData json数据
     * @param mapKey   对象中的 Key 类型
     * @param mapValue 对象中的 Value 类型
     * @return <T>List<T>
     */
    public static <K, V> Map<K, V> jsonToMap(String jsonData, Class<K> mapKey, Class<V> mapValue) {
        String json = jsonData.replaceAll("'", "\"");
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(HashMap.class, mapKey, mapValue);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NullUtil.tNull();
    }

}
