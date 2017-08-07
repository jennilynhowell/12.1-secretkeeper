package com.jennilyn.controllers;

import com.jennilyn.models.Secret;
import com.jennilyn.repositories.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Autowired
    SecretRepository secretRepo;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Secret> secrets = secretRepo.findAll();
        model.addAttribute("secrets", secrets);
        model.addAttribute("newSecret", new Secret());
        return "index";
    }
}
