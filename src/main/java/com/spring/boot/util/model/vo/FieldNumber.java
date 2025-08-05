package com.spring.boot.util.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * Field content quantity information
 *
 * @author Zhengmin Yu
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Schema(name = "FieldNumber", description = "Field content quantity information")
public class FieldNumber implements Serializable {

    /**
     * The content under a certain field
     */
    @Schema(description = "The content under a certain field")
    private String field;

    /**
     * The quantity of content under a certain field
     */
    @Schema(description = "The quantity of content under a certain field")
    private Integer number;

}
