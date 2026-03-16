package com.capgemini.Spring_Security.Service;

import com.capgemini.Spring_Security.Entity.Users;
import com.capgemini.Spring_Security.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users users = repo.findByName(userName);
        if(users==null){
            throw new UsernameNotFoundException("User not Found");
        }

        return new UserPrinciple(users);
    }
}
