package com.spring.boot.util.util.number;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.util.NumberUtil;
import com.spring.boot.util.util.math.SnowflakeAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 关于数学方面的公共类
 *
 * @author Zhengmin Yu
 */
public class MathUtil extends ArrayUtil {

    private static final Logger log = LoggerFactory.getLogger(MathUtil.class);

    /**
     * 计算 2 的 n 次方的值，以 String 形式返回
     * <p>
     * 这里主要用于计算权限的权值，来代表特定的权限
     * </p>
     *
     * <note>
     * 这里注意的是用 Math 类中 pow 方法会返回科学计数法形式，且次方过大时候，转化为不是科学计数法时，精准度大大失调，故需要自己写
     * </note>
     *
     * @param n 代表 2 的 n 次方
     * @return 2 的 n 次方的值
     */
    public static String pow2(int n) {
        StringBuilder res = new StringBuilder("1");
        int ten = 10;
        // 重复 N 次
        for (int i = CommonCode.ZERO; i < n; i++) {
            // 进位标志，每轮清零
            int temp = CommonCode.ZERO;
            // result 中的字符，从前往后逐位 * 2
            for (int j = res.length() - CommonCode.ONE; j >= CommonCode.ZERO; j--) {
                // 乘法运算,需要加上进位
                temp = ((res.charAt(j) - '0') << CommonCode.ONE) + temp / ten;
                // 替换此位结果
                res.setCharAt(j, (char) (NumberUtil.isRemainder(temp, ten) + '0'));
            }
            // 产生进位则需添加新的数字
            if (temp / ten >= CommonCode.ONE) {
                res.insert(CommonCode.ZERO, '1');
            }
        }
        return res.toString();
    }

    /**
     * 用于存储数据库表中的唯一标识符 ID 的一部分内容，代表唯一
     *
     * @param n 输入构造数字
     * @return 雪花值
     */
    public static String getSnowflakeAlgorithmString(long n) {
        long snowflakeAlgorithm = new SnowflakeAlgorithm(n, n, n).nextId();
        return String.valueOf(snowflakeAlgorithm);
    }

    /**
     * 用于存储数据库表中的唯一标识符 ID 的一部分内容，代表唯一
     *
     * @param n 输入构造数字
     * @return 雪花值
     */
    public static long getSnowflakeAlgorithm(long n) {
        return new SnowflakeAlgorithm(n, n, n).nextId();
    }

    /**
     * 随机生成 [0, 31) 的整数
     *
     * @return 随机整数
     */
    public static Integer getRandomMax31() {
        int nextInt = getRandomInt(31);
        // 等于 0 在运行一次，降低生成 0 的概率
        if (nextInt == CommonCode.ZERO) {
            return getRandomInt(31);
        }
        return nextInt;
    }

    /**
     * 随机生成 [0, 10) 的整数, 形成 number 个数组
     *
     * @return 随机整数
     */
    public static Integer[] getNumberArray(int number) {
        Integer[] array = new Integer[number];
        for (int i = 0; i < number; i++) {
            array[i] = getRandomInt(10);
        }
        return array;
    }

