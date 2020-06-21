package com.project.controller;

import com.project.dao.ProjectDao;
import com.project.dao.StudentDao;
import com.project.dto.StudentForm;
import com.project.dto.StudentWrapper;
import com.project.model.Projekt;
import com.project.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/lecturer/viewStudent/{id}")
public class StudentViewController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ProjectDao projectDao;

    @GetMapping
    public String taskView(Model model, @PathVariable("id") Integer id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);
        model.addAttribute("id", id);

        Projekt byId = (Projekt) projectDao.findById(id);
        Set<Student> studenci = byId.getStudenci();
        List<StudentForm> studentForms = new ArrayList<>();

        for (Student s : studenci) {
            StudentForm studentForm = new StudentForm(s, true, s.getStudentId());
            studentForms.add(studentForm);
        }

        List<Student> all=studentDao.findAll();

        for (Student s : all) {
           if (!studenci.contains(s)) {
               StudentForm studentForm = new StudentForm(s, false, s.getStudentId());
               studentForms.add(studentForm);
           }
        }

        StudentWrapper studentWrapper = new StudentWrapper();
        studentWrapper.setStudentFormList(studentForms);

        model.addAttribute("studentWrapper", studentWrapper);
        return "studentView";
    }


    @PostMapping
    public String handleTaskView(Model model, @PathVariable("id") Integer id, @ModelAttribute("studentWrapper") @Valid StudentWrapper studentWrapper) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);


        List<Integer> ids = studentWrapper.getStudentFormList().stream().filter(a->a.isActive()).map(a -> a.getId()).collect(Collectors.toList());
        Projekt projekt =(Projekt) projectDao.findById(id);

        List<Student> byIds = studentDao.findByIds(ids);

        Set<Student> result = byIds.stream().collect(Collectors.toSet());
        projekt.setStudenci(result);

        projectDao.merge(projekt);

        return "redirect:/projectView/?saveUser=true";
    }


}
