package com.example.Bucherei.service;
import com.example.Bucherei.model.Buch;
import com.example.Bucherei.repository.BuchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuchService {

    private final BuchRepository buchRepository;

    public BuchService(BuchRepository bookRepository) {
        this.buchRepository = bookRepository;
    }

    public List<Buch> alleBuecher() {
        return buchRepository.findAll();
    }

    public Buch buchErstellen(Buch buch) {
        return buchRepository.save(buch);
    }

    public Buch buchNachId(Long id) {
        return buchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Buch mit ID " + id + " nicht gefunden"));
    }

    public void buchLoeschen(Long id) {
        if (!buchRepository.existsById(id)) {
            throw new EntityNotFoundException("Buch mit ID " + id + " existiert nicht");
        }
        buchRepository.deleteById(id);
    }

    public List<Buch> sucheNachTitel(String titel) {
        return buchRepository.findByTitelStartingWithIgnoreCase(titel);
    }

    // Optional: Suche nach ISBN
    public List<Buch> sucheNachIsbn(String isbn) {
        return buchRepository.findByIsbn(isbn);
    }
}
