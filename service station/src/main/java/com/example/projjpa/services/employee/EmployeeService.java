package com.example.projjpa.services.employee;

import com.example.projjpa.models.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> get();
    Employee getByID(Long id);
}
