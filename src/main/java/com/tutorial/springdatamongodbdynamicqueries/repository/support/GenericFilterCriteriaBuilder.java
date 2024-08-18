package com.tutorial.springdatamongodbdynamicqueries.repository.support;

import com.tutorial.springdatamongodbdynamicqueries.helpers.FilterCondition;
import com.tutorial.springdatamongodbdynamicqueries.helpers.SearchFilters;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;

/**
 * This class is used to build all the queries passed as parameters.
 * filterAndConditions (filter list for the AND operator)
 * filterOrConditions (filter list for the OR operator)
 */
public class GenericFilterCriteriaBuilder {

    private final List<FilterCondition> filterAndConditions;
    private final List<FilterCondition> filterOrConditions;

    private static final Map<String, Function<FilterCondition, Criteria>>
            FILTER_CRITERIA = new HashMap<>();

    // @formatter:off
    static {
        // Create map of filter
        FILTER_CRITERIA.put("EQUAL", condition -> Criteria.where(condition.field()).is(condition.value()));
        FILTER_CRITERIA.put("NOT_EQUAL", condition -> Criteria.where(condition.field()).ne(condition.value()));
        FILTER_CRITERIA.put("GREATER_THAN", condition -> Criteria.where(condition.field()).gt(condition.value()));
        FILTER_CRITERIA.put("GREATER_THAN_OR_EQUAL_TO", condition -> Criteria.where(condition.field()).gte(condition.value()));
        FILTER_CRITERIA.put("LESS_THAN", condition -> Criteria.where(condition.field()).lt(condition.value()));
        FILTER_CRITERIA.put("LESS_THAN_OR_EQUAL_TO", condition -> Criteria.where(condition.field()).lte(condition.value()));
        FILTER_CRITERIA.put("CONTAINS", condition -> Criteria.where(condition.field()).regex((String) condition.value()));
        FILTER_CRITERIA.put("JOIN", condition ->  Criteria.where(condition.field()).is(condition.value()));
    }
    // @formatter:on


    public GenericFilterCriteriaBuilder(SearchFilters searchFilters) {
        this.filterAndConditions = searchFilters.filterAndConditions();
        this.filterOrConditions = searchFilters.filterOrConditions();
    }

    public Query buildQuery() {

        List<Criteria> criteriaAndClause = new ArrayList<>();
        List<Criteria> criteriaOrClause = new ArrayList<>();
        var criteria = new Criteria();

        // build criteria
        filterAndConditions.forEach(condition -> criteriaAndClause.add(buildCriteria(condition)));
        filterOrConditions.forEach(condition -> criteriaOrClause.add(buildCriteria(condition)));

        if (!CollectionUtils.isEmpty(criteriaAndClause) && !CollectionUtils.isEmpty(criteriaOrClause)) {
            return new Query(criteria.andOperator(criteriaAndClause.toArray(new Criteria[0])).orOperator(criteriaOrClause.toArray(new Criteria[0])));
        }

        if (!CollectionUtils.isEmpty(criteriaAndClause)) {
            return new Query(criteria.andOperator(criteriaAndClause.toArray(new Criteria[0])));
        }
        if (!CollectionUtils.isEmpty(criteriaOrClause)) {
            return new Query(criteria.orOperator(criteriaOrClause.toArray(new Criteria[0])));
        }

        return new Query();
    }


    /**
     * Build the predicate according to the request
     *
     * @param condition The condition of the filter requested by the query
     * @return {@link Criteria}
     */
    private Criteria buildCriteria(FilterCondition condition) {
        Function<FilterCondition, Criteria>
                function = FILTER_CRITERIA.get(condition.operator().name());

        if (Objects.isNull(function)) {
            throw new IllegalArgumentException("Invalid function param type: ");
        }

        return function.apply(condition);
    }

}
