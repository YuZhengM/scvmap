package com.spring.boot.util.util;

import com.google.common.collect.Lists;
import com.spring.boot.util.constant.CommonCode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 关于 List 集合操作
 *
 * @author Zhengmin Yu
 */
public class ListUtil {


    /**
     * 判断两个 List 是否相等
     *
     * @param list1 list1
     * @param list2 list2
     * @return 相等
     */
    public static boolean isEqualList(List<?> list1, List<?> list2) {
        return ListUtils.isEqualList(list1, list2);
    }

    /**
     * 判断两个 List 是否相等
     *
     * @param list1 list1
     * @param list2 list2
     * @return 不相等
     */
    public static boolean isNotEqualList(List<?> list1, List<?> list2) {
        return !isEqualList(list1, list2);
    }

    /**
     * 判断 List 是否为空
     *
     * @param list list
     * @return 为空
     */
    public static boolean isEmpty(List<?> list) {
        return CollectionUtils.isEmpty(list);
    }

    /**
     * 判断 List 是否为空
     *
     * @param list list
     * @return 不为空
     */
    public static <T> boolean isNotEmpty(List<T> list) {
        return !isEmpty(list);
    }

    /**
     * 判断 List<List<String>> 是否含有 List<String>
     *
     * @param list     list
     * @param listList list, list
     * @return 含有
     */
    public static boolean isContain(List<String> list, List<List<String>> listList) {
        if (isEmpty(listList)) {
            return CommonCode.FALSE;
        }
        for (List<String> stringList : listList) {
            if (isEqualList(stringList, list)) {
                return CommonCode.TRUE;
            }
        }
        return CommonCode.FALSE;
    }

    /**
     * 使 list 内的元素唯一
     *
     * @param list list
     * @return 元素唯一 list
     */
    public static List<String> uniqueList(List<String> list) {
        if (isEmpty(list)) {
            return NullUtil.listEmpty();
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 合并两个 list
     *
     * @param list1 list1
     * @param list2 list2
     * @return 合并后的 list
     */
    public static List<String> merge(List<String> list1, List<String> list2) {
        List<String> newList = Lists.newArrayListWithCapacity(list1.size() + list2.size());
        newList.addAll(list1);
        newList.addAll(list2);
        return newList;
    }

    /**
     * 合并多个 list
     *
     * @param list list1
     * @return 合并后的 list
     */
    @SafeVarargs
    public static List<String> merge(List<String>... list) {
        int length = list.length;
        if (NumberUtil.isEqualZero(length)) {
            return NullUtil.listEmpty();
        }
        if (NumberUtil.isEqualOne(length)) {
            return list[CommonCode.ZERO];
        }
        List<String> newList = Lists.newArrayListWithExpectedSize(length);
        for (List<String> strings : list) {
            newList.addAll(strings);
        }
        return newList;
    }

    /**
     * 合并多个 list
     *
     * @param list list1
     * @return 合并后的 list
     */
    @SafeVarargs
    public static List<String> mergeUnique(List<String>... list) {
        int length = list.length;
        if (NumberUtil.isEqualZero(length)) {
            return NullUtil.listEmpty();
        }
        if (NumberUtil.isEqualOne(length)) {
            return uniqueList(list[CommonCode.ZERO]);
        }
        List<String> newList = Lists.newArrayListWithExpectedSize(length);
        for (List<String> strings : list) {
            if (isEmpty(strings)) {
                continue;
            }
            for (String string : strings) {
                if (StringUtil.isNotContain(string, newList)) {
                    newList.add(string);
                }
            }
        }
        return newList;
    }
}
