package com.example.Bucherei.repository;

import com.example.Bucherei.model.Reservierung;
import com.example.Bucherei.model.ReservierungsStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservierungRepository extends JpaRepository<Reservierung, Long> {
    List<Reservierung> findByNutzerNutzerId(Long nutzerId);

    List<Reservierung> findByMediumMediumIdAndStatus(Long mediumId, ReservierungsStatus reservierungsStatus);
}
