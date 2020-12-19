package com.bilgeadam.tutorial.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommonController {

    @GetMapping("/greeting")
    public String welcome(@RequestParam(defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "Greetings";
    }

}
