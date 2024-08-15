package com.tutorial.springdatamongodbdynamicqueries.helpers;

/**
 * <h2>FilterCondition</h2>
 *
 * @author boottech
 * <p>
 * Description: Filter Condition Class
 */
public record FilterCondition(String field, FilterOperation operator, Object value) {
}
