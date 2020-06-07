package com.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;


@Controller
public class LoginController {

    @RequestMapping("/login")
    public String index(Model model, @RequestParam(value = "error", required = false) Boolean error, @RequestParam(value = "sessionerror", required = false) Boolean sessionerror) {

        if (error!= null && error){
            model.addAttribute("error",true);
            model.addAttribute("errorMessage", "Błędnie wprowadzony adres e-mail lub hasło!");
        }
        if (sessionerror!= null && sessionerror){
            model.addAttribute("error",true);
            model.addAttribute("errorMessage", "Zostałeś wylogowany, na twoje konto zalogował się ktoś inny!");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);

        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}
