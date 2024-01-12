package com.example.projjpa.controllers.ClientPages;

import com.example.projjpa.models.Car;
import com.example.projjpa.models.Client;
import com.example.projjpa.services.car.CarService;
import com.example.projjpa.services.client.ClientService;
import com.example.projjpa.services.util.UtilService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@AllArgsConstructor
@Controller

public class ProfileController {
    private final ClientService clientService;
    private final UtilService utilService;
    private final CarService carService;
    @GetMapping("/profile")
    public String show(Model model,RedirectAttributes attributes){
        Client torefr=(Client)utilService.getfromSession("user");
        torefr=clientService.refresh(torefr);
        utilService.addtoSession("user",torefr);
        model.addAttribute("cars", ((Client)utilService.getfromSession("user")).getCars());
        model.addAttribute("car", new Car());
        return "profile";
    }


//    @RequestMapping(value = "/show")
//    public String showpanel(Model model , @RequestParam(value = "show", required = true) String show, RedirectAttributes attributes){
//        model.getAttribute("show");
//        utilService.switchAttr(attributes,model,show);
//
//
//        return "redirect:/profile";
//    }
    @SneakyThrows
    @RequestMapping(value ="/profile",method = RequestMethod.POST,params = "action=Submit")
    public String addCar(@Valid @ModelAttribute("car") Car car,BindingResult bindingResult, @RequestParam("pictureу") MultipartFile file
            ,Model model){

        if(bindingResult.hasErrors()){
            System.out.println("daf");
            Client torefr=(Client)utilService.getfromSession("user");
            torefr=clientService.refresh(torefr);
            utilService.addtoSession("user",torefr);
            model.addAttribute("cars", ((Client)utilService.getfromSession("user")).getCars());
            model.addAttribute("car", car);
            return "profile";
        }

        carService.addCar(car,(Client)utilService.getfromSession("user"),file);

        return "redirect:/profile";
    }
}
