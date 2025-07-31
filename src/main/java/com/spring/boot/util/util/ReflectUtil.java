package com.spring.boot.util.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 反射相关的公共方法
 *
 * @author Zhengmin Yu
 */
public class ReflectUtil {


    /**
     * 取 Class 对象名称
     * <pre>
     *     For example: User class
     *     return com.example.project.pojo.User
     * </pre>
     *
     * @param obj 对象
     * @return 取 Class 对象名称
     */
    public static String getClassReference(Object obj) {
        Class<?> clazz = obj.getClass();
        return clazz.getName();
    }

    /**
     * 取对象全部属性名
     *
     * @param obj 对象
     * @return 成员信息
     */
    public static String[] getFiledName(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        return (String[]) Arrays.stream(fields).map(Field::getName).toArray();
    }

    /**
     * 获取指定静态对象
     *
     * @param clazz 对象
     * @return 成员信息
     */
    public static Object getStaticFiledValue(Class<?> clazz, String field) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> collect = Arrays.stream(fields).filter(item -> StringUtil.isEqual(item.getName(), field)).toList();
        if (NumberUtil.isEqualOne(collect.size())) {
            Field newField = collect.get(0);
            // 对 private 的属性解锁
            newField.setAccessible(true);
            try {
                return newField.get(clazz);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return NullUtil.OBJECT_NULL;
    }


}
