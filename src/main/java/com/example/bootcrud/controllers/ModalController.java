package com.example.bootcrud.controllers;

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


    @GetMapping("delete")
    public String modalDelete(@RequestParam("email") String email, Model model) {
        model.addAttribute("deleteUser", userService.findByEmail(email));
        return "delete";
    }

//    @GetMapping("modal2")
//    public String modal2(@RequestParam("name") String name, Model model) {
//        model.addAttribute("name", name);
//        return "modal2";
//    }
}
