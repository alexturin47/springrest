package com.example.bootcrud.controllers;

import com.example.bootcrud.services.RoleService;
import com.example.bootcrud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("modals")
public class ModalController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping("delete")
    public String modalDelete(@RequestParam("email") String email, Model model) {
        model.addAttribute("deleteUser", userService.findByEmail(email));
        return "delete";
    }

    @GetMapping("edit")
    public String modalEdit(@RequestParam("email") String email, Model model) {
            model.addAttribute("editUser", userService.findByEmail(email));
            model.addAttribute("roles", roleService.index());
        return "edit";
    }
}
