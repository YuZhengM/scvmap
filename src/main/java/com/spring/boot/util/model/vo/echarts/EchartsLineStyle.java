package com.spring.boot.util.model.vo.echarts;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Zhengmin Yu
 */
@Builder
@ToString
@Data
public class EchartsLineStyle implements Serializable {

    /**
     * 线的颜色
     */
    @Builder.Default
    private String color = "source";

    /**
     * 线宽
     */
    @Builder.Default
    private Double width = 1D;

    /**
     * 线的类型, 可选：solid, dashed, dotted
     */
    @Builder.Default
    private String type = "solid";

    /**
     * 用于设置虚线的偏移量
     */
    @Builder.Default
    private Double dashOffset = 0.1D;

    /**
     * 用于指定线段末端的绘制方式
     * 'butt': 线段末端以方形结束
     * 'round': 线段末端以圆形结束
     * 'square': 线段末端以方形结束, 但是增加了一个宽度和线段相同, 高度是线段厚度一半的矩形区域
     */
    @Builder.Default
    private String cap = "butt";

    /**
     * 用于设置 2 个长度不为 0 的相连部分
     * 'bevel': 在相连部分的末端填充一个额外的以三角形为底的区域, 每个部分都有各自独立的矩形拐角
     * 'round': 通过填充一个额外的, 心在相连部分末端的扇形, 绘制拐角的形状.  圆角的半径是线段的宽度
     * 'miter': 通过延伸相连部分的外边缘，使其相交于一点, 形成一个额外的菱形区域。这个设置可以通过 miterLimit 属性看到效果
     */
    @Builder.Default
    private String join = "bevel";

    /**
     * 用于设置斜接面限制比例
     */
    @Builder.Default
    private Double miterLimit = 10D;

    /**
     * 图形透明度。支持从 0 到 1 的数字，为 0 时不绘制该图形。
     */
    @Builder.Default
    private Double opacity = 0.9D;

    /**
     * 边的曲度，支持从 0 到 1 的值，值越大曲度越大。
     */
    @Builder.Default
    private Double curveness = 0D;

}
