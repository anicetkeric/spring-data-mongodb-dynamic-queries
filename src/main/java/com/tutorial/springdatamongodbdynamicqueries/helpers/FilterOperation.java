package com.tutorial.springdatamongodbdynamicqueries.helpers;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * <h2>FilterOperation</h2>
 *
 * @author boottech
 * <p>
 * Description:
 */
@Getter
@AllArgsConstructor
public enum FilterOperation {

    EQUAL("eq"),
    NOT_EQUAL("neq"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL_TO("gte"),
    LESS_THAN("lt"),
    LESS_THAN_OR_EQUAL_TO("lte"),
    IN("in"),
    NOT_IN("nin"),
    BETWEEN("btn"),
    CONTAINS("like"),
    NOT_CONTAINS("notLike"),
    IS_NULL("isnull"),
    IS_NOT_NULL("isnotnull"),
    START_WITH("startwith"),
    END_WITH("endwith"),
    IS_EMPTY("isempty"),
    IS_NOT_EMPTY("isnotempty"),
    JOIN("jn");

    private final String value;

    public static FilterOperation fromValue(@NotNull String value) {
        return Arrays.stream(FilterOperation.values())
                .filter(val -> val.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
