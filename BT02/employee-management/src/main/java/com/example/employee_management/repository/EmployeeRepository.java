package com.example.employee_management.repository;

import com.example.employee_management.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employeeList = new ArrayList<>();

    public EmployeeRepository() {
        employeeList.add(new Employee(1L, "Nguyen Van A", "a@gmail.com", "IT"));
        employeeList.add(new Employee(2L, "Tran Thi B", "b@gmail.com", "HR"));
        employeeList.add(new Employee(3L, "Le Van C", "c@gmail.com", "IT"));
    }

    public List<Employee> findAll() {
        return employeeList;
    }

    public Optional<Employee> findById(Long id) {
        return employeeList.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Employee save(Employee employee) {
        // Tự động tăng ID đơn giản
        Long newId = employeeList.isEmpty() ? 1L : employeeList.get(employeeList.size() - 1).getId() + 1;
        employee.setId(newId);
        employeeList.add(employee);
        return employee;
    }

    public Employee update(Long id, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setFullName(employeeDetails.getFullName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setDepartment(employeeDetails.getDepartment());
            return employee;
        }
        return null;
    }

    public boolean delete(Long id) {
        return employeeList.removeIf(e -> e.getId().equals(id));
    }

    public List<Employee> findByName(String name) {
        return employeeList.stream()
                .filter(e -> e.getFullName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Employee> filter(String name, String department) {
        return employeeList.stream()
                .filter(e -> (name == null || e.getFullName().toLowerCase().contains(name.toLowerCase())) &&
                        (department == null || e.getDepartment().toLowerCase().contains(department.toLowerCase())))
                .collect(Collectors.toList());
    }
}
