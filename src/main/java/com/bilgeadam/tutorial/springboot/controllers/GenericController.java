package com.bilgeadam.tutorial.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericController {
    /*
    //@RequestMapping(method = RequestMethod.GET, path = "/")
    @GetMapping("/")
    public String Welcome(){
        return "Welcome to SpringBoot tutorials";
    }
    */
    @GetMapping(path = "/welcome/{name}")
    public String Welcome(@PathVariable(value = "name", required = false) String name){
        return "Welcome to SpringBoot tutorials, " + name;
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
*/
    @GetMapping("/")
    public String Welcome(@RequestParam String firstName, @RequestParam String lastName){
        return "Welcome to SpringBoot tutorials, " + firstName + " " + lastName;
    }

}
