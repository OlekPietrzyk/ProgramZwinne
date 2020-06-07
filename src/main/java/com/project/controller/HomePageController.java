package com.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
public class HomePageController {

    @RequestMapping("/")
    public String index(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);

        return "index";
    }
}
