package com.spring.boot.util.util;

import com.spring.boot.util.constant.CommonCode;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class 相关的公共类
 *
 * @author Zhengmin Yu
 */
public class ClassUtil {


    /**
     * 判断一个类型是否为空
     *
     * @param obj obj
     * @return 是空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == NullUtil.OBJECT_NULL) {
            return CommonCode.TRUE;
        }
        return ObjectUtils.isEmpty(obj);
    }

    /**
     * 判断一个类型是否为空
     *
     * @param clazz class
     * @return 是空
     */
    public static boolean isEmpty(Class<?> clazz) {
        return clazz == NullUtil.CLASS_NULL;
    }

    /**
     * 判断一个类是否为空
     *
     * @param obj class
     * @return 非空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * 判断一个类是否为空
     *
     * @param clazz class
     * @return 非空
     */
    public static boolean isNotEmpty(Class<?> clazz) {
        return clazz != NullUtil.CLASS_NULL;
    }

    /**
     * 判断两个类是否相等
     *
     * @param clazz1 类
     * @param clazz2 类
     * @return 相等
     */
    public static boolean isEqual(Class<?> clazz1, Class<?> clazz2) {
        // 判断实体类型名称是否相同
        if (StringUtil.isNotEqual(clazz1.getName(), clazz2.getName())) {
            return CommonCode.FALSE;
        }
        // 判断两个类规范定义的格式是否相同
        if (StringUtil.isNotEqual(clazz1.getCanonicalName(), clazz2.getName())) {
            return CommonCode.FALSE;
        }
        // 判断两个类源代码中返回实例的名称是否相同
        if (StringUtil.isNotEqual(clazz1.getSimpleName(), clazz2.getSimpleName())) {
            return CommonCode.FALSE;
        }
        // 判断两个类包名是否相同
        if (StringUtil.isNotEqual(clazz1.getPackageName(), clazz2.getPackageName())) {
            return CommonCode.FALSE;
        }
        // 判断两个类类型的名称是否相同
        if (StringUtil.isNotEqual(clazz1.getTypeName(), clazz2.getTypeName())) {
            return CommonCode.FALSE;
        }
        // 判断两个类的所有方法名是否相同
        String clazz1Methods = Arrays.stream(clazz1.getMethods()).map(Method::getName).collect(Collectors.joining("_"));
        String clazz2Methods = Arrays.stream(clazz2.getMethods()).map(Method::getName).collect(Collectors.joining("_"));
        if (StringUtil.isNotEqual(clazz1Methods, clazz2Methods)) {
            return CommonCode.FALSE;
        }
        // 判断两个类的所有方法参数数量是否相同
        int clazz1ParameterCount = Arrays.stream(clazz1.getMethods()).mapToInt(Method::getParameterCount).sum();
        int clazz2ParameterCount = Arrays.stream(clazz2.getMethods()).mapToInt(Method::getParameterCount).sum();
        if (NumberUtil.isNotEqual(clazz1ParameterCount, clazz2ParameterCount)) {
            return CommonCode.FALSE;
        }
        // 判断两个类的所有方法参数类型情况是否相同
        String clazz1ParameterTypes = Arrays.stream(clazz1.getMethods()).map(
                item -> Arrays.stream(item.getParameterTypes()).map(
                        clazz -> Arrays.stream(clazz.getDeclaredFields()).map(Field::getName).collect(Collectors.joining("_"))
                ).collect(Collectors.joining("_"))
        ).collect(Collectors.joining(""));
        String clazz2ParameterTypes = Arrays.stream(clazz2.getMethods()).map(
                item -> Arrays.stream(item.getParameterTypes()).map(
                        clazz -> Arrays.stream(clazz.getDeclaredFields()).map(Field::getName).collect(Collectors.joining("_"))
                ).collect(Collectors.joining("_"))
        ).collect(Collectors.joining(""));
        return StringUtil.isEqual(clazz1ParameterTypes, clazz2ParameterTypes);
    }

    /**
     * 判断两个类是否相等
     *
     * @param clazz1 类
     * @param clazz2 类
     * @return 不相等
     */
    public static boolean isNotEqual(Class<?> clazz1, Class<?> clazz2) {
        return !isEqual(clazz1, clazz2);
    }

    /**
     * 为一个 Class 创建唯一一个字符串
     *
     * @param clazz class
     * @return 字符串
     */
    public static String toUniqueString(Class<?> clazz) {
        StringBuilder stringBuilder = new StringBuilder();
        // 实体类型名称
        stringBuilder.append(clazz.getName());
        // 规范定义的格式
        stringBuilder.append(clazz.getCanonicalName());
        // 代码中返回实例的名称
        stringBuilder.append(clazz.getSimpleName());
        // 类包名
        stringBuilder.append(clazz.getPackageName());
        // 类型的名称
        stringBuilder.append(clazz.getTypeName());
        // 所有方法名
        String clazzMethods = Arrays.stream(clazz.getMethods()).map(Method::getName).collect(Collectors.joining("_"));
        stringBuilder.append(clazzMethods);
        // 所有方法参数数量
        int clazzParameterCount = Arrays.stream(clazz.getMethods()).mapToInt(Method::getParameterCount).sum();
        stringBuilder.append(clazzParameterCount);
        // 的所有方法参数类型情况
        String clazzParameterTypes = Arrays.stream(clazz.getMethods()).map(
                item -> Arrays.stream(item.getParameterTypes()).map(
                        elem -> Arrays.stream(elem.getDeclaredFields()).map(Field::getName).collect(Collectors.joining("_"))
                ).collect(Collectors.joining("_"))
        ).collect(Collectors.joining("_"));
        stringBuilder.append(clazzParameterTypes);
        return stringBuilder.toString();
    }

    /**
     * 判断 Object 是否符合 class 类型
     *
     * @param obj object
     * @return T
     */
    public static boolean isMap(Object obj) {
        return obj instanceof Map;
    }

    /**
     * 判断 Object 是否符合 class 类型
     *
     * @param obj object
     * @return T
     */
    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    /**
     * 判断 Object 是否符合 class 类型
     *
     * @param obj object
     * @return T
     */
    public static boolean isFloat(Object obj) {
        return obj instanceof Float;
    }

    /**
     * 判断 Object 是否符合 class 类型
     *
     * @param obj object
     * @return T
     */
    public static boolean isDouble(Object obj) {
        return obj instanceof Double;
    }

    /**
     * 判断 Object 是否符合 class 类型
     *
     * @param obj object
     * @return T
     */
    public static boolean isInteger(Object obj) {
        return obj instanceof Integer;
    }

    /**
     * Object 转换为 class 类型
     *
     * @param o     object
     * @param clazz class
     * @param <T>   T
     * @return T
     */
    public static <T> T objectToClazz(Object o, Class<T> clazz) {
        if (clazz.isInstance(o)) {
            return clazz.cast(o);
        }
        return NullUtil.tNull();
    }

    /**
     * Object 转换为 Map 类型
     *
     * @param o   object
     * @param <K> K
     * @param <V> V
     * @return Map
     */
    public static <K, V> Map<K, V> objectToMap(Object o) {
        if (isMap(o)) {
            //noinspection unchecked
            return (Map<K, V>) o;
        }
        return NullUtil.tNull();
    }

}
