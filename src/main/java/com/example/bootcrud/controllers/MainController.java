package com.example.bootcrud.controllers;

import com.example.bootcrud.dto.UserDto;
import com.example.bootcrud.entities.Role;
import com.example.bootcrud.services.RoleService;
import com.example.bootcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.security.Principal;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }


    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @GetMapping("/admin")
    public String admin(Principal principal) {
        return "admin";
    }
    @RequestMapping(value = "admin:all", method = RequestMethod.GET)
    public @ResponseBody List<UserDto> allList() {
        return userService.findAll();
    }

    @RequestMapping(value = "admin:roles", method = RequestMethod.GET)
    public @ResponseBody Set<Role> roleSet() {
        return roleService.index();
    }


    @PatchMapping( "/admin:UserDto")
    public @ResponseBody UserDto updateAdmin(@RequestBody UserDto userDto) throws ValidationException {
        userService.updateUser(userDto);
        return userDto;
    }


    @PostMapping("admin:UserDto")
    public @ResponseBody UserDto save(@RequestBody UserDto userDto) throws ValidationException {
        userService.saveUser(userDto);
        userDto.setId(userService.findByEmail(userDto.getEmail()).getId());
        return userDto;
    }


    @DeleteMapping( "/admin:id={id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return userService.deleteUser(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    //  показ старницы профиля для юзера
    @GetMapping("/user")
    public String pageForReadProfile(Principal principal, Model model) {
        UserDto user = userService.findByEmail(principal.getName());
        model.addAttribute("roles", roleService.index());
        model.addAttribute("user", user);
        return "user";
    }

}
