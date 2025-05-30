package com.example.Bucherei.controller;

import com.example.Bucherei.model.Medium;
import com.example.Bucherei.service.MediumService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST-Controller für Medien.
 * <p>
 * Diese Klasse stellt Endpunkte bereit, um Medien zu erstellen, abzurufen oder nach bestimmten Begriffen zu durchsuchen.
 */
@RestController
@RequestMapping("/api/medien")
@Tag(name = "Medien", description = "Operations related to Medien")
public class MediumController {

    private final MediumService mediumService;

    /**
     * Konstruktorinjektion des MediumService.
     *
     * @param mediumService Service zur Verarbeitung von Medienoperationen
     */
    @Autowired
    public MediumController(MediumService mediumService) {
        this.mediumService = mediumService;
    }

    /**
     * Erstellt ein neues Medium und speichert es in der Datenbank.
     *
     * @param medium das zu erstellende Medium
     * @return das gespeicherte Medium
     */
    @PostMapping
    public Medium erstellen(@RequestBody Medium medium) {
        return mediumService.speichern(medium);
    }

    /**
     * Gibt alle verfügbaren Medien zurück.
     *
     * @return Liste aller Medien
     */
    @GetMapping
    public List<Medium> findeAlle() {
        return mediumService.findeAlle();
    }

    /**
     * Gibt ein einzelnes Medium anhand seiner ID zurück.
     *
     * @param id ID des Mediums
     * @return das gefundene Medium
     */
    @GetMapping("/{id}")
    public Medium findeNachId(@PathVariable Long id) {
        return mediumService.findeNachId(id);
    }

    /**
     * Durchsucht Titel und Autorennamen nach einem bestimmten Suchbegriff.
     *
     * @param begriff der Suchbegriff
     * @return Liste der passenden Medien
     */
    @GetMapping("/suche")
    public List<Medium> suche(@RequestParam String begriff) {
        return mediumService.suche(begriff);
    }
}
