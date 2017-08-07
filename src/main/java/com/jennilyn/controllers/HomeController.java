package com.jennilyn.controllers;

import com.jennilyn.models.Secret;
import com.jennilyn.models.User;
import com.jennilyn.repositories.SecretRepository;
import com.jennilyn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    SecretRepository secretRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/")
    public String index(Model model, Principal principal){
        String username = principal.getName();
        User user = userRepo.findByUsername(username);

        Iterable<Secret> secrets = secretRepo.findAllByUser(user);
        model.addAttribute("secrets", secrets);
        model.addAttribute("newSecret", new Secret());
        return "index";
    }
}
