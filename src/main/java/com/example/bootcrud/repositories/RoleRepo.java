package com.example.bootcrud.repositories;

import com.example.bootcrud.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

    Role findByname(String name);

}
