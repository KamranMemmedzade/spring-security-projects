package com.example.springsecurityjdbc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "<h1>Welcome to spring security intro project</h1>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h1>User page</h1>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h1>Admin page</h1>";
    }
}
