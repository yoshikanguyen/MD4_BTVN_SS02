package com.example.employee_management.service;

import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        return employeeRepository.update(id, employee);
    }

    public boolean deleteEmployee(Long id) {
        return employeeRepository.delete(id);
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByName(name);
    }

    public List<Employee> filterEmployees(String name, String department) {
        return employeeRepository.filter(name, department);
    }
}
