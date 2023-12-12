package com.example.projjpa.controllers.ClientPages;

import com.example.projjpa.config.OrderTemplate;
import com.example.projjpa.models.Client;
import com.example.projjpa.models.Order;
import com.example.projjpa.repos.HangarPlaceJpaRepo;
import com.example.projjpa.services.car.CarService;
import com.example.projjpa.services.hangarplace.HangarService;
import com.example.projjpa.services.order.OrderService;
import com.example.projjpa.services.util.UtilService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderingController {
    private final OrderService orderService;
    private final UtilService utilService;
    private final CarService carService;
    private final HangarService hangarService;
    private final List<OrderTemplate> templates;
    @GetMapping("/ordering/{Id}")
    public String viewPost(@PathVariable("Id") Long id, RedirectAttributes attributes) {
        attributes.addFlashAttribute("id",id);
        return "redirect:/ordering";
    }
    @GetMapping("/ordering")
    public String viewPost( Model model) {
        model.addAttribute("templates",templates);
        return "ordering";
    }
    @RequestMapping(method = RequestMethod.POST,value = "/ordering",params = "show=Выбрать")
    public String showCheckout(@RequestParam(value = "selectVal")String selected , RedirectAttributes attributes,@RequestParam(value = "car")String carId){
        attributes.addFlashAttribute("order",new Order());
        attributes.addFlashAttribute("id",carId);

        if(selected.equals("notemplate")){
            attributes.addFlashAttribute("show",true);
            attributes.addFlashAttribute("curtemp",null);
            return "redirect:/ordering";
        }else{
            attributes.addFlashAttribute("show",true);
            attributes.addFlashAttribute("curtemp",templates.stream().filter(x ->x.name.equals(selected)).toList().get(0));
            return "redirect:/ordering";
        }
    }
    @RequestMapping(method = RequestMethod.POST,value = "ordering",params = "sendnotemplate=Заказать")
    public String placeOrder(RedirectAttributes attributes,@ModelAttribute Order order,@RequestParam(value = "car")String carId){
        if(!hangarService.hasEmpty()){
            attributes.addFlashAttribute("trouble",true);
            return "redirect:/ordering";
        }
//        HangarPosition newpos=new HangarPosition();
//        System.out.println(order.getDescription());
//        System.out.println(carId+ "dsfsdgsdafs");
//        System.out.println(Long.getLong(carId));
//        System.out.println(carService.findFirstById(Long.getLong(carId)));
        Order reforder=orderService.addOrder(order,
                (Client) utilService.getfromSession("user"),
                carService.findFirstById(Long.parseLong(carId)));
//        newpos.setOrdernumber(reforder.getID());
//        newpos.setHangar_place(hangarPositionRepo.findFirstByOrdernumberIsNull().getHangar_place());
//        hangarPositionRepo.save(newpos);
        return "redirect:/profile";
    }
}
