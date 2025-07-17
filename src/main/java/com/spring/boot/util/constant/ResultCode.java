package com.spring.boot.util.constant;

import com.spring.boot.util.model.Result;

/**
 * 数据 Controller 层向前端响应的数据类型中响应状态码
 * <pre>
 *     2 开头: 正常状态
 *     3000: 代表一切非正常状态
 *     3 开头: 非法状态
 *     4 开头: 权限限制等状态
 *     5 开头: 服务器问题等状态
 * </pre>
 *
 * @author Zhengmin Yu
 * @see Result
 */
public class ResultCode {


    /**
     * 成功状态码
     */
    public static final int SUCCESS = 20000;
    /**
     * 转发状态码
     */
    public static final int FORWARD = 20001;
    public static final int CLIENT_DISCONNECTED = 21001;
    /**
     * 失败状态码
     */
    @Deprecated
    public static final int FAIL = 30000;
    /**
     * 非法参数
     */
    public static final int ILLEGAL_PARAMETER = 30001;
    /**
     * 空参
     */
    public static final int ILLEGAL_NULL = 30002;
    /**
     * 无效操作
     */
    public static final int INVALID_OPERATION = 30003;
    /**
     * 无权限访问
     */
    public static final int NO_ACCESS = 40000;
    /**
     * 服务器问题
     */
    public static final int SERVER_PROBLEM = 50000;
}
