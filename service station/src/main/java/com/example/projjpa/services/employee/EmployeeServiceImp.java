package com.example.projjpa.services.employee;

import com.example.projjpa.models.Employee;
import com.example.projjpa.repos.EmployeeJpaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class EmployeeServiceImp implements EmployeeService{
    private final EmployeeJpaRepo employeeJpaRepo;
    @Override
    public List<Employee> get() {
        return employeeJpaRepo.findAllByManager(false);
    }

    @Override
    public Employee getByID(Long id) {
        return employeeJpaRepo.findById(id).orElseThrow();
    }
}
