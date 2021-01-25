package com.tutorial.springdatamongodbdynamicqueries.service;


import com.tutorial.springdatamongodbdynamicqueries.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface EmployeeService {


    /**
     * @param query custom query
     * @return list of Employee
     */
    List<Employee> getAll(Query query);

    /**
     * Get all custom paginate data for entity Employee
     *
     * @param query    custom query
     * @param pageable pageable param
     * @return Page of entity Employee
     */
    Page<Employee> getPage(Query query, Pageable pageable);
}