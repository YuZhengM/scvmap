package com.spring.boot.util.constant;

import com.spring.boot.util.model.Result;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 一些共同的标识符代码
 *
 * @author Zhengmin Yu
 * @see Result
 */
public class CommonCode {

    /**
     * 项目名称小写
     */
    public static final String PROJECT_EN_L_NAME = "scvdb";
    /**
     * 项目名称
     */
    public static final String PROJECT_EN_NAME = "SCVDB";

    /**
     * 真状态码
     */
    public static final boolean TRUE = true;
    /**
     * 假状态码
     */
    public static final boolean FALSE = false;


    /**
     * 状态码-0
     * <p>
     * 这里可以进行衍生之意，比如 0 代表失败，未，奇，阴，下，北，右，女，假，坏等一系统对应词汇中负向抱阴之意
     * </p>
     */
    public static final int ZERO = NumberUtils.INTEGER_ZERO;
    public static final byte BYTE_ZERO = NumberUtils.BYTE_ZERO;
    /**
     * 状态码-1
     * <p>
     * 这里可以进行衍生之意，比如 1 代表成功，已，偶，阳，上，南，左，男，真，好等一系统对应词汇中正向抱阳之意
     * </p>
     */
    public static final int ONE = NumberUtils.INTEGER_ONE;
    public static final byte BYTE_ONE = NumberUtils.BYTE_ONE;

    /**
     * 状态码-999
     * <p>
     * 这里代表一切未知或者未初始化属性
     * </p>
     */
    public static final int UNKNOWN = 999;

    /**
     * 统一编码 UTF-8
     */
    public static final String UTF_8 = "UTF-8";
}
