package com.example.projjpa.controllers.EmployeePages;

import com.example.projjpa.models.Employee;
import com.example.projjpa.models.Job;
import com.example.projjpa.models.PartsInOrders;
import com.example.projjpa.models.PartsInWarehouse;
import com.example.projjpa.services.job.JobService;
import com.example.projjpa.services.parts.orders.PartsInOrdersService;
import com.example.projjpa.services.parts.warehouse.PartsInWarehouseService;
import com.example.projjpa.services.util.UtilService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@AllArgsConstructor
@Controller
public class JobsToFinishController {
    private final JobService jobService;
    private final UtilService utilService;
    private final PartsInWarehouseService partsInWarehouseService;
    private final PartsInOrdersService partsInOrdersService;
    @GetMapping("/jobstofinish/{Id}")
    public String hideid(@PathVariable("Id") Long id, RedirectAttributes attributes){

        attributes.addFlashAttribute("id",id);
        return "redirect:/jobstofinish";
    }
    @GetMapping("/jobstofinish")
    public String show(Model model){
        List<PartsInWarehouse> partsAvailable=partsInWarehouseService.get();
        List<Job>jobs=jobService.getJobsToFinish((Employee)utilService.getfromSession("employee") );
        model.addAttribute("tmp",new PartsInOrders());
        model.addAttribute("tmp2",new PartsInOrders());
        model.addAttribute("jobs",jobs);
        model.addAttribute("parts",partsAvailable);
        return "jobstofinish";
    }
    @RequestMapping(value = "/jobstofinish",method = RequestMethod.POST,params = "action=finish")
    public String finish(@RequestParam(value="description") String desc,@RequestParam(value="jobid") String jobId,@RequestParam(value="id") String id){
        jobService.finish(jobService.get(Long.parseLong(jobId)),
                desc);
        return "redirect:/jobstofinish/"+id;
    }
    @RequestMapping(value = "/jobstofinish",method = RequestMethod.POST,params = "action=add")
    public String add(@ModelAttribute PartsInOrders tmp,@RequestParam(value="fakeid") String id/*,@RequestParam(value="idJob") String jobId*//*,@RequestParam(value="amount") String amount*/){
        System.out.println(tmp.getIdJob().getId()+ " <-------------------------");
        partsInOrdersService.add(tmp);
        return "redirect:/jobstofinish/"+id;
    }
    @RequestMapping(value = "/jobstofinish",method = RequestMethod.POST,params = "action=edit")
    public String edit(@ModelAttribute PartsInOrders tmp2, @RequestParam(value="fakeid") String id){
        partsInOrdersService.edit(tmp2);
        return "redirect:/jobstofinish/"+id;
    }
}
