package com.spring.boot.util.util;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.util.number.MathUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * 关于数字的公共类方法
 *
 * @author Zhengmin Yu
 */
public class NumberUtil extends MathUtil {


    /**
     * 判断两个整数是否相等
     *
     * @param x 整数1
     * @param y 整数2
     * @return 相等
     */
    public static boolean isEqual(Integer x, Integer y) {
        return x.equals(y);
    }

    /**
     * 判断两个整数是否相等
     *
     * @param x 整数1
     * @param y 整数2
     * @return 相等
     */
    public static boolean isEqual(int x, int y) {
        return x == y;
    }

    /**
     * 判断两个整数是否相等
     *
     * @param x 整数1
     * @param y 整数2
     * @return 相等
     */
    public static boolean isEqual(Integer x, int y) {
        return x == y;
    }

    /**
     * 判断两个整数是否相等
     *
     * @param x 整数1
     * @param y 整数2
     * @return 相等
     */
    public static boolean isEqual(int x, Integer y) {
        return x == y;
    }

    /**
     * 判断两个整数是否相等-无序
     *
     * @param x 整数数组
     * @param y 整数数组
     * @return 相等
     */
    public static boolean isEqual(Integer[] x, Integer[] y) {
        return CollectionUtils.isEqualCollection(Arrays.asList(x), Arrays.asList(y));
    }

    /**
     * 判断两个整数数组是否相等-有序
     *
     * @param x 整数数组
     * @param y 整数数组
     * @return 相等
     */
    public static boolean isOrderEqual(Integer[] x, Integer[] y) {
        if (NumberUtil.isNotEqual(x.length, y.length)) {
            return CommonCode.FALSE;
        } else {
            Iterator<Integer> iteratorX = Arrays.stream(x).iterator();
            Iterator<Integer> iteratorY = Arrays.stream(y).iterator();
            while (iteratorX.hasNext() && iteratorY.hasNext()) {
                if (NumberUtil.isNotEqual(iteratorX.next(), iteratorY.next())) {
                    return CommonCode.FALSE;
                }
            }
            return CommonCode.TRUE;
        }
    }

    /**
     * 判断两个整数是否相等
     *
     * @param x 整数1
     * @param y 整数2
     * @return 不相等
     */
    public static boolean isNotEqual(Integer x, Integer y) {
        return !x.equals(y);
    }

    /**
     * 判断两个整数是否相等
     *
     * @param x 整数1
     * @param y 整数2
     * @return 不相等
     */
    public static boolean isNotEqual(int x, int y) {
        return x != y;
    }

    /**
     * 判断两个整数中第一个是否大于第二个
     *
     * @param x 整数1
     * @param y 整数2
     * @return 大于
     */
    public static boolean isFirstMax(int x, int y) {
        return x > y;
    }

    /**
     * 判断两个整数中第一个是否大于等于第二个
     *
     * @param x 整数1
     * @param y 整数2
     * @return 大于
     */
    public static boolean isFirstMaxEqual(int x, int y) {
        return x >= y;
    }

    /**
     * 判断两个整数中第一个是否小于等于第二个
     *
     * @param x 整数1
     * @param y 整数2
     * @return 小于
     */
    public static boolean isFirstMinEqual(int x, int y) {
        return x <= y;
    }

    /**
     * 判断两个整数中第一个是否小于第二个
     *
     * @param x 整数1
     * @param y 整数2
     * @return 小于
     */
    public static boolean isFirstMin(int x, int y) {
        return x < y;
    }

    /**
     * 判断整数是否相等零
     *
     * @param integer 整数
     * @return 相等
     */
    public static boolean isEqualZero(int integer) {
        return isEqual(integer, CommonCode.ZERO);
    }

    /**
     * 判断整数是否大于零
     *
     * @param integer 整数
     * @return 大于
     */
    public static boolean isGreaterThanZero(int integer) {
        return isFirstMax(integer, CommonCode.ZERO);
    }

    /**
     * 判断整数是否小于零
     *
     * @param integer 整数
     * @return 小于
     */
    public static boolean isLessThanZero(int integer) {
        return isFirstMax(CommonCode.ZERO, integer);
    }

