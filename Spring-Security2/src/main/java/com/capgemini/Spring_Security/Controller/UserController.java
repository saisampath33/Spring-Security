package com.capgemini.Spring_Security.Controller;

import com.capgemini.Spring_Security.Entity.Users;
import com.capgemini.Spring_Security.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
    @PostMapping("/add-user")
    public Users registerNewUser(@RequestBody Users user){
        String encoded = bcrypt.encode(user.getPassword());
        user.setPassword(encoded);
        return service.register(user);
    }


}