    /**
     * 随机生成 [0, 10) 的整数, 形成 number 个数组
     *
     * @return 随机整数
     */
    public static List<Integer> getNumberList(int number) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            integerList.add(getRandomInt(10));
        }
        return integerList;
    }

    /**
     * 生成随机整数, [min, max)
     *
     * @param min 最小值
     * @param max 最大值
     * @return 随机值
     */
    public static Integer getRandomInt(Integer min, Integer max) {
        return new Random().nextInt(max - min) + min;
    }

    public static Double getRandomDouble() {
        return new Random().nextDouble();
    }

    public static double[] generateRandomCoordinates(double x1, double y1, double x2, double y2) {
        Random random = new Random();
        double randomX = x1 + (x2 - x1) * random.nextDouble();
        double randomY = y1 + (y2 - y1) * random.nextDouble();
        return new double[]{randomX, randomY};
    }

    public static double[] generateCircleRandomCoordinates(double centerX, double centerY, double minRadius, double maxRadius) {
        Random random = new Random();
        // 生成随机角度（0到2π）
        double angle = 2 * Math.PI * random.nextDouble();
        // 生成随机半径（minRadius到maxRadius）
        double randomRadius = minRadius + (maxRadius - minRadius) * random.nextDouble();
        // 计算笛卡尔坐标
        double randomX = centerX + randomRadius * Math.cos(angle);
        double randomY = centerY + randomRadius * Math.sin(angle);
        return new double[]{randomX, randomY};
    }

    /**
     * 生成随机整数, [0, max)
     *
     * @param max 最大值
     * @return 随机值
     */
    public static Integer getRandomInt(Integer max) {
        return new Random().nextInt(max);
    }

    /**
     * JWT 对时间的加密算法
     * <pre>
     *     |2^y-x^2-x|
     *     y 为 x^1/2 向下取整
     * </pre>
     *
     * @param x 值
     * @return 函数值
     */
    public static Integer jwtAlgorithm(Integer x) {
        int y = (int) Math.floor(Math.sqrt(x));
        return (int) Math.abs(Math.pow(2, y) - Math.pow(x, 2) - x);
    }


    /**
     * 判断两个数的最大公约数
     * <pre>
     *     该判断方法用的是辗转相除法-目前认定为最快的方法, 此方法也成为欧几里德算法
     *     时间复杂度 O(log n)
     * </pre>
     *
     * @param x 两个数其中一个
     * @param y 两个数其中一个
     * @return 最大公约数
     */
    public static Integer greatestCommonDivisor(Integer x, Integer y) {
        // 判断情况
        if (NumberUtil.isLessThanZero(x) || NumberUtil.isLessThanZero(y)) {
            return -1;
        }
        if (NumberUtil.isEqualZero(y)) {
            return x;
        }
        // 判断余数
        int temp = NumberUtil.isRemainder(x, y);
        if (NumberUtil.isEqualZero(temp)) {
            return y;
        } else {
            // 辗转相除
            int n = x;
            while (temp != 0) {
                temp = NumberUtil.isRemainder(n, y);
                n = y;
                y = temp;
            }
            return n;
        }
    }

    /**
     * 判断一个数除以那些数得到的还是整数
     * <p>
     * 就是输入这个数除以比这个数 (不妨设这个数为 n) 小于等于且不等于 1 的数 (不妨设这个数为 m, m 属于 (1, n] 整数), 得到的是否是整数，若是整数，则将 x 添加到其中
     * </p>
     *
     * @return 这些数的集合
     */
    public static List<Integer> subDivisible(Integer x) {
        // 确保 x 为正数
        x = NumberUtil.sureIsPositiveNumber(x);
        // 判断这个数是否为质数
        if (isPrimeNumber(x)) {
            return List.of(x);
        }
        List<Integer> array = new ArrayList<>();
        for (int i = 2; i < x - 1; i++) {
            if (NumberUtil.isRemainder(x, i) == 0) {
                NumberUtil.notContainsAdd(array, i);
            }
        }
        array.add(x);
        // 去重
        return array;
    }

    /**
     * 判断一个数是否是质数或者素数
     * <pre>
     * 进行循环到 根号下 x
     * </pre>
     *
     * @param x 正数
     * @return true-质数
     */
    public static boolean isPrimeNumber(Integer x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (NumberUtil.isJustDivisible(x, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 一个数返回一些满足下面条件的整数
     * <pre>
     * 1. 若这个数为正数，如果这个数为质数，则直接返回这个正数。
     * 2. 若这个数不为质数，则判断这个数有哪些属于 <code>subDivisible(Integer x)</code> 中返回的
     * 3. 将 2 步骤返回的整数数组进行处理，得到其无限倍数，中止无限便是小于等于输入的这个整数
     * </pre>
     * <p>
     * 用于矩阵移动的<font color="orange">排除数</font>
     * </p>
     *
     * @param x 输入的这个数
     * @return 满足条件的整数
     */
    public static List<Integer> subDivisibleMore(Integer x) {
        // 确保 x 为正数
        x = NumberUtil.sureIsPositiveNumber(x);
        // 判断这个数是否为质数
        if (isPrimeNumber(x)) {
            return List.of(x);
        }
        List<Integer> array = new ArrayList<>();
        // 由于矩阵移动 1 何时都满足，从 2 开始
        for (int i = 2; i < x - 1; i++) {
            if (NumberUtil.isJustDivisible(x, i)) {
                notContainsAdd(array, i);
                int j = i;
                // 添加其中倍数
                while (true) {
                    j *= 2;
                    if (j > x) {
                        break;
                    } else {
                        notContainsAdd(array, j);
                    }
                }
            }
        }
        notContainsAdd(array, x);
        return array;
    }


}
