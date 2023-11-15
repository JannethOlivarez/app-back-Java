package com.appbackJava.springSecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/demo")
    public String showDemo(){
        return "Hola Janneth desde demo";
    }
}
