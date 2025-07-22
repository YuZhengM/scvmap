package com.spring.boot.util.constant;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Zhengmin Yu
 */
public class ApplicationConstant {

    public static final Pattern BIOSAMPLE_NAME_REGEX = Pattern.compile("\\s+");

    public static final String ALL_DATA_SYMBOL = "All";

    public static final Integer ALL_DATA_SYMBOL_NUMBER = -1;

    public static final String MYSQL_TABLE_PREFIX = "t_";

    public static final String MYSQL_FIELD_PREFIX = "f_";

    public static final String MYSQL_WILDCARD = "%";

    public static final String DEFAULT_METHOD = "SCAVENGE";

    public static final Map<Integer, String> MYSQL_SYMBOL_KEYWORD_MAP = Map.of(
            1, "=",
            2, "!=",
            3, ">",
            4, ">=",
            5, "<",
            6, "<=",
            7, "LIKE",
            8, "NOT LIKE",
            9, "LIKE",
            10, "LIKE"
    );

}
