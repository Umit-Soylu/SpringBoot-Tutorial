package com.bilgeadam.tutorial.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authentication")
public class SecurityController {

    @GetMapping("/login")
    public String loginForm(){
        return "/authentication/login";
    }

    @GetMapping("/logout")
    public String logoutForm(){
        return "/authentication/logout";
    }
}
