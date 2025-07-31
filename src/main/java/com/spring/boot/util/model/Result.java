package com.spring.boot.util.model;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.constant.ResultCode;
import com.spring.boot.util.util.NullUtil;
import lombok.*;

import java.io.Serializable;

/**
 * The data type format for the data Controller layer to respond to the frontend.
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
     * Response return status.
     *
     * @see ResultCode
     */
    @Builder.Default
    private int code = ResultCode.SUCCESS;

    /**
     * Response return status.
     *
     * @see CommonCode
     */
    @Builder.Default
    private boolean status = CommonCode.TRUE;

    /**
     * Introduction to the response return situation.
     */
    private String message;

    /**
     * Response return data.
     */
    @Builder.Default
    private T data = NullUtil.tNull();

    /**
     * Construct the content Response settings.
     *
     * @param <T>
     */
    public static class Response<T> {

        /**
         * Response return status.
         *
         * @see ResultCode
         */
        private final int code;

        /**
         * Response return status.
         *
         * @see CommonCode
         */
        private final boolean status;

        /**
         * Introduction to the response return situation.
         */
        private final String message;

        /**
         * Constructor to assign data.
         *
         * @param response Response information
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
     * Constructor of the outer class.
     *
     * @param response Content response settings
     */
    private Result(Response<T> response) {
        this.code = response.code;
        this.status = response.status;
        this.message = response.message;
    }

    /**
     * Set static access to the internal Response.
     *
     * @param response Response returned by the Service
     * @param <T>      Generic type
     * @return Itself
     */
    public static <T> Result<T> response(com.spring.boot.util.model.Response response) {
        return new Response<T>(response).response();
    }

    /**
     * Member method to return itself, enabling chained calls.
     *
     * @param val Value
     * @return This class
     */
    public Result<T> data(T val) {
        this.data = val;
        return this;
    }

}
