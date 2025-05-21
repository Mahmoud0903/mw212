package com.example.Bucherei.config;

import com.example.Bucherei.model.Buch;
import com.example.Bucherei.model.MediumStatus;
import com.example.Bucherei.model.MediumTyp;
import com.example.Bucherei.model.Nutzer;
import com.example.Bucherei.repository.MediumRepository;
import com.example.Bucherei.repository.NutzerRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

@Component
public class BuchSeeder implements CommandLineRunner {

    //private final BuchRepository buchRepository;
    private final MediumRepository mediumRepository;
    private final Random random = new Random();
    private final NutzerRepository nutzerRepository; // NEU

  /*  public BuchSeeder(BuchRepository buchRepository) {
        this.buchRepository = buchRepository;
    }*/
      public BuchSeeder(MediumRepository mediumRepository, NutzerRepository nutzerRepository) {
        this.mediumRepository = mediumRepository;
        this.nutzerRepository = nutzerRepository;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker(new Locale("de"));

        this.generiereNutzer();

        for (int i = 0; i < 10; i++) {
            MediumStatus[] statusWerte = MediumStatus.values();
            MediumStatus zufallsStatus = statusWerte[random.nextInt(statusWerte.length)];

            MediumTyp[] typWerte = MediumTyp.values();
            MediumTyp zufallsTyp = typWerte[random.nextInt(typWerte.length)];

            // Zufälliges Bild von picsum.photos
            String randomImageLink = "https://picsum.photos/200/300?random=" + faker.number().randomDigitNotZero();

            Random random = new Random();
            int seitenanzahl = 100 + random.nextInt(900); // 100 bis 999

            Buch buch = new Buch(faker.book().title(),faker.book().author(),"",randomImageLink,MediumStatus.VERFUEGBAR,MediumTyp.BUCH,faker.code().isbn13(),seitenanzahl);

            //buchRepository.save(buch);
            mediumRepository.save(buch);
        }

        System.out.println(" 10 Fake-Bücher mit zufälligen Bildern wurden erfolgreich gespeichert.");
    }

    public void generiereNutzer() {
        // 👤 Dummy-Nutzer erzeugen, falls noch nicht vorhanden
        String email = "admin@example.com";
        if (nutzerRepository.findByEmail(email).isEmpty()) {
            Nutzer nutzer = new Nutzer();
            nutzer.setName("Max Mustermann");
            nutzer.setEmail(email);
            nutzer.setPasswort(
                   "myPassword"
            );
            nutzer.setAdresse("Musterstraße 1");
            nutzer.setGeburtsdatum(LocalDate.of(1990, 1, 1));
            nutzer.setRegistrierungsdatum(LocalDate.now());
            nutzer.setTelefonnummer("0123456789");

            nutzerRepository.save(nutzer);
            System.out.println("Dummy-Nutzer wurde erstellt.");
        } else {
            System.out.println("Dummy-Nutzer existiert bereits.");
        }
    }
}
