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

    public static final String HOME_DEFAULT_TRAIT = "Trait or disease ==> All data";

    public static final String HOME_DEFAULT_SAMPLE = "scATAC-seq sample ==> All data";

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

    public static final String SAMPLE_EXAMPLE = "sample_id_1";

    public static final String TRAIT_EXAMPLE = "trait_id_894";

    public static final String CELL_TYPE_EXAMPLE = "Tumor 3";

    public static final String METHOD_EXAMPLE = "scavenge";

    public static final String STRATEGY_EXAMPLE = "mean";

    public static final String GENE_EXAMPLE = "RCC2";

    public static final String GENE_ID_EXAMPLE = "ENSG00000179051.14";

    public static final String TF_EXAMPLE = "HIF1A";

    public static final String CELL_RATE_EXAMPLE = "0.1";

    public static final String VARIANT_EXAMPLE = "rs57142672";

    public static final String GENOME_EXAMPLE = "hg19";

    public static final String EMAIL_ID_REPLACE_CODE = "0000000000";

    public static final String EMAIL_URL_REPLACE_CODE = "1111111111";

    public static final String FROM_EMAIL = "yuzhengmin.yzm@qq.com";

    public static final String CHECK_EMAIL = "yuzmbio@163.com";

}
