package com.spring.boot.util.util.result;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.model.Result;

/**
 * 关于返回 Controller 一些常见的返回形式
 *
 * @author Zhengmin Yu
 */
public class ResultUtil {

    /**
     * message 后缀
     */
    private static final String SUCCESS_SUFFIX = "-success";
    private static final String FAIL_SUFFIX = "-fail";

    /**
     * 返回 T 类型的返回形式
     *
     * @param message 返回数据的内容
     * @param data    返回的数据
     * @return 正确情况
     */
    public static <T> Result<T> success(String message, T data) {
        return Result.<T>builder().message(message + SUCCESS_SUFFIX).data(data).build();
    }

    /**
     * 返回 T 类型的返回形式
     *
     * @param message 返回内容
     * @param code    状态码
     * @return 失败
     */
    public static <T> Result<T> fail(String message, int code) {
        return Result.<T>builder()
                .code(code)
                .status(CommonCode.FALSE)
                .message(message + FAIL_SUFFIX)
                .build();
    }

    /**
     * 返回 T 类型的返回形式
     *
     * @param message 返回内容
     * @param data    返回的数据
     * @param code    状态码
     * @return 失败
     */
    public static <T> Result<T> fail(String message, T data, int code) {
        return Result.<T>builder()
                .code(code)
                .status(CommonCode.FALSE)
                .message(message + FAIL_SUFFIX)
                .data(data)
                .build();
    }

    /**
     * 返回 Boolean 类型的返回形式
     *
     * @param message 返回数据的内容
     * @return 正确情况
     */
    public static Result<Boolean> success(String message) {
        return Result.<Boolean>builder().message(message + SUCCESS_SUFFIX).data(CommonCode.TRUE).build();
    }

    /**
     * 返回 T 类型的返回形式
     *
     * @param judge   判定是否成功
     * @param success 返回成功内容
     * @param fail    返回失败内容
     * @param data    返回数据的内容
     * @param code    返回数据或者状态码
     * @return 判定
     */
    public static <T> Result<T> judgeResult(boolean judge, String success, String fail, T data, int code) {
        return judge ? success(success, data) : fail(fail, code);
    }

    /**
     * 返回 Boolean 数据
     *
     * @param judge   判定是否成功
     * @param success 返回成功内容
     * @param fail    返回失败内容
     * @param code    返回数据或者状态码
     * @return 判定
     */
    public static Result<Boolean> judgeResult(boolean judge, String success, String fail, int code) {
        return judge ? success(success, CommonCode.TRUE) : fail(fail, CommonCode.FALSE, code);
    }

    /**
     * 返回 Boolean 数据
     *
     * @param judge   判定是否成功
     * @param message 返回内容
     * @param code    返回数据或者状态码
     * @return 判定
     */
    public static Result<Boolean> judgeResult(boolean judge, String message, int code) {
        return judge ? success(message, CommonCode.TRUE) : fail(message, CommonCode.FALSE, code);
    }

}
