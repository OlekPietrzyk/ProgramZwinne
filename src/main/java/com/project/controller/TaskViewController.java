package com.project.controller;

import com.project.dao.ZadanieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class TaskViewController {

    @Autowired
    private ZadanieDao zadanieDao;

    @RequestMapping("/taskView/{id}")
    public ModelAndView taskView(@PathVariable("id") Optional<String> id){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("tasks", zadanieDao.findByIdProject(Integer.valueOf(id.get())));
        modelAndView.addObject("idProject", id.get());

        modelAndView.setViewName("taskView");
        return modelAndView;

    }


    @RequestMapping("/deleteTask/{idProject}/{id}")
    public String projectView(Model model, @PathVariable("idProject") Optional<String> idProject, @PathVariable("id") Optional<String> id){

        Object byId = zadanieDao.findById(Integer.parseInt(id.get()));
        zadanieDao.remove(byId);

        return "redirect:/taskView/"+ idProject.get() +"?remove=true";
    }


}
