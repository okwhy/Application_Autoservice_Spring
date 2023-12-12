package com.example.projjpa.controllers.EmployeePages;

import com.example.projjpa.models.Employee;
import com.example.projjpa.models.Job;
import com.example.projjpa.services.employee.EmployeeService;
import com.example.projjpa.services.job.JobService;
import com.example.projjpa.services.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class MainEmployeePageController {
    private final JobService jobService;
    private final UtilService utilService;
    @GetMapping("/employeeprofile")
    public String show(Model model){
        boolean haveJobsToDo= jobService.existsJobsToDo((Employee) utilService.getfromSession("employee"));
        boolean haveJobsToFinish =jobService.existsJobsToFinish((Employee) utilService.getfromSession("employee"));
        model.addAttribute("id",((Employee)utilService.getfromSession("employee")).getId());
        model.addAttribute("todo",haveJobsToDo);
        model.addAttribute("tofinish",haveJobsToFinish);
        Job job=new Job();

        return "employeeprofile";
    }
    @RequestMapping("/employeeprofile")
    public String logout(){
        return utilService.logout();
    }

}
