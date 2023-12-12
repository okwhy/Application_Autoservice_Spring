package com.example.projjpa.controllers.managerPages;

import com.example.projjpa.models.Employee;
import com.example.projjpa.models.Order;
import com.example.projjpa.services.hangarplace.HangarService;
import com.example.projjpa.services.notification.NotificationService;
import com.example.projjpa.services.order.OrderService;
import com.example.projjpa.services.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@AllArgsConstructor
@Controller
public class OrderProcedingController {
    private final OrderService orderService;
    private final HangarService hangarService;
    private final UtilService utilService;
    private final NotificationService notificationService;
    @GetMapping("/orderproceding/{Id}")
    public String hideid(@PathVariable("Id") Long id, RedirectAttributes attributes){
        attributes.addFlashAttribute("id",id);

        return "redirect:/orderproceding";
    }
    @GetMapping("/orderproceding")
    public String showpage(RedirectAttributes attributes, Model model){
        return "orderproceding";
    }
    @RequestMapping(value = "/orderproceding",params="action=accept")
    public String acceptOrder(@RequestParam("endinga") String todate,@RequestParam("id") String id){
        hangarService.takePlace(Long.parseLong(id),todate);
        orderService.start(Long.parseLong(id),
                (Employee) utilService.getfromSession("employee"),
                LocalDateTime.now());
        Order ref=orderService.get(Long.parseLong(id));
        notificationService.send("Заказ с номером - "+ref.getId()+" начал выполнятся," +
                "примерное время завершнения заказа - "+hangarService.getByOrder(ref).getEstimatedTime(),ref.getClient());
        return "redirect:/addingjobs/"+id;
    }
    @RequestMapping(value = "/orderproceding",params="action=reject")
    public String rejectOrder(@RequestParam("id") String id){
        Order ref=orderService.get(Long.valueOf(id));
        notificationService.send("Заказ с номером - "+ref.getId()+" отклонен",ref.getClient());
        orderService.reject(ref);
        return "managerprofile";
}
}
