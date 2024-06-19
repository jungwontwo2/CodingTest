package com.gonet.codingtest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
