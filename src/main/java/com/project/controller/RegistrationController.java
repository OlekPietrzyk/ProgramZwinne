package com.project.controller;

import com.project.dto.RegistrationForm;
import com.project.model.Lecturer;
import com.project.model.Person;
import com.project.model.Student;
import com.project.service.LecturerService;
import com.project.service.PersonService;
import com.project.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private PersonService personService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @ModelAttribute("registrationForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", role);
        return "registration";
    }

    @PostMapping
    public String handleRegistrationForm(Model model, @ModelAttribute("registrationForm") @Valid RegistrationForm registrationForm,
                                       BindingResult result){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String roles= authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        model.addAttribute("user", roles);

        if (result.hasErrors()){
            return "registration";
        } else {

            Person person = new Person();
            person.setRole(registrationForm.getRole());
            person.setActive(false);
            person.setEmail(registrationForm.getEmail().trim());
            person.setPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()));
            person.setImie(registrationForm.getName());
            person.setNazwisko(registrationForm.getSurname());

            personService.save(person);

            if (registrationForm.getRole().equals("LECTURER")) {
                Lecturer lecturer = new Lecturer();
                lecturer.setNrPracownika(registrationForm.getNr());
                lecturer.setPerson(person);

                lecturerService.save(lecturer);

            } else {
                Student student = new Student();
                student.setNrIndeksu(registrationForm.getNr());
                student.setPerson(person);
                student.setStacjonarny(registrationForm.getType().equals("true"));

                studentService.save(student);
            }


            logger.info("Poprawnie zarejestrował się użytkownik o adresie email: {}", registrationForm.getEmail());
            return "redirect:/?registration=true";
        }
    }
}
