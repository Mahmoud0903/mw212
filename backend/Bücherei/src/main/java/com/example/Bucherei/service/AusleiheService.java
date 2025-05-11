package com.example.Bucherei.service;

import com.example.Bucherei.model.*;
import com.example.Bucherei.repository.AusleiheRepository;
import com.example.Bucherei.repository.MediumRepository;
import com.example.Bucherei.repository.NutzerRepository;
import com.example.Bucherei.repository.ReservierungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AusleiheService {

    @Autowired
    private AusleiheRepository ausleiheRepository;

    @Autowired
    private NutzerRepository nutzerRepository;

    @Autowired
    private MediumRepository mediumRepository;

    @Autowired
    private ReservierungRepository reservierungRepository;

    public Ausleihe ausleihen(Long nutzerId, Long mediumId, int tage) {
        Nutzer nutzer = nutzerRepository.findById(nutzerId)
                .orElseThrow(() -> new IllegalArgumentException("Nutzer nicht gefunden"));
        Medium medium = mediumRepository.findById(mediumId)
                .orElseThrow(() -> new IllegalArgumentException("Medium nicht gefunden"));

        if (medium.getStatus() != MediumStatus.VERFUEGBAR && medium.getStatus() != MediumStatus.RESERVIERT) {
            throw new IllegalStateException("Medium ist nicht verfügbar.");
        }

        // Prüfen, ob eine Reservierung vorhanden ist
        List<Reservierung> offeneReservierungen = reservierungRepository
                .findByMediumMediumIdAndStatus(mediumId, ReservierungsStatus.OFFEN);

        Reservierung zugeordneteReservierung = null;
        if (!offeneReservierungen.isEmpty()) {
            zugeordneteReservierung = offeneReservierungen.get(0);
            zugeordneteReservierung.setStatus(ReservierungsStatus.ABGEHOLT);
            reservierungRepository.save(zugeordneteReservierung);
        }

        medium.setStatus(MediumStatus.AUSGELIEHEN);
        mediumRepository.save(medium);

        Ausleihe ausleihe = new Ausleihe(nutzer, medium, tage);
        ausleihe.setReservierung(zugeordneteReservierung);
        return ausleiheRepository.save(ausleihe);
    }

    public List<Ausleihe> findeAlleFuerNutzer(Long nutzerId) {
        return ausleiheRepository.findByNutzerNutzerId(nutzerId);
    }

    public Ausleihe verlaengern(Long ausleiheId, LocalDate neuesRueckgabedatum) {
        Ausleihe ausleihe = ausleiheRepository.findById(ausleiheId)
                .orElseThrow(() -> new IllegalArgumentException("Ausleihe nicht gefunden"));

        if (neuesRueckgabedatum != null && neuesRueckgabedatum.isAfter(ausleihe.getRueckgabedatum())) {
            ausleihe.setRueckgabedatum(neuesRueckgabedatum);
            ausleihe.setStatus(AusleihStatus.VERLAENGERT);
        } else {
            throw new IllegalArgumentException("Neues Rückgabedatum muss nach dem aktuellen liegen.");
        }

        return ausleiheRepository.save(ausleihe);
    }
}

