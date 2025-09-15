package com.spring.boot.util.constant;

import com.spring.boot.util.model.Result;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Some common identifier codes
 *
 * @author Zhengmin Yu
 * @see Result
 */
public class CommonCode {

    /**
     * Project name in lowercase
     */
    public static final String PROJECT_EN_L_NAME = "scvmap";
    /**
     * Project name
     */
    public static final String PROJECT_EN_NAME = "scVMAP";

    /**
     * True status code
     */
    public static final boolean TRUE = true;
    /**
     * False status code
     */
    public static final boolean FALSE = false;


    /**
     * Status code - 0
     * <p>
     * Derivative meanings can be made here. For example, 0 represents negative connotations such as failure, not done, odd, yin, down, north, right, female, false, bad, etc.
     * </p>
     */
    public static final int ZERO = NumberUtils.INTEGER_ZERO;
    public static final byte BYTE_ZERO = NumberUtils.BYTE_ZERO;
    /**
     * Status code - 1
     * <p>
     * Derivative meanings can be made here. For example, 1 represents positive connotations such as success, done, even, yang, up, south, left, male, true, good, etc.
     * </p>
     */
    public static final int ONE = NumberUtils.INTEGER_ONE;
    public static final byte BYTE_ONE = NumberUtils.BYTE_ONE;

    /**
     * Status code - 999
     * <p>
     * This represents all unknown or uninitialized attributes.
     * </p>
     */
    public static final int UNKNOWN = 999;

    /**
     * Unified encoding UTF-8
     */
    public static final String UTF_8 = "UTF-8";
}
