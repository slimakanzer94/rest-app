package com.slimakanzer.restapp.controller;

import com.slimakanzer.restapp.entities.Role;
import com.slimakanzer.restapp.entities.User;
import com.slimakanzer.restapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("registration")
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getRegistration() {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Model model) {
        User userFromDb = userService.getUserByUserName(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("msg", "This user already exist !");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/login";
    }
}

