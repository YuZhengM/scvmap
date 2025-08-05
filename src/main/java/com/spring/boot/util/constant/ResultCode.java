package com.spring.boot.util.constant;

import com.spring.boot.util.model.Result;

/**
 * Response status codes in the data type responded by the data Controller layer to the front-end
 * <pre>
 *     Starting with 2: Normal status
 *     3000: Represents all abnormal statuses
 *     Starting with 3: Illegal status
 *     Starting with 4: Permission-restricted statuses
 *     Starting with 5: Server issues and other statuses
 * </pre>
 *
 * @author Zhengmin Yu
 * @see Result
 */
public class ResultCode {


    /**
     * Success status code
     */
    public static final int SUCCESS = 20000;
    /**
     * Forward status code
     */
    public static final int FORWARD = 20001;
    /**
     * Client disconnected status code
     */
    public static final int CLIENT_DISCONNECTED = 21001;
    /**
     * Failure status code
     */
    @Deprecated
    public static final int FAIL = 30000;
    /**
     * Illegal parameter
     */
    public static final int ILLEGAL_PARAMETER = 30001;
    /**
     * Null parameter
     */
    public static final int ILLEGAL_NULL = 30002;
    /**
     * Invalid operation
     */
    public static final int INVALID_OPERATION = 30003;
    /**
     * No access permission
     */
    public static final int NO_ACCESS = 40000;
    /**
     * Server problem
     */
    public static final int SERVER_PROBLEM = 50000;
}
