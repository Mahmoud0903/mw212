package com.example.Bucherei.config;

import com.example.Bucherei.model.Buch;
import com.example.Bucherei.model.MediumStatus;
import com.example.Bucherei.model.MediumTyp;
import com.example.Bucherei.repository.MediumRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
public class BuchSeeder implements CommandLineRunner {

    //private final BuchRepository buchRepository;
    private final MediumRepository mediumRepository;
    private final Random random = new Random();

  /*  public BuchSeeder(BuchRepository buchRepository) {
        this.buchRepository = buchRepository;
    }*/
      public BuchSeeder(MediumRepository mediumRepository) {
        this.mediumRepository = mediumRepository;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker(new Locale("de"));

        for (int i = 0; i < 10; i++) {
            MediumStatus[] statusWerte = MediumStatus.values();
            MediumStatus zufallsStatus = statusWerte[random.nextInt(statusWerte.length)];

            MediumTyp[] typWerte = MediumTyp.values();
            MediumTyp zufallsTyp = typWerte[random.nextInt(typWerte.length)];

            // Zufälliges Bild von picsum.photos
            String randomImageLink = "https://picsum.photos/200/300?random=" + faker.number().randomDigitNotZero();

            Random random = new Random();
            int seitenanzahl = 100 + random.nextInt(900); // 100 bis 999

            Buch buch = new Buch(faker.book().title(),faker.book().author(),"",randomImageLink,zufallsStatus,zufallsTyp,faker.code().isbn13(),seitenanzahl);

            //buchRepository.save(buch);
            mediumRepository.save(buch);
        }

        System.out.println(" 10 Fake-Bücher mit zufälligen Bildern wurden erfolgreich gespeichert.");
    }
}
