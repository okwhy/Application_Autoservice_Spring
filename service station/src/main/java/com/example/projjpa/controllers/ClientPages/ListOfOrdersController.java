package com.example.projjpa.controllers.ClientPages;

import com.example.projjpa.models.Job;
import com.example.projjpa.models.Order;
import com.example.projjpa.services.car.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@AllArgsConstructor
@Controller
public class ListOfOrdersController {
    private final CarService carService;
    @GetMapping("/listoforders/{Id}")
    public String viewPost(@PathVariable("Id") Long id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("id",id);
        return "redirect:/listoforders";
    }
    @GetMapping("/listoforders")
    public String showOrders(Model model){
        Set<Order> orders=carService.findFirstById((Long) model.getAttribute("id")).getOrders();
        model.addAttribute("orders",orders);
        return "listoforders";
    }
}
