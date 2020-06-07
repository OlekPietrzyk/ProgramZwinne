package com.project.dto;

import com.project.validators.EmailExists;
import com.project.validators.FieldsValueMatch;

import javax.validation.constraints.*;


@FieldsValueMatch(
        field = "password",
        fieldMatch = "rePassword",
        message = "{Registration.password.match}"
)
public class RegistrationForm {

    @NotEmpty(message="{Registration.name.notEmpty}")
    @Size(max=30, message = "{Registration.name.size}")
    @Pattern(regexp = "[A-Za-z]*", message = "{Registration.name.pattern}")
    private String name;

    @NotEmpty(message="{Registration.surname.notEmpty}")
    @Size(max=30, message = "{Registration.surname.size}")
    @Pattern(regexp = "[A-Za-z]*", message = "{Registration.surname.pattern}")
    private String surname;

    @Email(message = "{Registration.email.validate}")
    @NotEmpty(message = "{Registration.email.notEmpty}")
    @EmailExists(message = "{Registration.email.exists}")
    @Size(max = 50, message = "{Size.50}")
    private String email;

    private String role;

    @NotEmpty(message = "{Registration.nr.notEmpty}")
    @Size(max = 50, message = "{Size.20}")
    private String nr;

    @NotEmpty(message = "{Registration.type.notEmpty}")
    private String type;

    @NotEmpty(message="{Registration.password.notEmpty}")
    @Size(max=20, message = "{Registration.password.size}")
    private String password;

    @NotEmpty(message="{Registration.password.notEmpty}")
    @Size(max=20, message = "{Registration.password.size}")
    private String rePassword;

    @AssertTrue(message="{Registration.acceptRegulation.accept}")
    private boolean acceptRegulation;

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public boolean isAcceptRegulation() {
        return acceptRegulation;
    }

    public void setAcceptRegulation(boolean acceptRegulation) {
        this.acceptRegulation = acceptRegulation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
