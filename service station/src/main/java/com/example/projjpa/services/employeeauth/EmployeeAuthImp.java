package com.example.projjpa.services.employeeauth;

import com.example.projjpa.models.Employee;
import com.example.projjpa.repos.EmployeeJpaRepo;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeAuthImp implements EmployeeAuth {
    private final EmployeeJpaRepo employeeJpaRepo;
    @Override
    public Employee authentificate(String login, String pass) {
        Employee tocheck=employeeJpaRepo.findFirstByLoginAndPassword(login,pass);
        if(tocheck==null){
            return null;
        }else{
            tocheck.setPassword(null);
            return tocheck;
        }
    }
}
