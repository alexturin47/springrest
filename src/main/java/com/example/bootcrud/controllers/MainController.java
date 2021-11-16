package com.example.bootcrud.controllers;

import com.example.bootcrud.dto.UserDto;
import com.example.bootcrud.entities.User;
import com.example.bootcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    //
//    @GetMapping("/")
//    public String homePage() {
//        return "/index";
//    }

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, @ModelAttribute("user") UserDto user) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", user.getRoles());
        return "admin";
    }

    @GetMapping("/authorized")
    public String pageForAuthenticatedUsers(Principal principal) {
        UserDto userDto = userService.findByUsername(principal.getName());
        if(userDto.getRoles().contains("ROLE_ADMIN")) {
            return "redirect:admin";
        } else {
            return "redirect:user";
        }
    }

}
