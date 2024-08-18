package com.tutorial.springdatamongodbdynamicqueries.helpers;

import java.util.List;

/**
 * <h2>SearchRequest</h2>
 *
 * @author boottech
 * <p>
 * Description: Use to build FilterCondition list-based controller params
 */
public record SearchFilters(List<FilterCondition> filterAndConditions, List<FilterCondition> filterOrConditions) {
}
