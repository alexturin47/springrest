package com.example.bootcrud.services;

import com.example.bootcrud.dto.UserDto;
import com.example.bootcrud.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface UserService extends UserDetailsService {

    void saveUser(UserDto userDto) throws ValidationException;
    void deleteUser(Long id);
    UserDto findByUsername(String username);
    //UserDto findById(Long id);
    List<UserDto> findAll();
    void updateUser(UserDto userDto) throws ValidationException;

//    User saveUser(User user) throws ValidationException;
//    void deleteUser(Long id);
//    User findByUsername(String username);
//    List<User> findAll();
}
