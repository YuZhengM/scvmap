package com.spring.boot.util.model;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.constant.ResultCode;
import lombok.*;

/**
 * The data type format that the data Service layer responds to the front-end.
 *
 * @author Zhengmin Yu
 */
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {

    /**
     * Response return status
     *
     * @see ResultCode
     */
    @Builder.Default
    private int code = ResultCode.SUCCESS;

    /**
     * Response return status
     *
     * @see CommonCode
     */
    @Builder.Default
    private boolean status = CommonCode.TRUE;

    /**
     * Introduction to the response return situation
     */
    private String message;

}
