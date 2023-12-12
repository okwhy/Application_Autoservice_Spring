package com.example.projjpa.controllers.ClientPages;

import com.example.projjpa.models.Client;
import com.example.projjpa.models.Notification;
import com.example.projjpa.services.client.ClientService;
import com.example.projjpa.services.notification.NotificationService;
import com.example.projjpa.services.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class NotificationController {
    private final ClientService clientService;
    private final UtilService utilService;
    private final NotificationService notificationService;
    @GetMapping("/notifications")
    public String show(Model model, RedirectAttributes attributes){
        Client torefr=(Client)utilService.getfromSession("user");
        torefr=clientService.refresh(torefr);
        utilService.addtoSession("user",torefr);
        model.addAttribute("notifications",notificationService.recieve(torefr));
        return "notifications";
    }
    @RequestMapping(value = "/notifications",method = RequestMethod.POST,params = "action=read")
    public String read(@RequestParam(value = "notid")String id){
        Notification ref=notificationService.getById(Long.parseLong(id));
        notificationService.read(ref);
        return "redirect:/notifications";
    }
    @RequestMapping(value = "/notifications",method = RequestMethod.POST,params = "action=readall")
    public String readall(){
        Client torefr=(Client)utilService.getfromSession("user");
        notificationService.readall(torefr);
        return "redirect:/notifications";
    }
    @RequestMapping(value = "/notifications",method = RequestMethod.POST,params = "action=hideall")
    public String hideall(){
        Client torefr=(Client)utilService.getfromSession("user");
        notificationService.hideall(torefr);
        return "redirect:/notifications";
    }
    @RequestMapping(value = "/notifications",method = RequestMethod.POST,params = "action=hide")
    public String delete(@RequestParam(value = "notid")String id){
        Notification ref=notificationService.getById(Long.parseLong(id));
        notificationService.hide(ref);
        return "redirect:/notifications";
    }
}
