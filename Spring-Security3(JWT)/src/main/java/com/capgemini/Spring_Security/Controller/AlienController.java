package com.capgemini.Spring_Security.Controller;

import com.capgemini.Spring_Security.Entity.Alien;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
public class AlienController {
    List<Alien> list = new ArrayList<>(List.of(new Alien(1,"sampath","vskp"),new Alien(2,"akhil","HP"),new Alien(3,"Roa","Telangana")));

    @GetMapping("/get-aliens")
    public List<Alien> getAliens(){
        return list;
    }

    @PostMapping("/add-aliens")
    public void addAlien(@RequestBody Alien alien){
        list.add(alien);
        System.out.println(list);
    }

    @GetMapping("/get-info")
    public String getInfo(HttpServletRequest request){
        return "This is the session id generated "+request.getSession().getId();
    }

    @GetMapping("/get-moreinfo")
    public String getMoreInfo(HttpServletRequest request){
        return "This id is same for all api for particular time ->  "+request.getSession().getId();
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
