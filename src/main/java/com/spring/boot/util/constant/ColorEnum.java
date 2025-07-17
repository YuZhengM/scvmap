package com.spring.boot.util.constant;

import lombok.Getter;

/**
 * @author Zhengmin Yu
 */
@Getter
public enum ColorEnum {


    /**
     * 颜色信息
     */
    COLOR1(0, "#37A2DA"), COLOR2(1, "#e06343"), COLOR3(2, "#37a354"), COLOR4(3, "#b55dba"), COLOR5(4, "#b5bd48"), COLOR6(5, "#8378EA"), COLOR7(6, "#96BFFF"), COLOR8(7, "#b5bd48"), COLOR9(8, "#b5bd48");

    /**
     * 代码
     */
    private final int code;
    /**
     * 服务名
     */
    private final String color;

    ColorEnum(int code, String color) {
        this.code = code;
        this.color = color;
    }

    public static String getColor(int code) {
        ColorEnum[] colorEnums = values();
        for (ColorEnum colorEnum : colorEnums) {
            if (colorEnum.getCode() == code) {
                return colorEnum.getColor();
            }
        }
        return "#EEEEEE";
    }
}
