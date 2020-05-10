package com.project.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "project")
@NamedQueries({
        @NamedQuery(name = "Projekt.getByName", query = "SELECT p FROM Projekt p WHERE p.nazwa =:name"),
        @NamedQuery(name = "Projekt.getAll", query = "SELECT p FROM Projekt p"),
        @NamedQuery(name = "Projekt.getProjektById", query = "SELECT p FROM Projekt p WHERE p.projektId =:id"),
})
public class Projekt {

    @Id
    @GeneratedValue
    @Column(name="projekt_id")
    private Integer projektId;

    @Column(nullable = false, length = 50)
    private String nazwa;

    @Column (name = "opis" ,nullable = false, length = 1000)
    private String opis;

    @CreationTimestamp
    @Column(name = "dataczas_utworzenia", nullable = false, updatable = false)
    private Date dataczasUtworzenia;

    @Column (name = "data_oddania", nullable = false)
    private Date dataOddania;

    @UpdateTimestamp
    @Column(name = "dataczas_modyfikacji", nullable = false)
    private Date dataCzasModyfikacji;

    @OneToMany(mappedBy = "projekt")
    private List<Zadanie> zadania;

    @ManyToMany
    @JoinTable(name = "projekt_student",
            joinColumns = {@JoinColumn(name="projekt_id")},
            inverseJoinColumns = {@JoinColumn(name="student_id")})
    private Set<Student> studenci;


    public Integer getProjektId() {
        return projektId;
    }

    public void setProjektId(Integer projektId) {
        this.projektId = projektId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getDataczasUtworzenia() {
        return dataczasUtworzenia;
    }

    public List<Zadanie> getZadania() {
        return zadania;
    }

    public void setZadania(List<Zadanie> zadania) {
        this.zadania = zadania;
    }

    public Set<Student> getStudenci() {
        return studenci;
    }

    public void setStudenci(Set<Student> studenci) {
        this.studenci = studenci;
    }

    public void setDataczasUtworzenia(Date dataczasUtworzenia) {
        this.dataczasUtworzenia = dataczasUtworzenia;
    }

    public Date getDataOddania() {
        return dataOddania;
    }

    public void setDataOddania(Date dataOddania) {
        this.dataOddania = dataOddania;
    }

    public Date getDataCzasModyfikacji() {
        return dataCzasModyfikacji;
    }

    public void setDataCzasModyfikacji(Date dataCzasModyfikacji) {
        this.dataCzasModyfikacji = dataCzasModyfikacji;
    }
}


