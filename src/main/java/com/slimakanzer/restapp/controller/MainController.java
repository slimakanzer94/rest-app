package com.slimakanzer.restapp.controller;

import com.slimakanzer.restapp.entities.User;
import com.slimakanzer.restapp.service.PersonDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private PersonDtoService personDtoService;

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        Map<Object, Object> data = new HashMap<>();
        data.put("profile", user);
        data.put("persons", personDtoService.getPerson());
        model.addAttribute("frontendData", data);
        return "index";
    }
}

