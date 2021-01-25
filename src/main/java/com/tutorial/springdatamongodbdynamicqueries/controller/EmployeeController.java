package com.tutorial.springdatamongodbdynamicqueries.controller;

import com.tutorial.springdatamongodbdynamicqueries.controller.dto.FilterCondition;
import com.tutorial.springdatamongodbdynamicqueries.domain.Employee;
import com.tutorial.springdatamongodbdynamicqueries.repository.support.GenericFilterCriteriaBuilder;
import com.tutorial.springdatamongodbdynamicqueries.service.EmployeeService;
import com.tutorial.springdatamongodbdynamicqueries.service.FilterBuilderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final FilterBuilderService filterBuilderService;

    public EmployeeController(EmployeeService employeeService, FilterBuilderService filterBuilderService) {
        this.employeeService = employeeService;
        this.filterBuilderService = filterBuilderService;
    }


    /**
     * @param page      page number
     * @param size      size count
     * @param filterOr  string filter or conditions
     * @param filterAnd string filter and conditions
     * @param orders    string orders
     * @return PageResponse<Employee>
     */
    @GetMapping(value = "/page")
    public ResponseEntity<PageResponse<Employee>> getSearchCriteriaPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd,
            @RequestParam(value = "orders", required = false) String orders) {

        PageResponse<Employee> response = new PageResponse<>();

        Pageable pageable = filterBuilderService.getPageable(size, page, orders);
        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();


        List<FilterCondition> andConditions = filterBuilderService.createFilterCondition(filterAnd);
        List<FilterCondition> orConditions = filterBuilderService.createFilterCondition(filterOr);

        Query query = filterCriteriaBuilder.addCondition(andConditions, orConditions);
        Page<Employee> pg = employeeService.getPage(query, pageable);
        response.setPageStats(pg, pg.getContent());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param filterOr  string filter or conditions
     * @param filterAnd string filter and conditions
     * @return list of Employee
     */
    @GetMapping()
    public ResponseEntity<List<Employee>> getAllSearchCriteria(
            @RequestParam(value = "filterOr", required = false) String filterOr,
            @RequestParam(value = "filterAnd", required = false) String filterAnd) {

        GenericFilterCriteriaBuilder filterCriteriaBuilder = new GenericFilterCriteriaBuilder();

        List<FilterCondition> andConditions = filterBuilderService.createFilterCondition(filterAnd);
        List<FilterCondition> orConditions = filterBuilderService.createFilterCondition(filterOr);

        Query query = filterCriteriaBuilder.addCondition(andConditions, orConditions);
        List<Employee> employees = employeeService.getAll(query);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


}