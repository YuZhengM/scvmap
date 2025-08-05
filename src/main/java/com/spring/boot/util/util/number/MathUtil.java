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
 * A common class related to mathematics.
 *
 * @author Zhengmin Yu
 */
public class MathUtil extends ArrayUtil {

    private static final Logger log = LoggerFactory.getLogger(MathUtil.class);

    /**
     * Used to store a part of the unique identifier ID in the database table, representing uniqueness.
     *
     * @param n The input number for construction
     * @return Snowflake value
     */
    public static String getSnowflakeAlgorithmString(long n) {
        long snowflakeAlgorithm = new SnowflakeAlgorithm(n, n, n).nextId();
        return String.valueOf(snowflakeAlgorithm);
    }

    /**
     * Used to store a part of the unique identifier ID in the database table, representing uniqueness.
     *
     * @param n The input number for construction
     * @return Snowflake value
     */
    public static long getSnowflakeAlgorithm(long n) {
        return new SnowflakeAlgorithm(n, n, n).nextId();
    }

    /**
     * Randomly generate an integer in the range [0, 31).
     *
     * @return A random integer
     */
    public static Integer getRandomMax31() {
        int nextInt = getRandomInt(31);
        // Run again if it equals 0 to reduce the probability of generating 0.
        if (nextInt == CommonCode.ZERO) {
            return getRandomInt(31);
        }
        return nextInt;
    }

    /**
     * Randomly generate integers in the range [0, 10) to form an array of 'number' elements.
     *
     * @param number The number of elements in the array
     * @return An array of random integers
     */
    public static Integer[] getNumberArray(int number) {
        Integer[] array = new Integer[number];
        for (int i = 0; i < number; i++) {
            array[i] = getRandomInt(10);
        }
        return array;
    }

    /**
     * Randomly generate integers in the range [0, 10) to form a list of 'number' elements.
     *
     * @param number The number of elements in the list
     * @return A list of random integers
     */
    public static List<Integer> getNumberList(int number) {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            integerList.add(getRandomInt(10));
        }
        return integerList;
    }

    /**
     * Generate a random integer in the range [min, max).
     *
     * @param min The minimum value
     * @param max The maximum value
     * @return A random integer
     */
    public static Integer getRandomInt(Integer min, Integer max) {
        return new Random().nextInt(max - min) + min;
    }

    public static Double getRandomDouble() {
        return new Random().nextDouble();
    }

    public static double[] generateRandomCoordinates(double x1, double y1, double x2, double y2) {
        Random random = new Random();
        // Generate a random point within the specified rectangular area
        double randomX = x1 + (x2 - x1) * random.nextDouble();
        double randomY = y1 + (y2 - y1) * random.nextDouble();
        return new double[]{randomX, randomY};
    }

    public static double[] generateCircleRandomCoordinates(double centerX, double centerY, double minRadius, double maxRadius) {
        Random random = new Random();
        // Generate a random angle (0 to 2π)
        double angle = 2 * Math.PI * random.nextDouble();
        // Generate a random radius (minRadius to maxRadius)
        double randomRadius = minRadius + (maxRadius - minRadius) * random.nextDouble();
        // Calculate Cartesian coordinates
        double randomX = centerX + randomRadius * Math.cos(angle);
        double randomY = centerY + randomRadius * Math.sin(angle);
        return new double[]{randomX, randomY};
    }

    /**
     * Generate a random integer in the range [0, max).
     *
     * @param max The maximum value
     * @return A random integer
     */
    public static Integer getRandomInt(Integer max) {
        return new Random().nextInt(max);
    }

    /**
     * Determine the greatest common divisor of two numbers.
     * <pre>
     *     This method uses the Euclidean algorithm (also known as the division algorithm),
     *     which is currently considered the fastest method.
     *     Time complexity: O(log n)
     * </pre>
     *
     * @param x One of the two numbers
     * @param y The other of the two numbers
     * @return The greatest common divisor
     */
    public static Integer greatestCommonDivisor(Integer x, Integer y) {
        // Check the input values
        if (NumberUtil.isLessThanZero(x) || NumberUtil.isLessThanZero(y)) {
            return -1;
        }
        if (NumberUtil.isEqualZero(y)) {
            return x;
        }
        // Check the remainder
        int temp = NumberUtil.isRemainder(x, y);
        if (NumberUtil.isEqualZero(temp)) {
            return y;
        } else {
            // Perform the Euclidean algorithm
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
     * Determine whether a number is a prime number.
     * <pre>
     *     Loop up to the square root of x.
     * </pre>
     *
     * @param x A positive integer
     * @return true if it is a prime number
     */
    public static boolean isPrimeNumber(Integer x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (NumberUtil.isJustDivisible(x, i)) {
                return false;
            }
        }
        return true;
    }

}
