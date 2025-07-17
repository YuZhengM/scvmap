package com.spring.boot.util.util;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * null 类型形式
 *
 * @author Zhengmin Yu
 */
public class NullUtil {

    /**
     * <code>Object</code> 类型为空
     */
    public static final Object OBJECT_NULL = null;
    /**
     * <code>Object</code> 类型为空
     */
    public static final Class<?> CLASS_NULL = null;
    /**
     * <code>String</code> 类型为空 <code>null</code>
     */
    public static final String STRING_NULL = null;
    /**
     * <code>String</code> 类型为空字符
     */
    public static final String STRING_NOTHING = "";
    /**
     * <code>String[]</code> 类型为空 <code>null</code>
     */
    public static final String[] STRING_ARRAY_NULL = null;
    /**
     * <code>String[][]</code> 类型为空 <code>null</code>
     */
    public static final String[][] STRING_TWO_ARRAY_NULL = null;
    /**
     * <code>List<String></code> 类型为空 <code>null</code>
     */
    public static final List<String> STRING_LIST_NULL = null;
    /**
     * <code>List<String></code> 类型为空
     */
    public static final List<String> STRING_LIST_EMPTY = Collections.emptyList();
    /**
     * <code>Integer</code> 类型为空
     */
    public static final Integer INTEGER_NULL = null;
    /**
     * <code>Integer[]</code> 类型为空
     */
    public static final Integer[] INTEGER_ARRAY_NULL = null;
    /**
     * <code>Long</code> 类型为空
     */
    public static final Long LONG_NULL = null;
    /**
     * <code>Long[]</code> 类型为空
     */
    public static final Long[] LONG_ARRAY_NULL = null;
    /**
     * <code>Float</code> 类型为空
     */
    public static final Float FLOAT_NULL = null;
    /**
     * <code>Float[]</code> 类型为空
     */
    public static final Float[] FLOAT_ARRAY_NULL = null;
    /**
     * <code>Double</code> 类型为空
     */
    public static final Double DOUBLE_NULL = null;
    /**
     * <code>Double[]</code> 类型为空
     */
    public static final Double[] DOUBLE_ARRAY_NULL = null;
    /**
     * <code>Date</code> 类型为空
     */
    public static final Date DATE_NULL = null;

    /**
     * 泛型类型为空
     */
    public static <T> List<T> listNull() {
        return null;
    }

    /**
     * 泛型类型为空
     */
    public static <T> List<T> listEmpty() {
        return Collections.emptyList();
    }

    /**
     * 泛型类型为空
     */
    public static <K, V> Map<K, V> mapNull() {
        return null;
    }

    /**
     * 泛型类型为空
     */
    public static <K, V> Map<K, V> mapEmpty() {
        return Collections.emptyMap();
    }

    /**
     * 泛型类型为空
     */
    public static <T> T tNull() {
        return null;
    }

}
