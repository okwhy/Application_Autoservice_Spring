package com.example.projjpa.config;

import java.util.ArrayList;
import java.util.List;

public class OrderTemplate {
    public String name;
    private List<String> CodesofJobs=new ArrayList<>();
    private boolean takesSpace;
    public OrderTemplate(String name, boolean takesSpace, String... codes) {
        this.name=name;
        this.takesSpace=takesSpace;
        for(String a:codes){
            CodesofJobs.add(a);
        }
    }

    public List<String> getCodesofJobs() {
        return CodesofJobs;
    }
}
