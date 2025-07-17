package com.spring.boot.util.util.string;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串类型的数组集合
 *
 * @author Zhengmin Yu
 */
public class ArrayUtil extends StringUtils {

    /**
     * 字符串数组左移
     *
     * @param strings 字符串
     * @param x       左移动的个数
     * @return 左移后的字符串数组
     */
    public static String[] leftMove(String[] strings, Integer x) {
        // 取长度
        int length = strings.length;
        if (NumberUtil.isEqualZero(x)) {
            return strings;
        } else if (NumberUtil.isGreaterThanZero(x)) {
            String[] array = new String[length];
            for (int i = CommonCode.ZERO; i < length; i++) {
                array[i] = NumberUtil.isFirstMin(i + x, length) ? strings[i + x] : strings[i + x - length];
            }
            return array;
        } else {
            // 为负数进行右移
            return rightMove(strings, Math.abs(x));
        }
    }

    /**
     * 字符串数组左移
     *
     * @param strings 字符串
     * @return 左移后的字符串数组
     */
    public static String[] leftMove(String[] strings) {
        return leftMove(strings, CommonCode.ONE);
    }

    /**
     * 字符串数组右移
     *
     * @param strings 字符串
     * @param x       右移动的个数
     * @return 右移后的字符串数组
     */
    public static String[] rightMove(String[] strings, Integer x) {
        // 取长度
        int length = strings.length;
        if (NumberUtil.isEqualZero(x)) {
            return strings;
        } else if (NumberUtil.isGreaterThanZero(x)) {
            String[] array = new String[length];
            for (int i = length - CommonCode.ONE; i >= CommonCode.ZERO; i--) {
                array[i] = NumberUtil.isGtEqualZero(i - x) ? strings[i - x] : strings[length + i - x];
            }
            return array;
        } else {
            // 为负数进行右移
            return leftMove(strings, Math.abs(x));
        }
    }

    /**
     * 字符串数组右移
     *
     * @param strings 字符串数组
     * @return 右移后的字符串数组
     */
    public static String[] rightMove(String[] strings) {
        return rightMove(strings, CommonCode.ONE);
    }

    /**
     * 字符串数组合并
     *
     * @param array 字符串数组集合
     * @return 右移后的字符串数组
     */
    public static String[] merge(String[]... array) {
        // 计算长度
        int length = CommonCode.ZERO;
        for (String[] strings : array) {
            length += strings.length;
        }
        // 初始化容器
        String[] merge = new String[length];
        // 进行添加计算
        int len = CommonCode.ZERO;
        for (String[] value : array) {
            System.arraycopy(value, CommonCode.ZERO, merge, len, value.length);
            len += value.length;
        }
        return merge;
    }

    /**
     * 字符串按照长度分割
     *
     * @param str    字符串
     * @param length 分割长度
     * @return 数组
     */
    public static List<String> splitGroup(String str, Integer length) {
        int strLength = str.length();
        // 判断
        if (NumberUtil.isFirstMaxEqual(length, strLength)) {
            return List.of(str);
        } else {
            List<String> stringList = new ArrayList<>();
            // 获取段数
            int number = NumberUtil.divisionCeil(strLength, length);
            // 添加每段
            stringList.add(str.substring(CommonCode.ZERO, length));
            for (int i = CommonCode.ONE; i < number - 1; i++) {
                stringList.add(str.substring(length * i, length * (i + CommonCode.ONE)));
            }
            stringList.add(str.substring(length * (number - 1)));
            return stringList;
        }
    }

    /**
     * 生成随机数字符串
     *
     * @param number 个数
     * @return 随机数字符串
     */
    public static String getNumberArray(Integer number) {
        StringBuilder stringBuilder = new StringBuilder(NumberUtil.getRandomInt(10));
        for (int i = 0; i < number; i++) {
            stringBuilder.append(NumberUtil.getRandomInt(10));
        }
        return stringBuilder.toString();
    }

    /**
     * 获取 value 值再 array 中第一次出现的索引
     *
     * @param array 数组
     * @param value 值
     * @return 索引
     */
    public static Integer indexOf(String[] array, String value) {
        return indexOf(Arrays.asList(array), value);
    }

    /**
     * 获取 value 值再 array 中第一次出现的索引
     *
     * @param array 数组
     * @param value 值
     * @return 索引
     */
    public static Integer indexOf(List<String> array, String value) {
        return array.indexOf(value);
    }

}
