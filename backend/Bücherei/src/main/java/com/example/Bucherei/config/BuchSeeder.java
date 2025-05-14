package com.example.Bucherei.config;

import com.example.Bucherei.model.Buch;
import com.example.Bucherei.model.MediumStatus;
import com.example.Bucherei.repository.BuchRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
public class BuchSeeder implements CommandLineRunner {

    private final BuchRepository buchRepository;
    private final Random random = new Random();

    public BuchSeeder(BuchRepository buchRepository) {
        this.buchRepository = buchRepository;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker(new Locale("de"));

        for (int i = 0; i < 10; i++) {
            Buch buch = new Buch();
            buch.setTitel(faker.book().title());
            buch.setAutor(faker.book().author());
            buch.setIsbn(faker.code().isbn13());

            // Zufälliges Bild von picsum.photos
            String randomImageLink = "https://picsum.photos/200/300?random=" + faker.number().randomDigitNotZero();
            buch.setBildLink(randomImageLink);  // Annahme, dass du eine `bildUrl`-Eigenschaft im `Buch`-Modell hast

            // Zufälliger Status
            MediumStatus[] statusWerte = MediumStatus.values();
            MediumStatus zufallsStatus = statusWerte[random.nextInt(statusWerte.length)];
            buch.setStatus(zufallsStatus);

            buchRepository.save(buch);
        }

        System.out.println(" 10 Fake-Bücher mit zufälligen Bildern wurden erfolgreich gespeichert.");
    }
}
