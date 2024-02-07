package com.green.Fragment.Fragment_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Fragment2Controller {

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("name", "지선영");
        model.addAttribute("age", 24);
        return "content/list";
    }

    @GetMapping("/detail")
    public String detail(){
        return "content/detail";
    }
}
