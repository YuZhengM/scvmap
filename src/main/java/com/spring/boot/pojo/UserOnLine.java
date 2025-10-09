package com.spring.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Builder
@TableName("t_user_on_line")
@Data
@Schema(name = "UserOnLine", description = "Users analyze uploaded information online.")
public class UserOnLine implements Serializable {

    @Schema(description = "Job ID")
    @TableId("f_job_id")
    private String jobId;

    @Schema(description = "Job status")
    @TableField("f_job_status")
    private String jobStatus;

    @Schema(description = "User email")
    @TableField("f_user_email")
    private String userEmail;

    @Schema(description = "scATAC-seq file ID")
    @TableField("f_sc_file_id")
    private String scFileId;

    @Schema(description = "Variant file ID")
    @TableField("f_variant_file_id")
    private String variantFileId;

    @Schema(description = "User IP")
    @TableField("f_user_ip")
    private String userIp;

    @Schema(description = "Genome")
    @TableField("f_genome")
    private String genome;

    @Schema(description = "Layer name")
    @TableField("f_layer")
    private String layer;

    @Schema(description = "Create time")
    @TableField("f_create_time")
    private Date createTime;

    @Schema(description = "Update time")
    @TableField("f_update_time")
    private Date updateTime;

    @TableField(exist = false)
    @Serial
    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
}
