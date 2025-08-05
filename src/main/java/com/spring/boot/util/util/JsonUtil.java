package com.spring.boot.util.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Conversion between JSON and POJO class objects
 *
 * @author Zhengmin Yu
 */
public class JsonUtil {

    private static final Log log = LogFactory.getLog(JsonUtil.class);

    /**
     * Define the jackson object
     */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Convert an object to a JSON string.
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
            log.error("[objectToJson]: Failed to convert object to JSON string. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return NullUtil.STRING_NULL;
    }

    /**
     * @param jsonData JSON data
     * @param beanType Object type in the object
     * @param <T>      T
     * @return <T>
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            return MAPPER.readValue(jsonData, beanType);
        } catch (Exception e) {
            log.error("[jsonToPojo]: Failed to convert JSON data to POJO object. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return NullUtil.tNull();
    }

    /**
     * Convert JSON data to a list of POJO objects
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     *
     * @param jsonData JSON data
     * @param beanType Object type in the object
     * @return <T>List<T>
     */
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try {
            return MAPPER.readValue(jsonData, javaType);
        } catch (Exception e) {
            log.error("[jsonToList]: Failed to convert JSON data to a list of POJO objects. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return NullUtil.tNull();
    }

    /**
     * Convert JSON data to a Map object
     * <p>Title: jsonToMap</p>
     * <p>Description: </p>
     *
     * @param jsonData JSON data
     * @param mapKey   Key type in the object
     * @param mapValue Value type in the object
     * @return Map<K, V>
     */
    public static <K, V> Map<K, V> jsonToMap(String jsonData, Class<K> mapKey, Class<V> mapValue) {
        String json = jsonData.replaceAll("'", "\"");
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(HashMap.class, mapKey, mapValue);
        try {
            return MAPPER.readValue(json, javaType);
        } catch (Exception e) {
            log.error("[jsonToMap]: Failed to convert JSON data to Map object. {}, {}", e.getMessage(), e.getStackTrace());
        }
        return NullUtil.tNull();
    }

}
