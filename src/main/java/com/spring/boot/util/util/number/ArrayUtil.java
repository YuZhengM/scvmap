package com.spring.boot.util.util.number;

import java.util.List;

/**
 * A collection of arrays of numeric types
 *
 * @author Zhengmin Yu
 */
public class ArrayUtil {

    /**
     * Check if the array contains the specified element
     *
     * @param array The array to check
     * @param n     The element to look for
     * @return true if the array contains the element, false otherwise
     */
    public static boolean isContains(List<Integer> array, Integer n) {
        return array.contains(n);
    }

    /**
     * Check if the array does not contain the specified element
     *
     * @param array The array to check
     * @param n     The element to look for
     * @return true if the array does not contain the element, false otherwise
     */
    public static boolean isNotContains(List<Integer> array, Integer n) {
        return !isContains(array, n);
    }

    /**
     * Add the element to the array if it does not already contain it
     *
     * @param array The array to add the element to
     * @param n     The element to add
     */
    public static void notContainsAdd(List<Integer> array, Integer n) {
        if (isNotContains(array, n)) {
            array.add(n);
        }
    }

}
