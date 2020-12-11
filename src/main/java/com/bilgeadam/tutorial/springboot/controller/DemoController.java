package com.bilgeadam.tutorial.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DemoController {

    @GetMapping("/")
    public String Welcome(){
        return "Welcome to SpringBoot tutorial at: " + LocalDateTime.now();
    }

}
