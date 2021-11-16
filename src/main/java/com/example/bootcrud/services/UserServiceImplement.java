package com.example.bootcrud.services;

import com.example.bootcrud.dto.UserDto;
import com.example.bootcrud.entities.Role;
import com.example.bootcrud.entities.User;
import com.example.bootcrud.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.ValidationException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class UserServiceImplement implements UserService {

    private UserRepo userRepo;
    private UserConverter converter;

    @Autowired
    public void setConverter(UserConverter converter) {
        this.converter = converter;
    }

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User findByFirstname(String firstname) {
        return userRepo.findByFirstname(firstname);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByFirstname(s);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", s));
        }


        return new org.springframework.security.core.userdetails.User(user.getFirstname(), user.getPassword()
                    , mapRoleToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r-> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }


    @Override
    public UserDto saveUser(UserDto userDto) throws ValidationException{
        validateUserDto(userDto);
        User savedUser = userRepo.save(converter.fromUserDtotoUser(userDto));
        return converter.fromUserToUserDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepo.findByFirstname(username);
        if (user != null) {
            return converter.fromUserToUserDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream()
                .map(converter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    private void validateUserDto(UserDto userDto) throws ValidationException{
        if(isNull(userDto)) {
            throw new ValidationException("Object user is null!");
        }

        if (isNull(userDto.getFirstname()) || userDto.getFirstname().isEmpty()) {

            throw new ValidationException("Name is empty!");
        }

        if(isNull(userDto.getPassword()) || userDto.getPassword().isEmpty()) {
            throw new ValidationException("Password is empty!");
        }

        if(isNull(userDto.getEmail()) || userDto.getEmail().isEmpty()) {
            throw new ValidationException("Email is empty!");
        }

        if(isNull(userDto.getRoles()) || userDto.getRoles().isEmpty()) {
            throw new ValidationException("Roles is empty!");
        }
    }
}
