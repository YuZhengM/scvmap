package com.spring.boot.util.util;

import com.google.common.collect.Lists;
import com.spring.boot.util.constant.CommonCode;
import com.spring.boot.util.factory.LogFactory;
import com.spring.boot.util.factory.log.Log;
import com.spring.boot.util.util.number.MathUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 类型相关操作的公共类
 *
 * @author Zhengmin Yu
 */
public class StringUtil {

    private static final Log log = LogFactory.getLog(StringUtil.class);

    public static final int STRING_TO_LONG_MAX_VALUE = 2 * 9;

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 是空
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 非空
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * 用于获取 ID
     *
     * @return 表的唯一标识符
     */
    public static String getUniqueId() {
        // 用于拼接字符串
        StringBuilder stringBuffer = new StringBuilder();
        // 生成随机 UUID
        stringBuffer.append(UUID.randomUUID());
        // 雪花算法
        Integer randomMax31 = MathUtil.getRandomMax31();
        stringBuffer.append(MathUtil.getSnowflakeAlgorithm(randomMax31));
        // 添加雪花算法的随机数
        stringBuffer.append(randomMax31);
        // 形成
        String randomId = stringBuffer.toString().replaceAll("-", "");
        // 控制长度返回
        return StringUtil.appendBlankCut(randomId, 62);
    }

