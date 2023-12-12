package com.example.projjpa.controllers.managerPages;

import com.example.projjpa.services.order.OrderService;
import com.example.projjpa.services.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class MainManagerPageController {
    private final OrderService orderService;
    private final UtilService utilService;
    @GetMapping("/managerprofile")
    public String showpage(Model model){
        model.addAttribute("orderexists",orderService.existToCheck());
        model.addAttribute("ordertoaddexists",orderService.checkOrdersToAdd());
        model.addAttribute("ordertofinishexists",orderService.checkUnfinished());
        return "managerprofile";
    }
    @RequestMapping("/managerprofile")
    public String logout(){
        return utilService.logout();
    }
}
