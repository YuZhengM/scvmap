package com.spring.boot.util.util.result;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.boot.util.constant.SystemException;
import com.spring.boot.util.exception.RunException;
import com.spring.boot.util.util.StringUtil;

import java.util.Objects;

import static com.spring.boot.util.constant.ApplicationConstant.MYSQL_SYMBOL_KEYWORD_MAP;
import static com.spring.boot.util.constant.ApplicationConstant.MYSQL_WILDCARD;

/**
 * 关于分页的一些公共类
 *
 * @author Zhengmin Yu
 */
public class PageUtil {


    /**
     * 后端分页用户通过条件筛选的信息
     *
     * @param page         分页
     * @param queryWrapper 构造器
     */
    public static <T> void setQueryWrapper(Page page, QueryWrapper<T> queryWrapper) {
        if (StringUtil.isNotEmpty(page.getSearchField()) && !Objects.isNull(page.getContent()) && !page.getContent().isEmpty()) {
            switch (page.getSymbol()) {
                case 1:
                    queryWrapper.eq(page.getSearchField(), page.getContent());
                    break;
                case 2:
                    queryWrapper.ne(page.getSearchField(), page.getContent());
                    break;
                case 3:
                    queryWrapper.gt(page.getSearchField(), page.getContent());
                    break;
                case 4:
                    queryWrapper.ge(page.getSearchField(), page.getContent());
                    break;
                case 5:
                    queryWrapper.lt(page.getSearchField(), page.getContent());
                    break;
                case 6:
                    queryWrapper.le(page.getSearchField(), page.getContent());
                    break;
                case 7:
                    queryWrapper.like(page.getSearchField(), page.getContent());
                    break;
                case 8:
                    queryWrapper.notLike(page.getSearchField(), page.getContent());
                    break;
                case 9:
                    queryWrapper.likeRight(page.getSearchField(), page.getContent());
                    break;
                case 10:
                    queryWrapper.likeLeft(page.getSearchField(), page.getContent());
                    break;
                default:
                    throw new RunException(SystemException.ILLEGAL_PARAMETER);
            }
        }
    }

    /**
     * 后端分页用户通过条件筛选的信息
     *
     * @param page         分页
     * @param queryWrapper 构造器
     */
    public static <T> void setQueryWrapperByApply(Page page, QueryWrapper<T> queryWrapper) {
        String content = page.getContent();

        if (StringUtil.isNotEmpty(page.getSearchField()) && !Objects.isNull(content) && !content.isEmpty()) {
            int symbol = page.getSymbol();
            int type = page.getType();
            String field = page.getSearchField();

            String keyword = MYSQL_SYMBOL_KEYWORD_MAP.get(symbol);

            System.out.println(page);

            if (symbol < 7) {
                if (type == 1) {
                    queryWrapper.apply(field + " " + keyword + " {0}", content);
                } else if (type == 2) {
                    queryWrapper.apply(field + " * 1.0 " + keyword + " {0}", content);
                } else {
                    throw new RunException(SystemException.ILLEGAL_PARAMETER);
                }
            } else if (symbol == 7 || symbol == 8) {
                queryWrapper.apply(field + " " + keyword + " {0}", MYSQL_WILDCARD + content + MYSQL_WILDCARD);
            } else if (symbol == 9) {
                queryWrapper.apply(field + " " + keyword + " {0}", content + MYSQL_WILDCARD);
            } else if (symbol == 10) {
                queryWrapper.apply(field + " " + keyword + " {0}", MYSQL_WILDCARD + content);
            } else {
                throw new RunException(SystemException.ILLEGAL_PARAMETER);
            }
        }
    }

}
