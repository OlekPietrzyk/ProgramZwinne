package com.project.controller;

import com.project.dao.ProjectDao;
import com.project.model.Projekt;
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
public class ProjektViewController {

    @Autowired
    private ProjectDao projectDao;

    @RequestMapping("/projectView")
    public ModelAndView projectView(){

        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        modelAndView.addObject("user", role);

        modelAndView.addObject("projects", projectDao.findAll());
        modelAndView.setViewName("projectView");
        return modelAndView;
    }

    @RequestMapping("/lecturer/deleteProject/{id}")
    public String projectView(Model model, @PathVariable("id") Optional<String> id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);

        Projekt byId = (Projekt) projectDao.findById(Integer.parseInt(id.get()));
        projectDao.remove(byId);

        return "redirect:/projectView/?remove=true";
    }


}
