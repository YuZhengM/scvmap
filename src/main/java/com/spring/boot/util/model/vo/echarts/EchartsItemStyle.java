package com.spring.boot.util.model.vo.echarts;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * ItemStyle 该节点的样式
 *
 * @author Zhengmin Yu
 */
@Builder
@ToString
@Data
public class EchartsItemStyle implements Serializable {

    private String color;

    /**
     * 图形的描边颜色
     */
    @Builder.Default
    private String borderColor = "#FFF";

    /**
     * 描边线宽
     */
    @Builder.Default
    private Integer borderWidth = 1;

    /**
     * 描边类型, 可选: "solid", "dashed", "dotted"
     */
    @Builder.Default
    private String borderType = "solid";

    /**
     * 用于设置虚线的偏移量
     */
    @Builder.Default
    private Integer borderDashOffset = 0;

    /**
     * 用于指定线段末端的绘制方式
     * 'butt': 线段末端以方形结束
     * 'round': 线段末端以圆形结束
     * 'square': 线段末端以方形结束
     */
    @Builder.Default
    private String borderCap = "butt";

    /**
     * 用于设置 2 个长度不为 0 的相连部分
     * 'bevel': 在相连部分的末端填充一个额外的以三角形为底的区域, 每个部分都有各自独立的矩形拐角
     * 'round': 通过填充一个额外的, 圆心在相连部分末端的扇形，绘制拐角的形状. 圆角的半径是线段的宽度
     * 'miter': 通过延伸相连部分的外边缘, 使其相交于一点, 形成一个额外的菱形区域. 这个设置可以通过 borderMiterLimit 属性看到效果
     */
    @Builder.Default
    private String borderJoin = "bevel";

    /**
     * 用于设置斜接面限制比例
     */
    @Builder.Default
    private Integer borderMiterLimit = 10;

    /**
     * 图形阴影的模糊大小
     */
    @Builder.Default
    private Integer shadowBlur = 10;

    /**
     * 图形阴影的颜色
     */
    @Builder.Default
    private String shadowColor = "#FFFFFF";

    /**
     * 阴影水平方向上的偏移距离
     */
    @Builder.Default
    private Double shadowOffsetX = 0D;

    /**
     * 阴影垂直方向上的偏移距离
     */
    @Builder.Default
    private Double shadowOffsetY = 0D;

    /**
     * 图形透明度
     */
    @Builder.Default
    private Double opacity = 1D;
}
