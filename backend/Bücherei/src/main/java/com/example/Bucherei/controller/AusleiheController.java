package com.example.Bucherei.controller;

import com.example.Bucherei.model.Ausleihe;
import com.example.Bucherei.service.AusleiheService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * REST-Controller für Ausleihen.
 * <p>
 * Diese Klasse stellt Endpunkte zur Verfügung, um Medien auszuleihen, alle Ausleihen eines Nutzers
 * abzurufen und bestehende Ausleihen zu verlängern.
 */
@RestController
@RequestMapping("/api/ausleihen")
@Tag(name = "Ausleihen", description = "Operations related to Ausleihen (Lendings)")
public class AusleiheController {

    @Autowired
    private AusleiheService ausleiheService;

    /**
     * Erstellt eine neue Ausleihe für ein Medium durch einen Nutzer.
     * <p>
     * Die Ausleihdauer wird anhand des Rückgabedatums berechnet. Falls kein Datum angegeben ist,
     * wird eine Standarddauer von 14 Tagen verwendet.
     *
     * @param ausleihe ein Objekt mit den notwendigen Informationen (Nutzer, Medium, Rückgabedatum)
     * @return die gespeicherte Ausleihe
     */
    @PostMapping
    public Ausleihe ausleihen(@RequestBody Ausleihe ausleihe) {
        return ausleiheService.ausleihen(
                ausleihe.getNutzer().getNutzerId(),
                ausleihe.getMedium().getMediumId(),
                ausleihe.getRueckgabedatum() != null
                        ? (int) ChronoUnit.DAYS.between(LocalDate.now(), ausleihe.getRueckgabedatum())
                        : 14
        );
    }

    /**
     * Gibt alle Ausleihen eines bestimmten Nutzers zurück.
     *
     * @param nutzerId die ID des Nutzers
     * @return Liste der Ausleihen
     */
    @GetMapping("/nutzer/{nutzerId}")
    public List<Ausleihe> findeFuerNutzer(@PathVariable Long nutzerId) {
        return ausleiheService.findeAlleFuerNutzer(nutzerId);
    }

    /**
     * Verlängert eine bestehende Ausleihe um ein neues Rückgabedatum.
     *
     * @param ausleiheId ID der Ausleihe, die verlängert werden soll
     * @param ausleihe   Objekt mit dem neuen Rückgabedatum
     * @return die aktualisierte Ausleihe
     */
    @PutMapping("/{ausleiheId}/verlaengern")
    public Ausleihe verlaengern(@PathVariable Long ausleiheId, @RequestBody Ausleihe ausleihe) {
        return ausleiheService.verlaengern(ausleiheId, ausleihe.getRueckgabedatum());
    }
}

