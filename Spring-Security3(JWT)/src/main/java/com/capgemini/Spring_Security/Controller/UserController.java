package com.capgemini.Spring_Security.Controller;

import com.capgemini.Spring_Security.Entity.Users;
import com.capgemini.Spring_Security.Service.JWTTokenService;
import com.capgemini.Spring_Security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTTokenService jwtService;

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
    @PostMapping("/add-user")
    public Users registerNewUser(@RequestBody Users user){
        String encoded = bcrypt.encode(user.getPassword());
        user.setPassword(encoded);
        return service.register(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user){
       Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));

       if(authentication.isAuthenticated()){
           String jwt =  jwtService.generateToken(user.getName());
           System.out.println(jwt);
           return jwt;
       }else
           return "Invalid credentials";
       
    }

}
