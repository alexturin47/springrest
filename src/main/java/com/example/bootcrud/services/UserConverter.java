package com.example.bootcrud.services;

import com.example.bootcrud.dto.UserDto;
import com.example.bootcrud.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User fromUserDtotoUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setRoles(userDto.getRoles());
        return user;
    }

    public UserDto fromUserToUserDto(User user) {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .email(user.getEmail())
                .age(user.getAge())
                .roles(user.getRoles())
                .build();
        return userDto;
    }
}
