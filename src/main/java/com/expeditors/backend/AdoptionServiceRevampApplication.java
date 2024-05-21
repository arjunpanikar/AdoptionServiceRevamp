package com.expeditors.backend;

import com.expeditors.backend.domain.Adopter;
import com.expeditors.backend.domain.Pet;
import com.expeditors.backend.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@SpringBootApplication
public class AdoptionServiceRevampApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdoptionServiceRevampApplication.class, args);
    }
    @Component
    class MyRunner implements CommandLineRunner {

        @Autowired
        private AdoptionService adoptionService;

        @Override
        public void run(String... args) throws Exception {

            Pet pet1 = new Pet("Dogs", "Rudolph", "Tibetan Mastiff");
            Adopter adopter1 = new Adopter("John Antony", "901-123-4567", LocalDate.of(2024, 1, 10),pet1);
            Adopter firstAdopter = adoptionService.create(adopter1);

            Pet pet2 = new Pet("Dogs", "Theo", "Labrador Retriever");
            Adopter adopter2 = new Adopter("Arjun Panikar", "901-780-4567", LocalDate.of(2024, 1, 10),pet2);
            Adopter secondAdopter = adoptionService.create(adopter2);

            //System.out.println("MyRunner called");
            //List<Adopter> adopters = adoptionService.getAdopters();
           // System.out.println("Adopters " + adopters.size());
            //System.out.println(adopters);
        }
    }
}
