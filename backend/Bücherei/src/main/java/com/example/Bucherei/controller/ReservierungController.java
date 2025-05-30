package com.example.Bucherei.controller;

import com.example.Bucherei.model.Reservierung;
import com.example.Bucherei.service.ReservierungService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller für Reservierungen.
 * <p>
 * Diese Klasse stellt Endpunkte bereit, um neue Reservierungen zu erstellen, bestehende abzurufen
 * oder zu stornieren sowie Reservierungen für ein bestimmtes Medium zu finden.
 */
@RestController
@RequestMapping("/api/reservierungen")
@Tag(name = "Reservierungen", description = "Operations related to Reservierungen")
public class ReservierungController {

    private final ReservierungService reservierungService;

    /**
     * Konstruktorinjektion des ReservierungService.
     *
     * @param reservierungService Service zur Verarbeitung von Reservierungen
     */
    @Autowired
    public ReservierungController(ReservierungService reservierungService) {
        this.reservierungService = reservierungService;
    }

    /**
     * Erstellt eine neue Reservierung für ein bestimmtes Medium durch einen bestimmten Nutzer.
     *
     * @param nutzerId ID des Nutzers
     * @param mediumId ID des Mediums
     * @return die erstellte Reservierung
     */
    @PostMapping
    public Reservierung reservieren(@RequestParam Long nutzerId, @RequestParam Long mediumId) {
        return reservierungService.reservieren(nutzerId, mediumId);
    }

    /**
     * Gibt alle Reservierungen eines bestimmten Nutzers zurück.
     *
     * @param nutzerId ID des Nutzers
     * @return Liste der Reservierungen
     */
    @GetMapping("/nutzer/{nutzerId}")
    public List<Reservierung> findeFuerNutzer(@PathVariable Long nutzerId) {
        return reservierungService.findeAlleFuerNutzer(nutzerId);
    }

    /**
     * Storniert eine offene Reservierung.
     *
     * @param reservierungsId ID der zu stornierenden Reservierung
     * @return die aktualisierte Reservierung
     */
    @PutMapping("/{reservierungsId}/stornieren")
    public Reservierung storniereReservierung(@PathVariable Long reservierungsId) {
        return reservierungService.storniereReservierung(reservierungsId);
    }

    /**
     * Sucht nach einer Reservierung anhand der Medium-ID.
     *
     * @param mediumId ID des Mediums
     * @return ResponseEntity mit der gefundenen Reservierung oder No Content
     */
    @GetMapping("/{mediumId}")
    public ResponseEntity<Reservierung> findReservierungByMedium(@PathVariable Long mediumId){
        return reservierungService.getReservierungByMediumId(mediumId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build()); // oder notFound()
    }}



