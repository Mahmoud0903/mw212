package com.example.Bucherei.service;

import com.example.Bucherei.model.*;
import com.example.Bucherei.repository.MediumRepository;
import com.example.Bucherei.repository.NutzerRepository;
import com.example.Bucherei.repository.ReservierungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservierungService {

    private final ReservierungRepository reservierungRepository;
    private final NutzerRepository nutzerRepository;
    private final MediumRepository mediumRepository;

    @Autowired
    public ReservierungService(ReservierungRepository rr, NutzerRepository nr, MediumRepository mr) {
        this.reservierungRepository = rr;
        this.nutzerRepository = nr;
        this.mediumRepository = mr;
    }

    public Reservierung reservieren(Long nutzerId, Long mediumId) {
        Nutzer nutzer = nutzerRepository.findById(nutzerId)
                .orElseThrow(() -> new IllegalArgumentException("Nutzer nicht gefunden"));
        Medium medium = mediumRepository.findById(mediumId)
                .orElseThrow(() -> new IllegalArgumentException("Medium nicht gefunden"));

        if (medium.getStatus() != MediumStatus.VERFUEGBAR) {
            throw new IllegalStateException("Medium ist nicht verfügbar");
        }

        medium.setStatus(MediumStatus.RESERVIERT);
        mediumRepository.save(medium);

        Reservierung reservierung = new Reservierung(nutzer, medium);
        return reservierungRepository.save(reservierung);
    }

    public List<Reservierung> findeAlleFuerNutzer(Long nutzerId) {
        return reservierungRepository.findByNutzerNutzerId(nutzerId);
    }

    public Reservierung storniereReservierung(Long reservierungsId) {
        Reservierung reservierung = reservierungRepository.findById(reservierungsId)
                .orElseThrow(() -> new IllegalArgumentException("Reservierung nicht gefunden"));

        if (reservierung.getStatus() != ReservierungsStatus.OFFEN) {
            throw new IllegalStateException("Reservierung ist bereits abgeschlossen oder storniert");
        }

        reservierung.setStatus(ReservierungsStatus.STORNIERT);

        Medium medium = reservierung.getMedium();
        medium.setStatus(MediumStatus.VERFUEGBAR);
        mediumRepository.save(medium);

        return reservierungRepository.save(reservierung);
    }

}