    /**
     * 判断整数是否大于等于零
     *
     * @param integer 整数
     * @return 大于
     */
    public static boolean isGtEqualZero(int integer) {
        return isFirstMaxEqual(integer, CommonCode.ZERO);
    }

    /**
     * 判断整数是否小于等于零
     *
     * @param integer 整数
     * @return 小于
     */
    public static boolean isLtEqualZero(int integer) {
        return isFirstMaxEqual(CommonCode.ZERO, integer);
    }

    /**
     * 判断整数是否小于等于 1
     *
     * @param integer 整数
     * @return 小于
     */
    public static boolean isLtEqualOne(int integer) {
        return isFirstMaxEqual(CommonCode.ONE, integer);
    }


    /**
     * Integer 强制转化为 Long 类型
     *
     * @param integer 整数
     * @return Long 类型
     */
    public static Long integerToLong(Integer integer) {
        return (long) integer;
    }

    /**
     * Integer 强制转化为 Double 类型
     *
     * @param integer 整数
     * @return Long 类型
     */
    public static Double integerToDouble(Integer integer) {
        return (double) integer;
    }

    /**
     * 判断整数是否相等 0
     *
     * @param x 值
     * @return 等于
     */
    public static boolean isEqualZero(Integer x) {
        return x.equals(CommonCode.ZERO);
    }

    /**
     * 判断整数是否相等 1
     *
     * @param x 值
     * @return 等于
     */
    public static boolean isEqualOne(Integer x) {
        return x.equals(CommonCode.ONE);
    }

    /**
     * 判断整数是否相等 1
     *
     * @param x 值
     * @return 不等于
     */
    public static boolean isNotEqualOne(Integer x) {
        return !isEqualOne(x);
    }

    /**
     * 判断整数是否是偶数
     *
     * @param x 值
     * @return 是
     */
    public static boolean isEven(Integer x) {
        return x % 2 == 0;
    }

    /**
     * 判断整数是否是奇数
     *
     * @param x 值
     * @return 是
     */
    public static boolean isOdd(Integer x) {
        return !isEven(x);
    }

    /**
     * 判断两个数是否都偶数
     *
     * @param x 值
     * @param y 值
     * @return 是
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public static boolean isEqualsEven(Integer x, Integer y) {
        return isEven(x) && isEven(y);
    }

    /**
     * 判断两个数是否都为奇数
     *
     * @param x 值
     * @param y 值
     * @return 是
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public static boolean isEqualsOdd(Integer x, Integer y) {
        return isOdd(x) && isOdd(y);
    }

    /**
     * 判断两个数奇偶性是否相同
     *
     * @param x 值
     * @param y 值
     * @return 是
     */
    public static boolean isEqualsParity(Integer x, Integer y) {
        return isEqualsEven(x, y) || isEqualsOdd(x, y);
    }

    /**
     * 返回绝对值
     *
     * @param x 值
     * @return 是
     */
    public static Integer abs(Integer x) {
        return Math.abs(x);
    }


    /**
     * 确保这个数为正数
     * <pre>
     *     可以认为是一种容错机制，或者是纠正策略
     *     如果这个数为正数直接返回，为 0 则返回 1, 负数返回其相反数
     * </pre>
     *
     * @param x 值
     * @return 正数
     */
    public static Integer sureIsPositiveNumber(Integer x) {
        return isGtEqualZero(x) ? isEqualZero(x) ? CommonCode.ONE : x : abs(x);
    }

    /**
     * 判断两个数中 y 是否可以被 x 整除
     * <pre>
     *     x % y == 0
     * </pre>
     *
     * @param x 值
     * @param y 值
     * @return 是
     */
    public static boolean isJustDivisible(Integer x, Integer y) {
        return isEqual(isRemainder(x, y), CommonCode.ZERO);
    }

    /**
     * 判断两个数中 y 是否被 x 整除 后的余数
     * <pre>
     *     x % y
     * </pre>
     *
     * @param x 值
     * @param y 值
     * @return 是
     */
    public static Integer isRemainder(Integer x, Integer y) {
        return x % y;
    }

    /**
     * x 除以 y 得到是正数返回，不是正数向上取整
     *
     * @param x 值
     * @param y 值
     * @return 数
     */
    public static Integer divisionCeil(Integer x, Integer y) {
        return isJustDivisible(x, y) ? x / y : x / y + CommonCode.ONE;
    }

