package com.capgemini.Spring_Security.Repo;

import com.capgemini.Spring_Security.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,String> {
    Users findByName(String userName);
}
