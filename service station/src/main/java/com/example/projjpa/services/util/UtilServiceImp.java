package com.example.projjpa.services.util;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Data
@Service
public class UtilServiceImp implements UtilService{
    private HttpSession getSession(boolean isNew){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(isNew);
        return session;
    }

    @Override
    public void switchAttr(RedirectAttributes attr, Model md, String st) {
        if(md.getAttribute("show")==null){
            attr.addFlashAttribute("show",true) ;
            System.out.println("ada");
        }else
        if((boolean)md.getAttribute("show")){
            attr.addFlashAttribute("show",false) ;

        }else{
            attr.addFlashAttribute("show",true) ;
        }
    }

    @Override
    public void addtoSession(String attrName, Object o) {
        HttpSession session=this.getSession(true);
        session.setAttribute(attrName,o);
    }

    @Override
    public void removeFromSession(String attrName) {
        HttpSession session=this.getSession(false);
        session.removeAttribute(attrName);
    }

    @Override
    public Object getfromSession(String attrName) {
        HttpSession session=this.getSession(false);
        if(session==null){
            return null;
        }
        return session.getAttribute(attrName);
    }

    @Override
    public String logout() {
        HttpSession session=this.getSession(false);

        if(session!=null){
            session.invalidate();
        }
        return "redirect:/mainpage";
    }
}
