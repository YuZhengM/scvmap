package com.spring.boot.util.model;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.constant.ResultCode;
import com.spring.boot.util.util.NullUtil;
import lombok.*;

import java.io.Serializable;

/**
 * 数据 Controller 层向前端响应的数据类型形式
 *
 * @author Zhengmin Yu
 */
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> implements Serializable {

    /**
     * 响应返回状态
     *
     * @see ResultCode
     */
    @Builder.Default
    private int code = ResultCode.SUCCESS;

    /**
     * 响应返回状态
     *
     * @see CommonCode
     */
    @Builder.Default
    private boolean status = CommonCode.TRUE;

    /**
     * 响应返回情况介绍
     */
    private String message;

    /**
     * 响应返回数据
     */
    @Builder.Default
    private T data = NullUtil.tNull();

    /**
     * 构造内容 Response 设置
     *
     * @param <T>
     */
    public static class Response<T> {

        /**
         * 响应返回状态
         *
         * @see ResultCode
         */
        private final int code;

        /**
         * 响应返回状态
         *
         * @see CommonCode
         */
        private final boolean status;

        /**
         * 响应返回情况介绍
         */
        private final String message;

        /**
         * 构造函数赋值数据
         *
         * @param response 响应信息
         */
        public Response(com.spring.boot.util.model.Response response) {
            this.code = response.getCode();
            this.status = response.isStatus();
            this.message = response.getMessage();
        }

        public Result<T> response() {
            return new Result<>(this);
        }
    }

    /**
     * 外部类的构造函数
     *
     * @param response 内容响应设置
     */
    private Result(Response<T> response) {
        this.code = response.code;
        this.status = response.status;
        this.message = response.message;
    }

    /**
     * 设置静态访问内设 Response
     *
     * @param response Service 返回的 Response
     * @param <T>      泛型
     * @return 自身
     */
    public static <T> Result<T> response(com.spring.boot.util.model.Response response) {
        return new Response<T>(response).response();
    }

    /**
     * 成员方法返回其自身, 可以链式调用
     *
     * @param val 值
     * @return 本类
     */
    public Result<T> data(T val) {
        this.data = val;
        return this;
    }

}
