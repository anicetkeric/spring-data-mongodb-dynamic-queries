package com.tutorial.springdatamongodbdynamicqueries.repository;

import com.tutorial.springdatamongodbdynamicqueries.domain.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ResourceRepository<Employee, String> {
}
