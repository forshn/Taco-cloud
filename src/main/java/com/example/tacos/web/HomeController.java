package com.example.tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(name = "/")
    public String home(){
        return "home";
    }
}
