package com.project.controller;

import com.project.model.Person;
import com.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class PersonViewController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/lecturer/personView")
    public ModelAndView projectView(){

        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        modelAndView.addObject("user", role);

        modelAndView.addObject("persons", personService.findAllByActive(false));
        modelAndView.setViewName("personView");
        return modelAndView;
    }

    @RequestMapping("/lecturer/acceptPerson/{id}")
    public String projectView(Model model, @PathVariable("id") Optional<String> id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);

        Person byId = personService.findById(Integer.parseInt(id.get()));
        byId.setActive(true);

        personService.update(byId);

        return "redirect:/lecturer/personView/?accept=true";
    }


}
