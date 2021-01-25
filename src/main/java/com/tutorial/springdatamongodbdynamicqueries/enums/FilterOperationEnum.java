/*
 * Copyright (c) 2019. @aek - (anicetkeric@gmail.com)
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.tutorial.springdatamongodbdynamicqueries.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <h2>FilterOperation</h2>
 *
 * @author aek
 * <p>
 * Description:
 */
public enum FilterOperationEnum {

    EQUAL("eq"),
    NOT_EQUAL("neq"),
    GREATER_THAN("gt"),
    GREATER_THAN_OR_EQUAL_TO("gte"),
    LESS_THAN("lt"),
    LESSTHAN_OR_EQUAL_TO("lte"),
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
    JOIN("jn"),
    IS("is");


    private final String value;

    FilterOperationEnum(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    public static FilterOperationEnum fromValue(String value) {
        for (FilterOperationEnum op : FilterOperationEnum.values()) {

            //Case insensitive operation name
            if (String.valueOf(op.value).equalsIgnoreCase(value)) {
                return op;
            }
        }
        return null;
    }

}
