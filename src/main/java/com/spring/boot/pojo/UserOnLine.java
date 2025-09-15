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
public class UserOnLine implements Serializable {

    @TableId("f_job_id")
    private String jobId;

    @TableField("f_job_status")
    private String jobStatus;

    @TableField("f_user_email")
    private String userEmail;

    @TableField("f_sc_file_id")
    private String scFileId;

    @TableField("f_variant_file_id")
    private String variantFileId;

    @TableField("f_user_ip")
    private String userIp;

    @TableField("f_genome")
    private String genome;

    @TableField("f_layer")
    private String layer;

    @TableField("f_create_time")
    private Date createTime;

    @TableField("f_update_time")
    private Date updateTime;

    @TableField(exist = false)
    @Serial
    @Schema(hidden = true)
    private static final long serialVersionUID = 1L;
}
