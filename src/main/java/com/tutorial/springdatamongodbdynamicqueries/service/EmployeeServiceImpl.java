package com.tutorial.springdatamongodbdynamicqueries.service;

import com.tutorial.springdatamongodbdynamicqueries.document.Employee;
import com.tutorial.springdatamongodbdynamicqueries.service.support.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, String> implements EmployeeService {
}