package com.tutorial.springdatamongodbdynamicqueries.controller;

import com.tutorial.springdatamongodbdynamicqueries.document.Employee;
import com.tutorial.springdatamongodbdynamicqueries.helpers.FilterSortRegister;
import com.tutorial.springdatamongodbdynamicqueries.helpers.PageResponse;
import com.tutorial.springdatamongodbdynamicqueries.service.EmployeeService;
import org.springframework.data.domain.Page;
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

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
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
            @RequestParam(value = "or", required = false) String filterOr,
            @RequestParam(value = "and", required = false) String filterAnd,
            @RequestParam(value = "orders", required = false) String orders) {

        PageResponse<Employee> response = new PageResponse<>();

        var request = new FilterSortRegister(page, size, filterOr, filterAnd, orders);

        Page<Employee> pg = employeeService.getPage(request);
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
            @RequestParam(value = "or", required = false) String filterOr,
            @RequestParam(value = "and", required = false) String filterAnd,
            @RequestParam(value = "orders", required = false) String orders) {

        var request = new FilterSortRegister(0, 0, filterOr, filterAnd, orders);

        List<Employee> employees = employeeService.getAll(request);

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}