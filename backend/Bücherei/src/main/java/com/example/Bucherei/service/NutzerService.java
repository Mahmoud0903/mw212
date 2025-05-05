package com.example.Bucherei.service;

import com.example.Bucherei.model.Nutzer;
import com.example.Bucherei.repository.NutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class NutzerService {

    private final NutzerRepository nutzerRepository;

    @Autowired
    public NutzerService(NutzerRepository nutzerRepository) {
        this.nutzerRepository = nutzerRepository;
    }

    public Nutzer registrieren(Nutzer nutzer) {
        if (nutzerRepository.findByEmail(nutzer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ein Nutzer mit dieser E-Mail existiert bereits.");
        }

        nutzer.setRegistrierungsdatum(LocalDate.now());
        return nutzerRepository.save(nutzer);
    }

    public Nutzer findeNutzerNachId(Long id) {
        return nutzerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nutzer nicht gefunden"));
    }
}
