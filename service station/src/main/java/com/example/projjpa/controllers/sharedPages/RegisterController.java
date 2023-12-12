package com.example.projjpa.controllers.sharedPages;

import com.example.projjpa.models.Client;
import com.example.projjpa.services.client.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
public class RegisterController {
    private final ClientService clientService;
    @GetMapping("/register")
    public String showPage(Model model){
        model.addAttribute("client",new Client());
        return "register";
    }
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public String addClientToDB(/*@Valid*/ @ModelAttribute Client client, RedirectAttributes attributes){
        if(clientService.canCreate(client.getLogin())){
            attributes.addFlashAttribute("msg");
            return "redirect:/register";
        }

        clientService.register(client);
        return "redirect:/login";
    }
}
