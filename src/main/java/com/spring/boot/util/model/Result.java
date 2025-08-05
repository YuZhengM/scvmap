package com.spring.boot.util.model;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.constant.ResultCode;
import com.spring.boot.util.util.NullUtil;
import lombok.*;

import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;

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
    @Schema(name = "code", description = "Response return status.", example = "20")
    private int code = ResultCode.SUCCESS;

    /**
     * Response return status.
     *
     * @see CommonCode
     */
    @Builder.Default
    @Schema(name = "status", description = "Response return status.", example = "true")
    private boolean status = CommonCode.TRUE;

    /**
     * Introduction to the response return situation.
     */
    @Schema(name = "message", description = "Introduction to the response return situation.", example = "Success")
    private String message;

    /**
     * Response return data.
     */
    @Builder.Default
    @Schema(name = "data", description = "Response return data.")
    private T data = NullUtil.tNull();

}
