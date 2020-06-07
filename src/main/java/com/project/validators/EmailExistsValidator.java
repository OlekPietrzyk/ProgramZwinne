package com.project.validators;

import com.project.model.Person;
import com.project.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistsValidator implements ConstraintValidator<EmailExists, String> {

    @Autowired
    private PersonService personService;

    public void initialize(EmailExists constraintAnnotation) {

    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Person person = personService.findAllByEmail(value);
        if (person==null){
            return true;
        }

        return email.equals(person.getEmail());
    }
}