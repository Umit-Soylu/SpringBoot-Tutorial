package com.bilgeadam.tutorial.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class GenericController {
    private static final String dateFormat = "dd MMM yyyy hh:ss:SSS";

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/hello")
    public String welcome(Model model, WebRequest request){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, request.getLocale());

        model.addAttribute("time", simpleDateFormat.format(new Date()));

        return "welcome";
    }

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }
}
