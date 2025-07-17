package com.spring.boot.util.util.number;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.util.NumberUtil;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * 数字类型的数组集合
 *
 * @author Zhengmin Yu
 */
public class ArrayUtil extends NumberUtils {

    /**
     * 数组中是否包含该元素
     *
     * @param array 数组
     * @param n     元素
     * @return 包含
     */
    public static boolean isContains(List<Integer> array, Integer n) {
        return array.contains(n);
    }

    /**
     * 数组中是否包含该元素
     *
     * @param array 数组
     * @param n     元素
     * @return 不包含
     */
    public static boolean isNotContains(List<Integer> array, Integer n) {
        return !isContains(array, n);
    }

    /**
     * 数组中不包含该元素就添加该元素
     *
     * @param array 数组
     * @param n     元素
     */
    public static void notContainsAdd(List<Integer> array, Integer n) {
        if (isNotContains(array, n)) {
            array.add(n);
        }
    }

    /**
     * 数字数组左移
     *
     * @param integers 字符串
     * @param x        左移动的个数
     * @return 左移后的字符串数组
     */
    public static Integer[] leftMove(Integer[] integers, Integer x) {
        // 取长度
        int length = integers.length;
        if (NumberUtil.isEqualZero(x)) {
            return integers;
        } else if (NumberUtil.isGreaterThanZero(x)) {
            Integer[] array = new Integer[length];
            for (int i = CommonCode.ZERO; i < length; i++) {
                array[i] = NumberUtil.isFirstMin(i + x, length) ? integers[i + x] : integers[i + x - length];
            }
            return array;
        } else {
            // 为负数进行右移
            return rightMove(integers, Math.abs(x));
        }
    }

    /**
     * 数字数组左移
     *
     * @param integers 字符串
     * @return 左移后的字符串数组
     */
    public static Integer[] leftMove(Integer[] integers) {
        return leftMove(integers, CommonCode.ONE);
    }

    /**
     * 数字数组右移
     *
     * @param integers 字符串
     * @param x        右移动的个数
     * @return 右移后的字符串数组
     */
    public static Integer[] rightMove(Integer[] integers, Integer x) {
        // 取长度
        int length = integers.length;
        if (NumberUtil.isEqualZero(x)) {
            return integers;
        } else if (NumberUtil.isGreaterThanZero(x)) {
            Integer[] array = new Integer[length];
            // 循环重新赋值
            for (int i = length - CommonCode.ONE; i >= CommonCode.ZERO; i--) {
                array[i] = NumberUtil.isGtEqualZero(i - x) ? integers[i - x] : integers[length + i - x];
            }
            return array;
        } else {
            // 为负数进行右移
            return leftMove(integers, Math.abs(x));
        }
    }

    /**
     * 数字数组右移
     *
     * @param integers 字符串
     * @return 右移后的字符串数组
     */
    public static Integer[] rightMove(Integer[] integers) {
        return rightMove(integers, CommonCode.ONE);
    }

}
