package com.spring.boot.util.util;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.MapUtils;

import java.util.*;

/**
 * 关于 K-V 类型的公共类
 *
 * @author Zhengmin Yu
 */
public class MapUtil {

    /**
     * 判断 Map 是否为空
     *
     * @param map map
     * @param <K> K
     * @param <V> V
     * @return 为空
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 带有时间的 Map
     *
     * @param count 数量
     * @return Map
     */
    public static Map<String, Object> getMapWithTime(int count, String key) {
        Map<String, Object> map = new HashMap<>(count + 1);
        map.put(key, new Date());
        return map;
    }

    /**
     * 获取 Map 中 key 和 value 集合值
     *
     * @param map map
     */
    public static List<Object> listKeyAndValueByMap(Map<String, Object> map) {
        int size = map.size();
        // 创建容器
        List<String> keyList = Lists.newArrayListWithCapacity(size);
        List<Object> valueList = Lists.newArrayListWithCapacity(size);
        // 迭代添加
        map.forEach((key, value) -> {
            StringBuilder keys = new StringBuilder(key);
            // 由于列名尽然有 '.' 这个符号的存在, 在 java 中认为是属性
            if (ClassUtil.isMap(value)) {
                StringBuilder valueString = new StringBuilder();
                float valueFloat = 0F;
                double valueDouble = 0D;
                int valueInteger = 0;
                Object test = null;
                Map<String, Object> valueMap = ClassUtil.objectToMap(value);
                // 转化为 '.'
                Set<String> strings = valueMap.keySet();
                for (String string : strings) {
                    keys.append(".").append(string);
                }
                // 计算值, 正常都一个
                Collection<Object> collection = valueMap.values();
                for (Object o : collection) {
                    test = o;
                    // 判断类型
                    if (ClassUtil.isString(o)) {
                        valueString.append(o);
                    } else if (ClassUtil.isFloat(o)) {
                        valueFloat += (Float) o;
                    } else if (ClassUtil.isDouble(o)) {
                        valueDouble += (Double) o;
                    } else if (ClassUtil.isInteger(o)) {
                        valueInteger += (Integer) o;
                    }
                }
                if (ClassUtil.isString(test)) {
                    valueList.add(valueString);
                } else if (ClassUtil.isFloat(test)) {
                    valueList.add(valueFloat);
                } else if (ClassUtil.isDouble(test)) {
                    valueList.add(valueDouble);
                } else if (ClassUtil.isInteger(test)) {
                    valueList.add(valueInteger);
                }
            } else {
                valueList.add(value);
            }
            keyList.add(keys.toString());
        });
        List<Object> keyValueList = Lists.newArrayListWithCapacity(2);
        keyValueList.add(keyList);
        keyValueList.add(valueList);
        return keyValueList;
    }
}
