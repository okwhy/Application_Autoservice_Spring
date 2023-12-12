package com.example.projjpa.repos;


import com.example.projjpa.models.Employee;

import java.util.List;

public interface EmployeeJpaRepo extends CustomRepository<Employee,Long>{
    Employee findFirstByLoginAndPassword(String login, String password);
    List<Employee> findAllByManager(boolean manager);
}
