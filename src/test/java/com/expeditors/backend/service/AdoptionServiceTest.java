package com.expeditors.backend.service;

import com.expeditors.backend.domain.Adopter;
import com.expeditors.backend.domain.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@ContextConfiguration(classes = {adopterConfig.class})
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdoptionServiceTest {

    @Autowired
    private AdoptionService adoptionService;

    @Test
    public void testAdopterServiceInsert() {
        Pet pet = new Pet("Dogs", "Rudolph", "Tibetan Mastiff");
        Adopter adopter = new Adopter("John Antony", "901-123-4567", LocalDate.of(2024, 1, 10),pet);
        //AdoptionService adoptionService = new AdoptionService();
        Adopter insertedAdopter = adoptionService.create(adopter);
        System.out.println("Adopter: " + insertedAdopter);
        assertNotNull(insertedAdopter);
    }

    @Test
    public void testDeleteExistingAdopter() {
        Pet pet = new Pet("Dogs", "Dickey", "Pomeranian");
        Adopter adopter = new Adopter("Dan Blake", "901-143-4567", LocalDate.of(2024, 1, 15),pet);
        //AdoptionService adoptionService = new AdoptionService();
        Adopter insertedAdopter = adoptionService.create(adopter);
        System.out.println("Adopter: " + insertedAdopter);
        assertNotNull(insertedAdopter);

        boolean deleted = adoptionService.delete(insertedAdopter.getAdopterId());
        assertTrue(deleted);
    }

    @Test
    public void testDeleteNonExistingAdopter() {
        //AdoptionService adoptionService = new AdoptionService();
        boolean deleted = adoptionService.delete(1000);
        assertFalse(deleted);
    }

    @Test
    public void testGetExistingAdopter() {
        Pet pet = new Pet("Dogs", "Rudolph", "Tibetan Mastiff");
        Adopter adopter = new Adopter("John Antony", "901-123-4567", LocalDate.of(2024, 1, 10),pet);
        //AdoptionService adoptionService = new AdoptionService();
        Adopter insertedAdopter = adoptionService.create(adopter);
        assertEquals(adoptionService.getAdopter(13).getAdopterName(),"John Antony");
    }

    @Test
    public void testUpdateExistingAdopter() {
        Pet pet = new Pet("Dogs", "Rudolph", "Tibetan Mastiff");
        Adopter adopter = new Adopter("John Antony", "901-123-4567", LocalDate.of(2024, 1, 10),pet);
        //AdoptionService adoptionService = new AdoptionService();
        Adopter insertedAdopter = adoptionService.create(adopter);
        insertedAdopter.setAdopterName("Johny Antony");
        boolean modifiedAdopter = adoptionService.update(insertedAdopter);
        System.out.println(adoptionService.getAdopters());
        assertEquals(adoptionService.getAdopter(13).getAdopterName(),"Johny Antony");
    }

    @Test
    public void testGetAllAdopter() {
        Pet pet = new Pet("Dogs", "Rudolph", "Tibetan Mastiff");
        Adopter adopter = new Adopter("John Antony", "901-123-4567", LocalDate.of(2024, 1, 10),pet);
        //AdoptionService adoptionService = new AdoptionService();
        Adopter firstAdopter = adoptionService.create(adopter);
        Pet pet2 = new Pet("Dogs", "Theo", "Labrador Retriever");
        Adopter adopter2 = new Adopter("Arjun Panikar", "901-780-4567", LocalDate.of(2024, 1, 10),pet2);
        Adopter secondAdopter = adoptionService.create(adopter2);
        assertEquals(4, adoptionService.getAdopters().size());
    }
}
