package com.project.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *  Klasa odpowiedzialna za trzymanie hasła oraz roli utworzonego użytkownika
 *  Wykorzystywnaa jest także podczas logownaia.
 */

@Entity
@Table(name="PERSON")
@NamedQueries({
        @NamedQuery(name = "Person.getAll", query = "SELECT p FROM Person p"),
        @NamedQuery(name = "Person.getPersonByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
        @NamedQuery(name = "Person.getPersonById", query = "SELECT p FROM Person p WHERE p.id =:id"),
})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, length = 50)
    private String imie;

    @Column (nullable = false, length = 100)
    private String nazwisko;

    @NotNull(message="Pole email nie może być puste. Prosze uzupełnić pole. ")
    @Size(max=30, message = "Pole email może posiadać maksymalnie 30 znaków. ")
    @Column(length = 50)
    private String email;

    @NotNull(message="Pole hasło nie może być puste. ")
    @Column(length = 100)
    private String password;

    @Column(length = 10)
    private String role;

    private Boolean active;

    @OneToOne(mappedBy = "person")
    private Student student;

    @OneToOne(mappedBy = "person")
    private Lecturer lecturer;

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}


