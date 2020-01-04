package com.selenium.controller;

import com.selenium.simulation.Selenium;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/call", method= RequestMethod.POST)
    @ResponseBody
    public String apiCall() {
        Selenium selenium = new Selenium();
        return selenium.call();
    }
}
