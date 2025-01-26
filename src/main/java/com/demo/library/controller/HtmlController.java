package com.demo.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
    @GetMapping("/books")
    public String books() {
        return "books";
    }
    @GetMapping("/categories")
    public String categories() {
        return "category";
    }

    @GetMapping("/checkouts")
    public String checkout() {
        return "checkout";
    }

    @GetMapping("/profile")
    public String users() {
        return "users";
    }
}
