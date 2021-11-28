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
    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{put("id", "1"); put("text", "message 1");}});
        add(new HashMap<String, String>() {{put("id", "2"); put("text", "message 2");}});
        add(new HashMap<String, String>() {{put("id", "3"); put("text", "message 3");}});
    }};

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


    // редактирование юзера
//    @GetMapping("/edit/{username}")
//    public String edit(Model model, @PathVariable("username") String username) {
//        model.addAttribute("user", userService.findByEmail(username));
//        model.addAttribute("roles", roleServce.index());
//        return "/edit";
//    }


    @PatchMapping( "/admin:UserDto")
    public @ResponseBody UserDto updateAdmin(@RequestBody UserDto userDto) throws ValidationException {
        userService.updateUser(userDto);
        return userDto;
    }



//    // созадние нового юзера
//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new UserDto());
//        model.addAttribute("roles", roleService.index());
//        return "/new";
//    }

//    @PostMapping("/new")
//    public String create(Model model, @ModelAttribute("user") UserDto userDto
//            , @RequestParam(name = "roles", required = false) String[] roles) throws ValidationException {
//
//        userDto.setRoles(roleService.getRoleSet(roles));
//        userService.saveUser(userDto);
//        return "redirect:/admin";
//    }

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
