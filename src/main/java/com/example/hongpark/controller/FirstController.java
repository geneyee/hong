package com.example.hongpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/")
    public String greatings(Model model){

        model.addAttribute("username", "홍팍");

        return "greatings";
    }

    @GetMapping("/bye")
    public String bye(Model model){
        model.addAttribute("username", "바이바이");
        return "goodbye";
    }
}
