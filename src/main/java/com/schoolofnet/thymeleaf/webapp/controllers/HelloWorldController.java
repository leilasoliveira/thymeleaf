package com.schoolofnet.thymeleaf.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView("/hello");
        mv.addObject("name", "Leila Farias");
        return mv;
    }

    @GetMapping("/new")
    public String newPath(Model model) {
        List<String> lista = new ArrayList<>();
        lista.add("Leila");
        lista.add("Orlando");
        lista.add("Vinícius");

        model.addAttribute("name", "Leila Farias");
        model.addAttribute("lista", lista);
        return "new";
    }

}
