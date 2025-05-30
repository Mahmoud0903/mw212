package com.example.Bucherei.controller;

import com.example.Bucherei.model.Nutzer;
import com.example.Bucherei.service.NutzerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST-Controller für Nutzer.
 * <p>
 * Dieser Controller stellt Endpunkte für die Registrierung neuer Nutzer und das Abrufen von Nutzerinformationen bereit.
 */
@RestController
@RequestMapping("/api/nutzer")
@Tag(name = "Nutzer", description = "Operations related to Nutzer")
public class NutzerController {

    private final NutzerService nutzerService;

    /**
     * Konstruktorinjektion des NutzerService.
     *
     * @param nutzerService Service zur Verarbeitung von Nutzeroperationen
     */
    @Autowired
    public NutzerController(NutzerService nutzerService) {
        this.nutzerService = nutzerService;
    }

    /**
     * Registriert einen neuen Nutzer.
     * <p>
     * Prüft, ob die angegebene E-Mail-Adresse bereits vergeben ist und setzt das Registrierungsdatum.
     *
     * @param nutzer das zu registrierende Nutzerobjekt
     * @return der erfolgreich registrierte Nutzer
     */
    @PostMapping("/registrieren")
    public Nutzer registrieren(@Valid @RequestBody Nutzer nutzer) {
        return nutzerService.registrieren(nutzer);
    }

    /**
     * Gibt einen Nutzer anhand seiner ID zurück.
     *
     * @param id ID des Nutzers
     * @return das entsprechende Nutzerobjekt
     */
    @GetMapping("/{id}")
    public Nutzer findeNutzer(@PathVariable Long id) {
        return nutzerService.findeNutzerNachId(id);
    }
}
