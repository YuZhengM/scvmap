package com.spring.boot.util.util.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * Pagination request information
 *
 * @author Zhengmin Yu
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Schema(name = "Page", description = "Pagination request information")
public class Page implements Serializable {

    /**
     * Page number to display
     */
    @Schema(description = "Page number to display", defaultValue = "1")
    @Builder.Default
    private int page = 1;

    /**
     * Number of rows per page
     */
    @Schema(description = "Number of rows per page", defaultValue = "10")
    @Builder.Default
    private int size = 10;

    /**
     * Field for sorting
     */
    @Schema(description = "Field for sorting", example = "")
    private String field;

    /**
     * Sorting order type for the field.
     * 0: No sorting
     * 1: Ascending
     * -1: Descending
     */
    @Schema(description = "Sorting order type for the field. 0: No sorting, 1: Ascending, -1: Descending", defaultValue = "0")
    @Builder.Default
    private int order = 0;

    /**
     * Field name for searching
     */
    @Schema(description = "Field name for searching", example = "")
    private String searchField;

    /**
     * Content information of the search field
     */
    @Schema(description = "Content information of the search field", example = "")
    private String content;

    /**
     * Type information of the search field.
     * 1: String type
     * 2: Number type
     */
    @Schema(description = "Type information of the search field. 1: String type, 2: Number type", defaultValue = "1")
    @Builder.Default
    private int type = 1;

    /**
     * Symbol information representing operations.
     * 1: =
     * 2: !=
     * 3: >
     * 4: >=
     * 5: <
     * 6: <=
     * 7: contain
     * 8: not-contain
     * 9: starts with
     * 10: ends with
     */
    @Schema(description = "Symbol information representing operations. 1: =, 2: !=, 3: >, 4: >=, 5: <, 6: <=, 7: contain, 8: not-contain, 9: starts with, 10: ends with", defaultValue = "1")
    @Builder.Default
    private int symbol = 1;

}
