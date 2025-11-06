package com.example.ajira.controller;

import com.example.ajira.model.Employee;
import com.example.ajira.repository.EmployeeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Accessible by both ADMIN and MANAGER
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // ADMIN only
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // ADMIN only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    // ADMIN only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }
}