    /**
     * 正方向进行补位
     * <p>
     * 像数组中每个元素后面添加统一的内容 str
     * </p>
     *
     * @param str   内容
     * @param array 数组
     * @return 部位后的
     */
    public static String[] appendBlankAdd(String str, String... array) {
        String[] container = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            container[i] = array[i] + str;
        }
        return container;
    }

    /**
     * 负方向进行补位-字符串切割
     *
     * @param str 内容
     * @param n   个数
     * @return 部位后的
     */
    public static String appendBlankCut(String str, int n) {
        return splitBefore(str, n);
    }

    /**
     * 字符串切割
     *
     * @param str   内容
     * @param start 开始位置
     * @param end   结束位置
     * @return 切割部分
     */
    public static String split(String str, int start, int end) {
        return NumberUtil.isFirstMax(str.length(), end - start) ? str.substring(start, end) : str;
    }

    /**
     * 字符串切割-开始部位
     *
     * @param str 内容
     * @param end 结束位置
     * @return 切割部分
     */
    public static String splitBefore(String str, int end) {
        return split(str, CommonCode.ZERO, end);
    }

    /**
     * 字符串切割-结束部位
     *
     * @param str   内容
     * @param start 开始位置
     * @return 切割部分
     */
    public static String splitAfter(String str, int start) {
        return split(str, start, str.length());
    }

    /**
     * 获取扩展名称
     *
     * @param str 字符串
     * @return 文件后缀名
     */
    public static String getExtensionName(String str) {
        return str.substring(str.lastIndexOf(".") + 1);
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param str1 字符串
     * @param str2 字符串
     * @return true-不相等
     */
    public static boolean isEqual(String str1, String str2) {
        if (NullUtil.STRING_NOTHING.equals(str1) && NullUtil.STRING_NOTHING.equals(str2)) {
            return true;
        }
        return Objects.equals(str1, str2);
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param str1 字符串
     * @param str2 字符串
     * @return true-不相等
     */
    public static boolean isEqualIgnoreCase(String str1, String str2) {
        return isEqual(str1.toLowerCase(), str2.toLowerCase());
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param str1 字符串
     * @param str2 字符串
     * @return true-不相等
     */
    public static boolean isNotEqual(String str1, String str2) {
        return !isEqual(str1, str2);
    }

    /**
     * 匹配的字符串是否符合正则表达式
     *
     * @param regexExpression 正则表达式
     * @param matchString     匹配的字符串
     * @return 符合
     */
    @SuppressWarnings({"DeprecatedIsStillUsed", "RedundantSuppression"})
    @Deprecated
    public static boolean isRegexMatch(String regexExpression, String matchString) {
        // 匹配内容为空返回 false
        if (isEmpty(matchString)) {
            return CommonCode.FALSE;
        }
        // 建立匹配模型
        Pattern regex = Pattern.compile(regexExpression);
        // 进行匹配
        Matcher matcher = regex.matcher(matchString);
        // 返回匹配结果
        return matcher.matches();
    }

    /**
     * 匹配的字符串是否符合正则表达式
     * <p>
     * 所有正则匹配使用这个, 正则表达提前进行预编译
     * </p>
     *
     * @param regex       正则表达式
     * @param matchString 匹配的字符串
     * @return 符合
     */
    public static boolean isRegexMatch(Pattern regex, String matchString) {
        // 匹配内容为空返回 false
        if (isEmpty(matchString)) {
            return CommonCode.FALSE;
        }
        // 进行匹配
        Matcher matcher = regex.matcher(matchString);
        // 返回匹配结果
        return matcher.matches();
    }

    /**
     * 正则表达式匹配的字符串进行替换
     * <p>
     * 所有正则匹配使用这个, 正则表达提前进行预编译
     * </p>
     *
     * @param text          匹配的字符串
     * @param regex         正则表达式
     * @param replaceString 替换的字符串
     * @return 替换后的字符串
     */
    public static String replaceAll(String text, Pattern regex, String replaceString) {
        // 匹配内容为空返回 false
        if (isEmpty(text)) {
            return NullUtil.STRING_NOTHING;
        }
        // 进行匹配
        Matcher matcher = regex.matcher(text);
        // 返回匹配结果
        return matcher.replaceAll(replaceString);
    }

    /**
     * 判断数组中是否含有一个值
     *
     * @param value   值
     * @param content 数组
     * @return 含有
     */
    public static boolean isHave(String value, String... content) {
        for (String s : content) {
            if (isEqual(value, s)) {
                return CommonCode.TRUE;
            }
        }
        return CommonCode.FALSE;
    }

    /**
     * 判断数组中是否不含有一个值
     *
     * @param value   值
     * @param content 数组
     * @return 不含有
     */
    public static boolean isNotHave(String value, String... content) {
        return !isHave(value, content);
    }

    /**
     * 字符串是否包含数组中每个元素
     *
     * @param str   字符串
     * @param array 数组
     * @return 包含
     */
    public static boolean isStringContainElement(String str, String... array) {
        for (String s : array) {
            if (NumberUtil.isFirstMax(str.indexOf(s), CommonCode.ZERO)) {
                return CommonCode.TRUE;
            }
        }
        return CommonCode.FALSE;
    }

    /**
     * 判断 List 中是否不含有一个值
     *
     * @param value      值
     * @param stringList List
     * @return 含有
     */
    public static boolean isContain(String value, List<String> stringList) {
        return stringList.contains(value);
    }

    /**
     * 判断 List 中是否不含有一个值
     *
     * @param value      值
     * @param stringList List
     * @return 不含有
     */
    public static boolean isNotContain(String value, List<String> stringList) {
        return !stringList.contains(value);
    }

    /**
     * 字符串数组去重
     *
     * @param strings 字符串数组
     * @return 去重后的 List
     */
    public static List<String> duplicateRemoval(String... strings) {
        List<String> stringList = Lists.newArrayListWithCapacity(strings.length);
        for (String string : strings) {
            if (stringList.contains(string)) {
                stringList.add(string);
            }
        }
        return stringList;
    }

    /**
     * 将数组其中一个阶段变换为 List
     *
     * @param strings 数组
     * @param first   开始的阶段数
     * @param length  阶段的长度
     * @return List<String>
     */
    public static List<String> arrayToList(String[] strings, int first, int length) {
        if (NumberUtil.isFirstMax(first + length, strings.length)) {
            return NullUtil.listEmpty();
        }
        List<String> stringList = Lists.newArrayListWithCapacity(strings.length);
        int arrayLength = strings.length;
        stringList.addAll(Arrays.asList(strings).subList((arrayLength / length) * first, (arrayLength / length) * (first + 1)));
        return stringList;
    }


    public static List<String> getUniqueValue(List<String> data) {
        return new ArrayList<>(new HashSet<>(data));
    }

    public static int wordToNumberByAscii(String word) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            sum += (int) c;
        }
        return sum;
    }
}
