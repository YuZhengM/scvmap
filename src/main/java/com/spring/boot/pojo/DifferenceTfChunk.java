package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * t_difference_tf_sample_id_1
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Schema(name = "DifferenceTfChunk", description = "Table for difference TF information")
public class DifferenceTfChunk extends DifferenceTf implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
