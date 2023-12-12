package com.example.projjpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Config {
    private List<OrderTemplate> templates=List.of(new OrderTemplate("Осмотр трансмиссии",true,"EXP002"),new OrderTemplate("Мытье малого авто",false,"WASHING_SMALL"));
    @Bean
    List<OrderTemplate> templates(){
        return templates;
    }
}
