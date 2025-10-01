package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@TableName("t_sample_time_sex_drug")
@Data
public class SampleTimeSexDrug implements Serializable {

    @Schema(description = "Unique identifier of the single-cell sample, used for database operations.")
    @TableField("f_sample_id")
    private String sampleId;

    @TableField("f_type_value")
    private String typeValue;

    @TableField("f_type_count")
    private Integer typeCount;

    @TableField("f_type")
    private String type;

}
