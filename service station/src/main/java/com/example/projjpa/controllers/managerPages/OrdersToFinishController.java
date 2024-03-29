package com.example.projjpa.controllers.managerPages;

import com.example.projjpa.services.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class OrdersToFinishController {

    private final OrderService orderService;
    @GetMapping("/orderstofinish")
    public String showorders(Model model){
        model.addAttribute("orders",orderService.getUnfinised());
        return "orderstofinish";
    }
}
