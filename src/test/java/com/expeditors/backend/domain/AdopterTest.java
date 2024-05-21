package com.expeditors.backend.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
class AdopterTest {

    @Test
    void setEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Adopter("", "843-111-1111"));
    }

    @Test
    void setValidName() {
        Adopter fred = new Adopter("Fred Sullivan", "843-111-1111");
        assertEquals (fred.getAdopterName(), "Fred Sullivan");
    }

    @Test
    void setInValidName() {
        assertThrows(IllegalArgumentException.class, () -> new Adopter("Fred&Sullivan", "843-111-1111"));
    }

    @Test
    void setValidPet() {
        Pet pet = new Pet("Turtles", "Simon", "Spotted Turtle");
        assertEquals (pet.getPetName(), "Simon");
    }

    @Test
    void setInValidPetType() {
        assertThrows(IllegalArgumentException.class, () -> new Pet("Fish", "Mickey","Goldfish"));
    }

    @Test
    void setInValidPetDetails() {
        assertThrows(IllegalArgumentException.class, () -> new Pet("Dogs", "843-111-1111","Pomeranian"));
    }

}