package com.example.projjpa.controllers.managerPages;

import com.example.projjpa.services.order.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class OrdersToEditController {
    private final OrderService orderService;
    @GetMapping("/orderstoadd")
    public String showpage(Model model){
        model.addAttribute("orders",orderService.getOrdersToAdd());
        return "orderstoadd";
    }
}
