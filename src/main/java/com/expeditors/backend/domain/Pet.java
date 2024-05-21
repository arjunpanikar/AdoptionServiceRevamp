package com.expeditors.backend.domain;

import jakarta.persistence.*;

@Entity
public class Pet {

    private static int petCounter = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int petId;
    private String petType;
    private String petName;
    private String petBreed;


    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        if (petType.equals("Dogs") || petType.equals("Cats") || petType.equals("Turtles") || petType.equals("NA")) {
            this.petType = petType;
        } else {
            throw new IllegalArgumentException("Only Dogs/Cats/Turtles are adopted");
        }
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        if (petName != null && !petName.trim().isEmpty() && petName.matches("^[a-zA-Z0-9 ]*$")) {
            this.petName = petName;
        } else {
            throw new IllegalArgumentException("Invalid Pet Name Entered");
        }
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        if (petBreed != null && !petBreed.trim().isEmpty() && petBreed.matches("^[a-zA-Z0-9 ]*$")) {
            this.petBreed = petBreed;
        } else {
            throw new IllegalArgumentException("Invalid Pet Breed Entered");
        }
    }

    public Pet(String petType, String petName, String petBreed) {
        setPetId(petCounter++);
        setPetType(petType);
        setPetName(petName);
        setPetBreed(petBreed);
    }

    public Pet() {
        setPetId(petCounter++);
    }

    @Override
    public String toString(){
        return " Pet Id - " +  petId + " Pet Type - " +  petType + "; Pet Name - " + petName + "; Pet Breed - " + petBreed;
    }
}
