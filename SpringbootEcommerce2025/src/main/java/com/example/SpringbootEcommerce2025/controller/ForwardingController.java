package com.example.SpringbootEcommerce2025.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardingController {
    @RequestMapping(value="/{[path:[^\\.]*}")
    public String redirect(){
        //Forward to index.html so Angular can handle the route
        return "forward:/index.html";
        //this tells springboot if the path doesn't contain ., assume it's a route and forward it to index.html
    }
}
