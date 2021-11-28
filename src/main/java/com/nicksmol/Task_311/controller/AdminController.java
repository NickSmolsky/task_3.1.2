package com.nicksmol.Task_311.controller;

import com.nicksmol.Task_311.model.User;
import com.nicksmol.Task_311.service.RoleService;
import com.nicksmol.Task_311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAllUsers(Model model, Principal principal, @ModelAttribute("user") User user) {
        model.addAttribute("authUser", userService.findByEmail(principal.getName()));
        model.addAttribute("users", userService.getAll());
//        model.addAttribute("listRoles", roleService.getAllRoles());
        return "allUsers";
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        model.addAttribute("authUser", userService.findByEmail(principal.getName()));
        return "adminPage";
    }

    @PostMapping("users")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("roleList") String[] role) {
        user.setRoles(roleService.getRoleSet(role));
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("users/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("user") User user, @RequestParam("roleList") String[] role) {
        user.setRoles(roleService.getRoleSet(role));
        userService.update(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("users/{id}")
    public String userDelete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
