package com.example.bootcrud.controllers;

import com.example.bootcrud.dto.UserDto;
import com.example.bootcrud.entities.Role;
import com.example.bootcrud.entities.User;
import com.example.bootcrud.services.RoleServce;
import com.example.bootcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.security.Principal;
import java.util.Set;

@Controller
public class MainController {

    private final UserService userService;
    private final RoleServce roleServce;

    @Autowired
    public MainController(UserService userService, RoleServce roleServce) {
        this.userService = userService;
        this.roleServce = roleServce;
    }

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

    @RequestMapping(value = "/admin/update/{id}", method = {RequestMethod.POST, RequestMethod.PATCH})
    public String updateAdmin(@ModelAttribute("user") UserDto userDto, @PathVariable("id") Long id
            ,@RequestParam("roles") String... roles) throws ValidationException {
        userDto.setId(id);
        userDto.setRoles(roleServce.getRoleSet(roles));
        userService.updateUser(userDto);
        return "redirect:/authorized";
    }
//
//    @RequestMapping(value="login")
//    public String loginPage() {
//        return "login";
//    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleServce.index());
        return "admin";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model, @ModelAttribute("user") UserDto userDto) {
        model.addAttribute("roles", roleServce.index());
        return "/create";
    }

    @PostMapping("/admin/")
    public String create(@ModelAttribute("user") UserDto userDto, @RequestParam("roles") String... roles) throws ValidationException {
        userDto.setRoles(roleServce.getRoleSet(roles));
        userService.saveUser(userDto);
        return "redirect:/admin";
    }


    @GetMapping("/authorized")
    public String pageForAuthenticatedUsers(Principal principal) {
        UserDto user = userService.findByUsername(principal.getName());
        if(user.hasAuthorities("ROLE_ADMIN")) {
            return "redirect:admin";
        } else {
            return "redirect:user";
        }
    }

    @GetMapping("/admin/edit/{username}")
    public String edit(Model model, @PathVariable("username") String username) {
        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("roles", roleServce.index());
        return "/edit";
    }

//    @PatchMapping("/admin/update/{username}")
//    public String updateAdmin(@ModelAttribute("user") UserDto userDto, @PathVariable("username") String username
//            ,@RequestParam("roles") String... roles) throws ValidationException {
//        userDto.setRoles(roleServce.getRoleSet(roles));
//        userService.saveUser(userDto);
//        return "redirect:/authorized";
//    }



    @GetMapping("/admin/delete/{username}")
    public String confirmDelete(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("roles", roleServce.index());
        return "/delete";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.POST, RequestMethod.DELETE})
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }

    @GetMapping("/admin/user/{username}")
    public String showUser(Model model, Principal principal, @PathVariable("username") String name) {
        model.addAttribute("user", userService.findByUsername(name));
        model.addAttribute("roles", roleServce.index());
        return "/user";
    }

    @GetMapping("/user")
    public String pageForReadProfile(Principal principal, Model model) {
        UserDto user = userService.findByUsername(principal.getName());
        model.addAttribute("roles", roleServce.index());
        model.addAttribute("user", user);
        return "/user";
    }

}
