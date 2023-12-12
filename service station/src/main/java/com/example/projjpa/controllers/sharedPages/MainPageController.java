package com.example.projjpa.controllers.sharedPages;

import com.example.projjpa.models.Client;
import com.example.projjpa.services.notification.NotificationService;
import com.example.projjpa.services.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class MainPageController {
    private final NotificationService notificationService;
    private final UtilService utilService;
    @GetMapping({"","/","/mainpage"})
    public String showmain(Model model){
        model.addAttribute("isAuthorized", utilService.getfromSession("user") != null);
        if(utilService.getfromSession("user") != null){
            System.out.println(notificationService.count((Client) utilService.getfromSession("user")));
        model.addAttribute("count",notificationService.count((Client) utilService.getfromSession("user")));
        }
        return "mainpage";
    }
    @RequestMapping("/mainpage")
    public String logout(){
        return utilService.logout();
    }
}
