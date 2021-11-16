package com.example.bootcrud.repositories;

import com.example.bootcrud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByFirstname(String name);
    User findByEmail(String email);

}
