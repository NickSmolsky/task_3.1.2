package com.nicksmol.Task_311.controller;

import com.nicksmol.Task_311.model.User;
import com.nicksmol.Task_311.service.RoleService;
import com.nicksmol.Task_311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping("users")
    public String createUser(@ModelAttribute("user") User user, @RequestParam("roleList") String[] role) {
        user.setRoles(roleService.getRoleSet(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("users/{id}")
    public String selectUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.selectById(id));
        return "userById";
    }

    @GetMapping("users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.selectById(id));
        return "edit";
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
