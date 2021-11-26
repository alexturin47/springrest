package com.example.bootcrud.controllers;

import com.example.bootcrud.dto.UserDto;
import com.example.bootcrud.services.RoleService;
import com.example.bootcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("modals")
public class ModalController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;



    @GetMapping("delete")
    public @ResponseBody UserDto modalDelete(@RequestParam("email") String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("edit")
    public @ResponseBody UserDto modalEdit(@RequestParam("email") String email) {

//            model.addAttribute("editUser", userService.findByEmail(email));
//            model.addAttribute("roles", roleService.index());
//        return "edit";
        return userService.findByEmail(email);
    }
}
