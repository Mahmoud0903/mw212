package com.example.Bucherei.model;

import jakarta.persistence.*;

@Entity
@Table(name = "medium")
public class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediumId;

    private String titel;

    private String autor;

    private String kategorie;

    @Enumerated(EnumType.STRING)
    private MediumStatus status;

    // Konstruktoren

    public Medium() {
        this.status = MediumStatus.VERFUEGBAR;
    }

    public Medium(String titel, String autor, String kategorie) {
        this.titel = titel;
        this.autor = autor;
        this.kategorie = kategorie;
        this.status = MediumStatus.VERFUEGBAR;
    }

    // Getter & Setter

    public Long getMediumId() {
        return mediumId;
    }

    public void setMediumId(Long mediumId) {
        this.mediumId = mediumId;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public MediumStatus getStatus() {
        return status;
    }

    public void setStatus(MediumStatus status) {
        this.status = status;
    }
}