package com.example.projjpa.controllers.managerPages;

import com.example.projjpa.models.Order;
import com.example.projjpa.services.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class OrderAcceptingController {
    private final OrderService orderService;
    @GetMapping("/orderstoaccept")
    public String showPage(Model model){
        if(orderService.existToCheck()){
            List<Order> orders=orderService.getOrdersToCheck();
            model.addAttribute("orders",orders);
            return "orderstoaccept";
        }
        return "orderstoaccept";
    }
}
