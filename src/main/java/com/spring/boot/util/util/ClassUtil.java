package com.spring.boot.util.util;

import com.spring.boot.util.constant.CommonCode;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Common class related to Class operations.
 *
 * @author Zhengmin Yu
 */
public class ClassUtil {


    /**
     * Check if an object is empty.
     *
     * @param obj the object to check
     * @return true if the object is empty, false otherwise
     */
    public static boolean isEmpty(Object obj) {
        if (obj == NullUtil.OBJECT_NULL) {
            return CommonCode.TRUE;
        }
        return ObjectUtils.isEmpty(obj);
    }

    /**
     * Check if a class is empty.
     *
     * @param clazz the class to check
     * @return true if the class is empty, false otherwise
     */
    public static boolean isEmpty(Class<?> clazz) {
        return clazz == NullUtil.CLASS_NULL;
    }

    /**
     * Check if an object is not empty.
     *
     * @param obj the object to check
     * @return true if the object is not empty, false otherwise
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * Check if a class is not empty.
     *
     * @param clazz the class to check
     * @return true if the class is not empty, false otherwise
     */
    public static boolean isNotEmpty(Class<?> clazz) {
        return clazz != NullUtil.CLASS_NULL;
    }

    /**
     * Check if two classes are equal.
     *
     * @param clazz1 the first class
     * @param clazz2 the second class
     * @return true if the two classes are equal, false otherwise
     */
    public static boolean isEqual(Class<?> clazz1, Class<?> clazz2) {
        // Check if the entity type names are the same
        if (StringUtil.isNotEqual(clazz1.getName(), clazz2.getName())) {
            return CommonCode.FALSE;
        }
        // Check if the canonical names of the two classes are the same
        if (StringUtil.isNotEqual(clazz1.getCanonicalName(), clazz2.getName())) {
            return CommonCode.FALSE;
        }
        // Check if the simple names returned in the source code of the two classes are the same
        if (StringUtil.isNotEqual(clazz1.getSimpleName(), clazz2.getSimpleName())) {
            return CommonCode.FALSE;
        }
        // Check if the package names of the two classes are the same
        if (StringUtil.isNotEqual(clazz1.getPackageName(), clazz2.getPackageName())) {
            return CommonCode.FALSE;
        }
        // Check if the type names of the two classes are the same
        if (StringUtil.isNotEqual(clazz1.getTypeName(), clazz2.getTypeName())) {
            return CommonCode.FALSE;
        }
        // Check if all method names of the two classes are the same
        String clazz1Methods = Arrays.stream(clazz1.getMethods()).map(Method::getName).collect(Collectors.joining("_"));
        String clazz2Methods = Arrays.stream(clazz2.getMethods()).map(Method::getName).collect(Collectors.joining("_"));
        if (StringUtil.isNotEqual(clazz1Methods, clazz2Methods)) {
            return CommonCode.FALSE;
        }
        // Check if the total number of method parameters of the two classes is the same
        int clazz1ParameterCount = Arrays.stream(clazz1.getMethods()).mapToInt(Method::getParameterCount).sum();
        int clazz2ParameterCount = Arrays.stream(clazz2.getMethods()).mapToInt(Method::getParameterCount).sum();
        if (NumberUtil.isNotEqual(clazz1ParameterCount, clazz2ParameterCount)) {
            return CommonCode.FALSE;
        }
        // Check if the parameter types of all methods of the two classes are the same
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
     * Check if two classes are not equal.
     *
     * @param clazz1 the first class
     * @param clazz2 the second class
     * @return true if the two classes are not equal, false otherwise
     */
    public static boolean isNotEqual(Class<?> clazz1, Class<?> clazz2) {
        return !isEqual(clazz1, clazz2);
    }

    /**
     * Check if an object is an instance of Map.
     *
     * @param obj the object to check
     * @return true if the object is an instance of Map, false otherwise
     */
    public static boolean isMap(Object obj) {
        return obj instanceof Map;
    }

    /**
     * Check if an object is an instance of String.
     *
     * @param obj the object to check
     * @return true if the object is an instance of String, false otherwise
     */
    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    /**
     * Check if an object is an instance of Float.
     *
     * @param obj the object to check
     * @return true if the object is an instance of Float, false otherwise
     */
    public static boolean isFloat(Object obj) {
        return obj instanceof Float;
    }

    /**
     * Check if an object is an instance of Double.
     *
     * @param obj the object to check
     * @return true if the object is an instance of Double, false otherwise
     */
    public static boolean isDouble(Object obj) {
        return obj instanceof Double;
    }

    /**
     * Check if an object is an instance of Integer.
     *
     * @param obj the object to check
     * @return true if the object is an instance of Integer, false otherwise
     */
    public static boolean isInteger(Object obj) {
        return obj instanceof Integer;
    }

    /**
     * Convert an object to the specified class type.
     *
     * @param o     the object to convert
     * @param clazz the target class
     * @param <T>   the generic type
     * @return the converted object, or the null value defined by NullUtil if conversion fails
     */
    public static <T> T objectToClazz(Object o, Class<T> clazz) {
        if (clazz.isInstance(o)) {
            return clazz.cast(o);
        }
        return NullUtil.tNull();
    }

    /**
     * Convert an object to a Map type.
     *
     * @param o   the object to convert
     * @param <K> the key type of the map
     * @param <V> the value type of the map
     * @return the converted map, or the null value defined by NullUtil if conversion fails
     */
    public static <K, V> Map<K, V> objectToMap(Object o) {
        if (isMap(o)) {
            //noinspection unchecked
            return (Map<K, V>) o;
        }
        return NullUtil.tNull();
    }

}
