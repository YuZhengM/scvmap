package com.spring.boot.util.model;

import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.constant.ResultCode;
import lombok.*;

/**
 * 数据 Service 层向前端响应的数据类型形式
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


}
