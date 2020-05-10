package com.project.controller;

import com.project.dao.ProjectDao;
import com.project.model.Projekt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class ProjektViewController {

    @Autowired
    private ProjectDao projectDao;

    @RequestMapping("/projectView")
    public ModelAndView projectView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("projects", projectDao.findAll());
        modelAndView.setViewName("projectView");
        return modelAndView;
    }

    @RequestMapping("/deleteProject/{id}")
    public String projectView(Model model, @PathVariable("id") Optional<String> id){

        Projekt byId = (Projekt) projectDao.findById(Integer.parseInt(id.get()));
        projectDao.remove(byId);

        return "redirect:/projectView/?remove=true";
    }


}
