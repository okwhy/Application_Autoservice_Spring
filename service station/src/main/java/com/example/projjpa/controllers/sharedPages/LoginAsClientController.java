package com.example.projjpa.controllers.sharedPages;

import com.example.projjpa.models.Client;
import com.example.projjpa.services.client.ClientService;
import com.example.projjpa.services.util.UtilService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class LoginAsClientController {
    private final UtilService utilService;
    private final ClientService clientService;
    @GetMapping({"/login"})
    public String login(/*Model model*/){
        return "login";
    }
    @RequestMapping(value = "/login" ,params = "action=register", method = RequestMethod.POST)
    public String toRegister(){
        return "redirect:/register";
    }
    @RequestMapping(value = "/login",params ="action=login" ,   method = RequestMethod.POST)
    public String login(String login, String password, RedirectAttributes attributes){
        if(login.isBlank()||password.isBlank()){
            return "redirect:/login";
        }
        Client client=clientService.authentificate(login,password);
        if(client==null){
            return "redirect:/login";
        }
        utilService.addtoSession("user",client);
        return "redirect:/mainpage";
    }
}
