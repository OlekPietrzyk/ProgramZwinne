package com.project.model;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="STUDENT")
@NamedQueries({
        @NamedQuery(name = "Student.getAll", query = "SELECT s FROM Student s"),
        @NamedQuery(name = "Student.getStudentById", query = "SELECT s FROM Student s WHERE s.studentId =:id"),
        @NamedQuery(name = "Student.getStudentByIds", query = "SELECT s FROM Student s WHERE s.studentId IN :ids"),
})
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id")
    private Integer studentId;

    @Column (name = "nr_indeksu", nullable = false, length = 20, unique = true)
    private String nrIndeksu;

    @Column (nullable = false)
    private boolean stacjonarny;

    @ManyToMany(mappedBy = "studenci")
    private Set<Projekt> projekty;

    @OneToOne
    private Person person;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getNrIndeksu() {
        return nrIndeksu;
    }

    public void setNrIndeksu(String nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public boolean isStacjonarny() {
        return stacjonarny;
    }

    public void setStacjonarny(boolean stacjonarny) {
        this.stacjonarny = stacjonarny;
    }

    public Set<Projekt> getProjekty() {
        return projekty;
    }

    public void setProjekty(Set<Projekt> projekty) {
        this.projekty = projekty;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
