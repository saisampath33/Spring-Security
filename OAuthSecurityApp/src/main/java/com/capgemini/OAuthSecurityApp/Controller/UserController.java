package com.capgemini.OAuthSecurityApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/get-info")
    public String getCourseInfo(){
        return "Spring Security in Java Full Stack";
    }
}
