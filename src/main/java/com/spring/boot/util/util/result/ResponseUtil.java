package com.spring.boot.util.util.result;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.constant.ResultCode;
import com.spring.boot.util.model.Response;

/**
 * 关于返回 Service 一些常见的返回形式
 * 以及一些响应信息
 *
 * @author Zhengmin Yu
 */
public class ResponseUtil {

    /**
     * message 后缀
     */
    private static final String SUCCESS_SUFFIX = "-成功";
    private static final String FAIL_SUFFIX = "-失败";

    /**
     * 返回 T 类型的返回形式
     *
     * @param message 返回数据的内容
     * @return 正确情况
     */
    public static Response success(String message) {
        return Response.builder().message(message + SUCCESS_SUFFIX).build();
    }

    /**
     * 返回 T 类型的返回形式
     *
     * @param message 返回内容
     * @param code    状态码
     * @return 失败
     */
    public static Response fail(String message, int code) {
        return Response.builder().status(CommonCode.FALSE).message(message + FAIL_SUFFIX).code(code).build();
    }

    /**
     * 返回 T 类型的返回形式
     *
     * @param message 返回内容
     * @return 失败
     */
    public static Response fail(String message) {
        return fail(message, ResultCode.ILLEGAL_PARAMETER);
    }

    /**
     * 判定返回
     *
     * @param status  判定状态
     * @param message 响应内容
     * @return Response
     */
    public static Response judge(boolean status, String message) {
        return status ? success(message) : fail(message);
    }
}
