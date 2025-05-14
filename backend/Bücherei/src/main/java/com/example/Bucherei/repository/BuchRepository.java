package com.example.Bucherei.repository;

import com.example.Bucherei.model.Buch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuchRepository extends JpaRepository<Buch, Long> {
    List<Buch> findByTitelStartingWithIgnoreCase(String title);
    List<Buch> findByIsbn(String isbn);
}