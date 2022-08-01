package com.example.hospital2022.controller;


import com.example.hospital2022.model.User;
import com.example.hospital2022.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "user/login";
    }


    @GetMapping("/registration")
    public String registration(Principal principal, Model model){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "user/registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if(!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с login: " + user.getLogin() + "уже существует");
            return "user/registration";
        }
        return "redirect:/login";
    }
}
