package com.capgemini.Spring_Security.Service;

import com.capgemini.Spring_Security.Entity.Users;
import com.capgemini.Spring_Security.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public Users register(Users user){
        return repo.save(user);
    }
}
