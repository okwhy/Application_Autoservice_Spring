package com.example.projjpa.services.util;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface UtilService {
    void switchAttr(RedirectAttributes attr, Model md,String st);
    void addtoSession(String attrName,Object o);
    void removeFromSession(String attrName);
    Object getfromSession(String attrName);
    String logout();
}
