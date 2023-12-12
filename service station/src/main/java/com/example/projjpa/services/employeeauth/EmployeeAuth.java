package com.example.projjpa.services.employeeauth;

import com.example.projjpa.models.Employee;

public interface EmployeeAuth {
    Employee authentificate(String login,String pass);
}
