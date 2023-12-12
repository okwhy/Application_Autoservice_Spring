package com.example.projjpa.controllers.managerPages;


import com.example.projjpa.models.Client;
import com.example.projjpa.models.Employee;
import com.example.projjpa.models.Job;
import com.example.projjpa.models.Order;
import com.example.projjpa.services.client.ClientService;
import com.example.projjpa.services.employee.EmployeeService;
import com.example.projjpa.services.job.JobService;
import com.example.projjpa.services.order.OrderService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Controller
public class AddingJobsToOrderController {
    private final OrderService orderService;
    private final EmployeeService employeeService;
    private final JobService jobService;
    @GetMapping("/addingjobs/{Id}")
    public String hideid(@PathVariable("Id") Long id, RedirectAttributes attributes){
        attributes.addFlashAttribute("id",id);

        return "redirect:/addingjobs";
    }
    @GetMapping("/addingjobs")
    public String show(Model model) {
        String id= String.valueOf(model.getAttribute("id"));
        Order ref=orderService.get(Long.parseLong(id));
        model.addAttribute("canFinish",orderService.canBeFinished
                (ref));
        Job job=new Job();

        model.addAttribute("newjob",job);
        List<Employee> employees=employeeService.get();
        Set<Job> jobs=ref.getJobs();
        model.addAttribute("jobs",jobs);
        model.addAttribute("employees", employees);
        return "addingjobs";

    }
    @RequestMapping(value = "/addingjobs",params="action=add")
    public String addJob(@ModelAttribute Job newjob, @RequestParam(value = "orderNum")String id){
        if(newjob.getDiscount()==null){
            newjob.setDiscount(0);
        }
        jobService.add(newjob,Long.parseLong(id));
//        jobService.add(newjob);
        return "redirect:/addingjobs/"+id;
    }
}
