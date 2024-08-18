package com.tutorial.springdatamongodbdynamicqueries.helpers;

import com.tutorial.springdatamongodbdynamicqueries.exception.BadRequestException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Service for Filtering Page
 * This class is used to extract any filters requested by the client.
 *
 * @author boottech
 */
@Component
public class FilterBuilder {

    private static final int DEFAULT_SIZE_PAGE = 20;

    private static final String FILTER_SEARCH_DELIMITER = ",";
    private static final String FILTER_CONDITION_DELIMITER = ";";

    /**
     * Prepare filter condition.  extract the different filters used in the controller via @RequestParam
     *
     * @param criteria search Criteria.
     * @return a list of {@link FilterCondition}
     */
    public List<FilterCondition> createFilterCondition(String criteria) {
        List<FilterCondition> filters = new ArrayList<>();

        try {

            if (StringUtils.hasLength(criteria)) {


                List<String> values = split(criteria, FILTER_SEARCH_DELIMITER);
                if (!CollectionUtils.isEmpty(values)) {
                    values.forEach(searchFilter -> {

                        // eg. field|eq|xxxx (| is filterConditionDelimiter)
                        List<String> filter = split(searchFilter, FILTER_CONDITION_DELIMITER);
                        // check if operator match
                        var operator = FilterOperation.fromValue(filter.get(1));
                        if (Objects.nonNull(operator)) {
                            filters.add(new FilterCondition(filter.get(0), operator, filter.get(2)));
                        }
                    });
                }
            }

            return filters;

        } catch (Exception ex) {
            throw new BadRequestException(MessageFormat.format("Cannot create condition filter . Error: {0}", ex.getMessage()));
        }

    }

    private static List<String> split(String search, String delimiter) {
        return Stream.of(search.split(delimiter)).toList();
    }

    /**
     * Get specification filters.
     *
     * @param filterOr  filters or conditions
     * @param filterAnd filters and conditions
     * @return SearchFilters
     */
    public SearchFilters getFilters(String filterAnd, String filterOr) {
        return new SearchFilters(createFilterCondition(filterAnd), createFilterCondition(filterOr));
    }

    /**
     * Get request pageable. Page Request Builder. custom pageable
     *
     * @param size   the number of items to collect
     * @param page   page number
     * @param orders search order filter (eg: field:ASC)
     * @return PageRequest
     */
    public PageRequest getPageable(int size, int page, String orders) {

        int pageSize = (size <= 0) ? DEFAULT_SIZE_PAGE : size;
        int currentPage = (page <= 0) ? 1 : page;
        try {
            var sorting = getSort(orders);

            return PageRequest.of((currentPage - 1), pageSize, sorting);

        } catch (Exception ex) {
            throw new BadRequestException(MessageFormat.format("Cannot create condition filter. Error: {0}", ex.getMessage()));
        }
    }

    /**
     * Get request pageable. Page Request Builder. custom pageable
     *
     * @param orders search order filter (eg: field:ASC)
     * @return PageRequest
     */
    public Sort getSort(String orders) {

        List<Sort.Order> orderList = new ArrayList<>();

        try {

            if (!StringUtils.hasLength(orders)) {
                return Sort.unsorted();
            }

            List<String> multipleOrder = split(orders, FILTER_SEARCH_DELIMITER);
            if (!CollectionUtils.isEmpty(multipleOrder)) {
                multipleOrder.forEach(order -> {

                    List<String> values = split(order, FILTER_CONDITION_DELIMITER);
                    if (values.size() != 2) {
                        throw new BadRequestException("Value for param 'order' is not valid");
                    }
                    String column = values.get(0);
                    String sortDirection = values.get(1);

                    if (sortDirection.equalsIgnoreCase("ASC")) {
                        orderList.add(new Sort.Order(Sort.Direction.ASC, column));
                    }

                    if (sortDirection.equalsIgnoreCase("DESC")) {
                        orderList.add(new Sort.Order(Sort.Direction.DESC, column));
                    }
                });

                return Sort.by(orderList);
            }

            return Sort.unsorted();

        } catch (Exception ex) {
            throw new BadRequestException(MessageFormat.format("Cannot create extract orders params. Error: {0}", ex.getMessage()));
        }
    }


}
