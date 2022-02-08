package com.example.tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
/*

когда контроллер достаточно прост, что он не заполняет модель или процесс ввода-как
в случае с вашим HomeController-есть еще один способ, которым вы можете определить контроллер.
 */

}
