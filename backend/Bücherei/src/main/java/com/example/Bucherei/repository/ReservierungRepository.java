package com.example.Bucherei.repository;

import com.example.Bucherei.model.Reservierung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservierungRepository extends JpaRepository<Reservierung, Long> {
    List<Reservierung> findByNutzerNutzerId(Long nutzerId);
}
