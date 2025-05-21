package com.example.Bucherei.service;

import com.example.Bucherei.model.Medium;
import com.example.Bucherei.model.MediumStatus;
import com.example.Bucherei.model.Reservierung;
import com.example.Bucherei.repository.MediumRepository;
import com.example.Bucherei.repository.ReservierungRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservierungCleanupService {

    private final ReservierungRepository reservierungRepository;
    private final MediumRepository mediumRepository;

    public ReservierungCleanupService(ReservierungRepository reservierungRepository, MediumRepository mediumRepository) {
        this.reservierungRepository = reservierungRepository;
        this.mediumRepository = mediumRepository;
    }

    // Cron: täglich um Mitternacht
   /* @Scheduled(fixedRate = 60000) // alle 60 Sekunden
    public void removeExpiredReservations() {
        repository.deleteExpired(LocalDateTime.now());
    }*/

    @Scheduled(fixedRate = 60000)
    public void removeExpiredReservations() {
        List<Reservierung> abgelaufene = reservierungRepository.findByReserviertBisBefore(LocalDateTime.now());

        for (Reservierung reservierung : abgelaufene) {
            Medium medium = reservierung.getMedium();
            medium.setStatus(MediumStatus.VERFUEGBAR);
            mediumRepository.save(medium);
        }

        reservierungRepository.deleteAll(abgelaufene);
    }

}
