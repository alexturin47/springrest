package com.example.bootcrud.repositories;

import com.example.bootcrud.entities.Role;
import com.example.bootcrud.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitDB {

    UserRepo userRepo;
    RoleRepo roleRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    public void setRoleRepo(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @PostConstruct
    private void saveUsers() {
        if(roleRepo.findByname("ROLE_ADMIN") == null) {
            roleRepo.save(new Role("ROLE_ADMIN"));
        }
        if(roleRepo.findByname("ROLE_USER") == null) {
            roleRepo.save(new Role("ROLE_USER"));
        }

        userRepo.deleteAll();
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByname("ROLE_USER"));
        roles.add(roleRepo.findByname("ROLE_ADMIN"));
        try {
            userRepo.save(new User("admin"
                    , "adminnov"
                    , "$2a$12$AFJsSpNrJlC04sp2qGPcYepkRMy6rs1k3hNxeTxRMj0qZJ/aUK6X2"
                    , "admin@mail.ru"
                    , 35 , roles));
        } catch (Exception e) {

        }


        roles = new HashSet<>();
        roles.add(roleRepo.findByname("ROLE_USER"));

        try {
            userRepo.save(new User("user"
                    , "userov"
                    , "$2a$12$wblAIp0iNog81E3RpCdwBuEZ6mqlSIJ/BFfUfFdf4p0y6naziXoGK"
                    , "user@mail.ru"
                    , 38 , roles));
        } catch (Exception e) {

        }


    }


}
