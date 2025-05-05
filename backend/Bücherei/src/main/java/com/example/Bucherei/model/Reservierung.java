package com.example.Bucherei.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reservierung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Nutzer nutzer;

    @ManyToOne
    private Medium medium;

    private LocalDate reservierungsdatum;

    @Enumerated(EnumType.STRING)
    private ReservierungsStatus status;

    public Reservierung() {
        // leer für JPA
    }

    public Reservierung(Nutzer nutzer, Medium medium) {
        this.nutzer = nutzer;
        this.medium = medium;
        this.reservierungsdatum = LocalDate.now();
        this.status = ReservierungsStatus.OFFEN;
    }

    // Getter & Setter …

    public Long getId() { return id; }

    public Nutzer getNutzer() { return nutzer; }

    public Medium getMedium() { return medium; }

    public LocalDate getReservierungsdatum() { return reservierungsdatum; }

    public ReservierungsStatus getStatus() { return status; }

    public void setStatus(ReservierungsStatus status) { this.status = status; }
}
