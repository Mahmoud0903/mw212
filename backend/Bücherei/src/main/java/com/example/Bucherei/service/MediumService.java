package com.example.Bucherei.service;

import com.example.Bucherei.model.Medium;
import com.example.Bucherei.repository.MediumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediumService {

    private final MediumRepository mediumRepository;

    @Autowired
    public MediumService(MediumRepository mediumRepository) {
        this.mediumRepository = mediumRepository;
    }

    public Medium speichern(Medium medium) {
        return mediumRepository.save(medium);
    }

    public List<Medium> findeAlle() {
        return mediumRepository.findAll();
    }

    public Medium findeNachId(Long id) {
        return mediumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medium nicht gefunden"));
    }

    public List<Medium> suche(String begriff) {
        return mediumRepository.findByTitelContainingIgnoreCaseOrAutorContainingIgnoreCase(begriff, begriff);
    }
}
