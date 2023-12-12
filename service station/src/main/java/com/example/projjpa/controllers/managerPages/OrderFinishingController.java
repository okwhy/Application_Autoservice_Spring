package com.example.projjpa.controllers.managerPages;

import com.example.projjpa.models.Job;
import com.example.projjpa.models.Order;
import com.example.projjpa.models.PartsInOrders;
import com.example.projjpa.services.hangarplace.HangarService;
import com.example.projjpa.services.job.JobService;
import com.example.projjpa.services.notification.NotificationService;
import com.example.projjpa.services.order.OrderService;
import com.example.projjpa.services.parts.orders.PartsInOrdersService;
import com.example.projjpa.services.parts.warehouse.PartsInWarehouseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class OrderFinishingController {
    private final PartsInWarehouseService partsInWarehouseService;
    private final PartsInOrdersService partsInOrdersService;
    private final JobService jobService;
    private final HangarService hangarService;
    private final OrderService orderService;
    private final NotificationService notificationService;
    @GetMapping("/finishorder/{Id}")
    public String hideid(@PathVariable("Id") Long id, RedirectAttributes attributes){

        attributes.addFlashAttribute("id",id);
        return "redirect:/finishorder";
    }
    @GetMapping("/finishorder")
    public String show(Model model){
        Long id= (Long) model.getAttribute("id");
        Order ref=orderService.get(id);
        model.addAttribute("totalprice",orderService.totalPrice(ref));
        model.addAttribute("jobs",ref.getJobs());
        model.addAttribute("tmpjob",new Job());
        model.addAttribute("tmppart",new PartsInOrders());
        return "/finishorder";
    }
    @RequestMapping(value = "/finishorder",params="action=editJob",method = RequestMethod.POST)
    public String editJob(@ModelAttribute Job tmpjob,@RequestParam(value = "idoforder")String id){
        jobService.add(tmpjob);
        return "redirect:/finishorder/"+id;
    }
    @RequestMapping(value = "/finishorder",params="action=editPart",method = RequestMethod.POST)
    public String editJob(@ModelAttribute PartsInOrders tmppart,@RequestParam(value = "idoforder")String id){
        partsInOrdersService.editDiscount(tmppart);
        return "redirect:/finishorder/"+id;
    }
    @RequestMapping(value = "/finishorder",params="action=end",method = RequestMethod.POST)
    public String finish(@RequestParam(value = "idoforder")String id){
        Order ref=orderService.get(Long.parseLong(id));
        hangarService.freePlace(ref);
        orderService.finish(ref);
        notificationService.send("Заказ с номером - "+ref.getId()+" готов",ref.getClient());
        return "redirect:/orderstofinish";
    }
}
