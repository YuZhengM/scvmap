package com.spring.boot.util.util.result;

import com.github.pagehelper.PageHelper;
import com.spring.boot.util.model.PageResult;
import com.spring.boot.util.util.NullUtil;

import java.util.List;

/**
 * 分页公共类
 *
 * @author Zhengmin Yu
 */
public class PageResultUtil {

    /**
     * 分页返回
     *
     * @param page               翻页请求的参数信息
     * @param pageResultCallback 回调函数
     * @param <T>                T
     * @return PageResult<T>
     */
    public static <T> PageResult<T> format(Page page, PageCallback<T> pageResultCallback) {
        int order = page.getOrder();
        if (order == 0) {
            PageHelper.startPage(page.getPage(), page.getSize());
        } else if (order == 1) {
            PageHelper.startPage(page.getPage(), page.getSize(), page.getField() + " ASC");
        } else if (order == -1) {
            PageHelper.startPage(page.getPage(), page.getSize(), page.getField() + " DESC");
        }
        List<T> list = pageResultCallback.run();
        return new PageResult<>(list);
    }

    /**
     * 分页返回
     *
     * @param start              开始的行数
     * @param size               每页的行数
     * @param pageResultCallback 回调函数
     * @param <T>                T
     * @return PageResult<T>
     */
    public static <T> PageResult<T> format(int start, int size, PageCallback<T> pageResultCallback) {
        PageHelper.startPage(start, size);
        List<T> list = pageResultCallback.run();
        return new PageResult<>(list);
    }

    /**
     * 得到一个空集合
     *
     * @param <T> T
     * @return PageResult<T>
     */
    public static <T> PageResult<T> empty(Page page) {
        PageHelper.startPage(page.getPage(), page.getSize());
        List<T> list = NullUtil.listEmpty();
        return new PageResult<>(list);
    }

}
