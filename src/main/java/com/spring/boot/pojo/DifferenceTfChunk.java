package com.spring.boot.pojo;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * t_difference_tf_sample_id_1
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class DifferenceTfChunk extends DifferenceTf implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}