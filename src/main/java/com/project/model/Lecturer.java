package com.project.model;

import javax.persistence.*;

@Entity
@Table(name="LECTURER")
@NamedQueries({
        @NamedQuery(name = "Lecturer.getAll", query = "SELECT l FROM Lecturer l"),
        @NamedQuery(name = "Lecturer.getLecturerById", query = "SELECT l FROM Lecturer l WHERE l.lecturerId =:id"),
})
public class Lecturer {

    @Id
    @GeneratedValue
    @Column(name = "lecturer_id")
    private Integer lecturerId;

    @Column (name = "nr_pracownika", nullable = false, length = 20, unique = true)
    private String nrPracownika;

    @OneToOne
    private Person person;


    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getNrPracownika() {
        return nrPracownika;
    }

    public void setNrPracownika(String nrPracownika) {
        this.nrPracownika = nrPracownika;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}