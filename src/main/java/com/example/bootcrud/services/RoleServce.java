package com.example.bootcrud.services;

import com.example.bootcrud.entities.Role;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public interface RoleServce {

    Role findByname(String rolename);

    HashSet<Role> index();

    void save(Role role);

    Role read(int id);

    void update(Role role);

    void delete(int id);

    HashSet<Role> getRoleSet(String[] roles);
}
