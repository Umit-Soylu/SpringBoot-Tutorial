package com.bilgeadam.tutorial.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class GenericController {
    /*
    //@RequestMapping(method = RequestMethod.GET, path = "/")
    @GetMapping("/")
    public String Welcome(){
        return "Welcome to SpringBoot tutorials";
    }
    */

    @GetMapping(path = "/welcome")
    public String Welcome(@RequestParam(value = "name", defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("date", new Date());
        return "welcome";
    }

    /*
    @GetMapping(path = "/{firstName}/{lastName}")
    public String Welcome(@PathVariable(value = "firstName") String firstName,
                          @PathVariable(value = "lastName") String lastName){
        return "Welcome to SpringBoot tutorials, " + firstName + " " + lastName;
    }

    @GetMapping("/")
    public String Welcome(@RequestBody Employee employee){
        return "Welcome to SpringBoot tutorials, " + employee;
    }

    @GetMapping("/")
    public String Welcome(@RequestParam String firstName, @RequestParam String lastName){
        return "Welcome to SpringBoot tutorials, " + firstName + " " + lastName;
    }
*/
}
