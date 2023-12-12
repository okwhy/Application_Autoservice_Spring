package com.example.projjpa.controllers.EmployeePages;

import com.example.projjpa.models.Job;
import com.example.projjpa.services.employee.EmployeeService;
import com.example.projjpa.services.job.JobService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
public class JobsToAcceptPageController {
    private final JobService jobService;
    private final EmployeeService employeeService;
    @GetMapping("/jobstoaccept/{Id}")
    public String hideid(@PathVariable("Id") Long id, RedirectAttributes attributes){

        attributes.addFlashAttribute("id",id);
        return "redirect:/jobstoaccept";
    }
    @GetMapping("/jobstoaccept")
    public String show(Model model){

        String id= String.valueOf(model.getAttribute("id"));
        List<Job> jobs=jobService.getJobsToDo(employeeService.getByID(Long.parseLong(id)));
        model.addAttribute("jobs",jobs);
        return "jobstoaccept";
    }
    @RequestMapping(value = "/jobstoaccept",method = RequestMethod.POST,params = "action=accept")
    public String accept(@RequestParam(value = "orderid") String id){
        return "redirect:/jobstoaccept/"+jobService.startJob(Long.parseLong(id));
    }
    @RequestMapping(value="/jobstoaccept",method =RequestMethod.POST,name = "action=inside")
    public String info(@RequestParam(value = "orderid") String id){
        return "redirect:/orderinfo/"+id;
    }
}
