package com.tutorial.springdatamongodbdynamicqueries.service;

import com.tutorial.springdatamongodbdynamicqueries.domain.Employee;
import com.tutorial.springdatamongodbdynamicqueries.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        this.employeeRepository.deleteById(id);
    }

}