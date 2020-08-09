package com.dotin.dotintasktwo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class MainController {


    @GetMapping("/")
    public ModelAndView homePage() {


        return new ModelAndView("index.jsp");

    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView("redirect:index.jsp");
        httpSession.invalidate();
        return modelAndView;
    }
}