package com.example.bootcrud.repositories;

import com.example.bootcrud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByFirstname(String name);
    User findByEmail(String email);
    //Optional<User> findById(Long id);

   // @Modifying
   // @Query("update User u set u.firstname = ?1, u.lastname = ?2, u.password = ?3, u.email = ?4 where u.id = ?5")
   // void setUserInfoById(String firstname, String lastname, String password, String email,  Long userId);


}
