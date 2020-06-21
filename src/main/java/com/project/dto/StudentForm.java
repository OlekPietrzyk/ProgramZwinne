package com.project.dto;

import com.project.model.Student;

public class StudentForm {

    Student student;

    boolean active;

    Integer id;

    public StudentForm() {

    }
    public StudentForm(Student student, boolean active, Integer id) {
        this.active = active;
        this.student = student;
        this.id = id;
    }


    public Student getStudent() {
        return student;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
