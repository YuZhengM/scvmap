package com.spring.boot.util.util.result;

import java.util.List;

/**
 * description
 *
 * @author Zhengmin Yu
 */
public interface PageCallback<T> {

    /**
     * 回调函数
     *
     * @return List<T>
     */
    List<T> run();

}
