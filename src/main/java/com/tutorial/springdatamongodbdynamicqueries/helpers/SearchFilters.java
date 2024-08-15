package com.tutorial.springdatamongodbdynamicqueries.helpers;

import java.util.List;

/**
 * <h2>SearchRequest</h2>
 *
 * @author boottech
 * <p>
 * Description: Class Pagination
 */
public record SearchFilters(List<FilterCondition> filterAndConditions, List<FilterCondition> filterOrConditions) {
}
