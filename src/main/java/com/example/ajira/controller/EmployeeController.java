package com.example.ajira.controller;

import com.example.ajira.model.Employee;
import com.example.ajira.model.Role;
import com.example.ajira.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    // Only admin can create employee
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Admin and Manager can read all employees
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Admin can update
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setName(emp.getName());
        employee.setPosition(emp.getPosition());
        employee.setSalary(emp.getSalary());
        return employeeRepository.save(employee);
    }

    // Admin can delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted";
    }
}
