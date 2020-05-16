package com.project.controller;

import com.project.dao.ProjectDao;
import com.project.dao.ZadanieDao;
import com.project.model.Projekt;
import com.project.model.Zadanie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping({"/addTask/{idProject}","/addTask/{idProject}/{id}"})
public class TaskController {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ZadanieDao zadanieDao;


    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @ModelAttribute("task")
    public Zadanie createProjectForm(@PathVariable("id") Optional<String> id) {
        if (id.isPresent()) {
            return (Zadanie) zadanieDao.findById(Integer.parseInt(id.get()));
        } else {
            return new Zadanie();
        }
    }

    @GetMapping
    public String showProjectForm(Model model, @PathVariable("idProject") Optional<String> idProject) {

        model.addAttribute("idProject", idProject.get());
        return "addTask";
    }

    @PostMapping
    public String handleProjectForm(Model model, @PathVariable("idProject") Optional<String> idProject, @ModelAttribute("task") @Valid Zadanie zadanie,
                                    BindingResult result){

        if (result.hasErrors()){
            return "addTask";
        } else {

            Projekt byId = (Projekt) projectDao.findById(Integer.valueOf(idProject.get()));
            zadanie.setProjekt(byId);

            if (zadanie.getZadanieId()==null) {
                zadanieDao.persist(zadanie);
                logger.info("Tworzenie nowego obiektu zadanie o id: ");
                return "redirect:/taskView/"+ idProject.get() +"/?create=true";
            } else {
                zadanieDao.merge(zadanie);
                logger.info("Edycja obiektu zadanie o id: " + zadanie.getZadanieId());
                return "redirect:/taskView/"+ idProject.get() +"/?modify=true";
            }
        }
    }
}
