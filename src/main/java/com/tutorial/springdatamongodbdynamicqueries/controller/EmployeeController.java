package com.tutorial.springdatamongodbdynamicqueries.controller;

import com.tutorial.springdatamongodbdynamicqueries.domain.Employee;
import com.tutorial.springdatamongodbdynamicqueries.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping()
    public List<Employee> get() {
        return employeeService.getAll();

    }

    @PostMapping()
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping(value = "/{id}")
    public Employee update(@PathVariable("id") String id, @RequestBody Employee employee) {

        Optional<Employee> emp = employeeService.getById(id);
        if (emp.isPresent()) {
            emp.get().setFirstName(employee.getFirstName());
            emp.get().setLastName(employee.getLastName());
            emp.get().setEmail(employee.getEmail());

            return employeeService.save(emp.get());
        }
        throw new RuntimeException("not found");

    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") String id) {
        return employeeService.getById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") String id) {
        employeeService.deleteById(id);
    }


}