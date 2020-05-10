package com.project.controller;

import com.project.dao.ProjectDao;
import com.project.model.Projekt;
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
@RequestMapping({"/addProject","/addProject/{id}"})
public class ProjectController {

    @Autowired
    private ProjectDao projectDao;

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @ModelAttribute("project")
    public Projekt createProjectForm(@PathVariable("id") Optional<String> id) {
        if (id.isPresent()) {
           return (Projekt) projectDao.findById(Integer.parseInt(id.get()));
        } else {
            return new Projekt();
        }
    }

    @GetMapping
    public String showProjectForm(Model model) {
        return "addProject";
    }

    @PostMapping
    public String handleProjectForm(Model model, @ModelAttribute("project") @Valid Projekt projekt,
                                         BindingResult result){

        if (result.hasErrors()){
            return "addProject";
        } else {

            if (projekt.getProjektId()==null) {
                projectDao.persist(projekt);
                logger.info("Tworzenie nowego obiektu projekt o id: ");
                return "redirect:/projectView/?create=true";

            } else {
                projekt.setDataczasUtworzenia(projekt.getDataOddania());
                projekt.setDataCzasModyfikacji(projekt.getDataOddania());
                projectDao.merge(projekt);
                logger.info("Edycja obiektu projekt o id: " + projekt.getProjektId());
                return "redirect:/projectView/?modify=true";

            }

        }
    }
}
