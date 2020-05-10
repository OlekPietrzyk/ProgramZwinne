package com.project.model;

import javax.persistence.*;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="zadanie")
@NamedQueries({
        @NamedQuery(name = "Zadanie.getByName", query = "SELECT z FROM Zadanie z WHERE z.nazwa =:name"),
        @NamedQuery(name = "Zadanie.getAll", query = "SELECT z FROM Zadanie z"),
        @NamedQuery(name = "Zadanie.getZadanieById", query = "SELECT z FROM Zadanie z WHERE z.zadanieId =:id"),
})
public class Zadanie {

    @Id
    @GeneratedValue
    @Column(name = "zadanie_id")
    private Integer zadanieId;

    @ManyToOne
    @JoinColumn(name = "projekt_id")
    private Projekt projekt;

    @Column(name = "nazwa", nullable = false, length = 50)
    private String nazwa;

    @Column (name = "kolejnosc", nullable = false)
    private Integer kolejnosc;

    @Column (name= "opis", nullable = false, length = 1000)
    private String opis;

    @CreationTimestamp
    @Column(name = "dataczas_dodania", nullable = false, updatable = false)
    private LocalDateTime dataczasDodania;

    public Integer getZadanieId() {
        return zadanieId;
    }

    public void setZadanieId(Integer zadanieId) {
        this.zadanieId = zadanieId;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getKolejnosc() {
        return kolejnosc;
    }

    public void setKolejnosc(Integer kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataczasDodania() {
        return dataczasDodania;
    }

    public void setDataczasDodania(LocalDateTime dataczasDodania) {
        this.dataczasDodania = dataczasDodania;
    }
}
