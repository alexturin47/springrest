package com.example.bootcrud.dto;

import com.example.bootcrud.entities.Role;
import lombok.Data;
import lombok.Builder;

import java.util.Set;

@Data
@Builder
public class UserDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private int age;
    private Set<Role> roles;
}