    /**
     * x 向上取整
     *
     * @param x 值
     * @return 数
     */
    public static Integer ceil(Double x) {
        return (int) Math.ceil(x);
    }

    /**
     * x 向下取整
     *
     * @param x 值
     * @return 数
     */
    public static Integer floor(Double x) {
        return (int) Math.floor(x);
    }

    /**
     * x 平方根
     *
     * @param x 值
     * @return 数
     */
    public static double sqrt(Integer x) {
        return Math.sqrt(x);
    }

    /**
     * x 平方根向下取整
     *
     * @param x 值
     * @return 数
     */
    public static Integer sqrtFloor(Integer x) {
        return floor(Math.sqrt(x));
    }

    /**
     * x 的 y 次方
     *
     * @param x 值
     * @param y 值
     * @return 数
     */
    @SuppressWarnings("SuspiciousNameCombination")
    public static Integer pow(Integer x, Integer y) {
        if (isEqualOne(x) || isEqualZero(y)) {
            return CommonCode.ONE;
        } else {
            return (int) Math.pow(x, y);
        }
    }

    /**
     * 本身加一
     *
     * @param number 数量
     * @return 加一
     */
    public static Long selfIncreaseOne(Long number) {
        return selfIncreaseN(number, CommonCode.ONE);
    }

    /**
     * 本身加 n
     *
     * @param number 数量
     * @return 加 n
     */
    public static Long selfIncreaseN(Long number, int n) {
        return number + n;
    }

    /**
     * 本身加一
     *
     * @param number 数量
     * @return 加一
     */
    public static Integer selfIncreaseOne(Integer number) {
        return number + CommonCode.ONE;
    }

    /**
     * 判断数值包类型是否为空
     *
     * @param number 数值
     * @return true 为空
     */
    public static boolean isEmpty(Integer number) {
        if (number == null) {
            return true;
        }
        return ObjectUtils.isEmpty(number);
    }

    /**
     * 判断数值包类型是否为空
     *
     * @param number 数值
     * @return true 为空
     */
    public static boolean isEmpty(Double number) {
        if (number == null) {
            return true;
        }
        return ObjectUtils.isEmpty(number);
    }

    /**
     * 字符串转 Integer
     *
     * @param number 数
     * @return Integer
     */
    public static Integer createInteger(String number) {
        return MathUtil.createInteger(StringUtil.trim(number));
    }

    /**
     * 字符串转 Double
     *
     * @param number 数
     * @return Double
     */
    public static Double createDouble(String number) {
        return MathUtil.createDouble(StringUtil.trim(number));
    }

    /**
     * 字符串转 BigDecimal
     *
     * @param number 数
     * @return BigDecimal
     */
    public static BigDecimal createBigDecimal(String number) {
        return MathUtil.createBigDecimal(StringUtil.trim(number));
    }

    /**
     * 数是否介于最小值和最大值之间
     *
     * @param number 数
     * @param min    最小值
     * @param max    最大值
     * @return true-介于
     */
    public static boolean betweenEqual(Integer number, Integer min, Integer max) {
        return isFirstMaxEqual(number, min) && isFirstMinEqual(number, max);
    }

    public static List<Double> zScoreNormalize(List<Double> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        // 计算均值
        double mean = data.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        // 计算标准差
        double variance = data.stream()
                .mapToDouble(d -> Math.pow(d - mean, 2))
                .average()
                .orElse(0.0);
        double stdDev = Math.sqrt(variance);

        // 如果标准差为 0（所有值相同），直接返回 0
        if (stdDev == 0) {
            return data.stream().map(d -> 0.0).toList();
        }

        // 标准化（Z-Score）
        return data.stream()
                .map(d -> (d - mean) / stdDev)
                .toList();
    }

    public static List<Double> minMaxNormalize(List<Double> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        // 计算最小值和最大值
        double min = data.stream().min(Double::compare).orElse(0.0);
        double max = data.stream().max(Double::compare).orElse(1.0);

        // 如果所有值相同，直接返回 0.5（避免除以 0）
        if (min == max) {
            return data.stream().map(d -> 0.5).toList();
        }

        // 标准化到 [0, 1]
        return data.stream()
                .map(d -> (d - min) / (max - min))
                .toList();
    }
}
