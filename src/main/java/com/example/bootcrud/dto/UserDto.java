package com.example.bootcrud.dto;

import com.example.bootcrud.entities.Role;
import lombok.Builder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
public class UserDto {

    private Long id;
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message ="Имя должно быть в диапазоне 2 - 30 символов")
    private String firstname;
    private String lastname;
//    @NotEmpty(message = "Пароль не может быть пустым")
//    @Size(min = 4, message = "Длина пароля не менее 4 символов")
    private String password;
    @NotEmpty(message = "Email не может быть пустым")
    @Email(message = "Не валидный Email")
    private String email;
    private int age;
    @NotEmpty(message = "Должна быть выбрана хотя бы одна роль")
    private Set<Role> roles;

    public UserDto(){}

    public UserDto(final Long id, final String firstname, final String lastname, final String password, final String email, final int age, final Set<Role> roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.age = age;
        this.roles = roles;
    }

    public static UserDto.UserDtoBuilder builder() {
        return new UserDto.UserDtoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAge() {
        return this.age;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean hasAuthorities(String role){
        Set<Role> roles = getRoles();
        return roles.stream().anyMatch(a -> a.getName().equals(role));
    }
}
