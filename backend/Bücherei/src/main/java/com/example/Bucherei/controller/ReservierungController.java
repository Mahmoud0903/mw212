package com.example.Bucherei.controller;

import com.example.Bucherei.model.Reservierung;
import com.example.Bucherei.service.ReservierungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservierungen")
public class ReservierungController {

    private final ReservierungService reservierungService;

    @Autowired
    public ReservierungController(ReservierungService reservierungService) {
        this.reservierungService = reservierungService;
    }

    @PostMapping
    public Reservierung reservieren(@RequestParam Long nutzerId, @RequestParam Long mediumId) {
        return reservierungService.reservieren(nutzerId, mediumId);
    }

    @GetMapping("/nutzer/{nutzerId}")
    public List<Reservierung> findeFuerNutzer(@PathVariable Long nutzerId) {
        return reservierungService.findeAlleFuerNutzer(nutzerId);
    }

    @PutMapping("/{reservierungsId}/stornieren")
    public Reservierung storniereReservierung(@PathVariable Long reservierungsId) {
        return reservierungService.storniereReservierung(reservierungsId);
    }

}
