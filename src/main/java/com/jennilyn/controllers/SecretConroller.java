package com.jennilyn.controllers;

import com.jennilyn.models.Secret;
import com.jennilyn.models.User;
import com.jennilyn.repositories.SecretRepository;
import com.jennilyn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class SecretConroller {

    @Autowired
    SecretRepository secretRepo;

    @Autowired
    UserRepository userRepo;

    @RequestMapping("/secret/createSecret")
    public String createSecretForm(Model model){
        model.addAttribute("newSecret", new Secret());
        return "createSecret";
    }

    @RequestMapping(value = "/secret/createSecret", method = RequestMethod.POST)
    public String createSecretForm(@RequestParam("body") String body,
                                   Principal principal){
        String username = principal.getName();
        User user = userRepo.findByUsername(username);

        Secret newSecret = new Secret();
        newSecret.setBody(body);
        newSecret.setUser(user);

        secretRepo.save(newSecret);
        return "redirect:/";
    }

    @RequestMapping("/secret/mySecrets")
    public String mySecrets(Principal principal, Model model){
        User user = userRepo.findByUsername(principal.getName());
        Iterable<Secret> secrets = secretRepo.findAllByUser(user);

        model.addAttribute("user", user);
        model.addAttribute("secrets", secrets);
        return "mySecrets";
    }

}
