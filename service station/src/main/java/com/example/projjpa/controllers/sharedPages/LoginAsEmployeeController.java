package com.example.projjpa.controllers.sharedPages;

import com.example.projjpa.models.Employee;
import com.example.projjpa.services.employeeauth.EmployeeAuth;
import com.example.projjpa.services.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@AllArgsConstructor
@Controller
public class LoginAsEmployeeController {
    private final UtilService utilService;
    private final EmployeeAuth employeeAuth;
    @GetMapping("/loginCompany")
    public String showpage(){
        return "loginCompany";
    }
    @PostMapping("/loginCompany")
    public String login(String login,String password){
        if(login.isBlank()||password.isBlank()){
            return "redirect:/loginCompany";
        }
        Employee employee=employeeAuth.authentificate(login, password);
        if(employee==null){
            return "redirect:/loginCompany";
        }
        utilService.addtoSession("employee",employee);
        if(employee.getManager()){
            return "redirect:/managerprofile";
        }else {
            return "redirect:/employeeprofile";
        }

    }
}
