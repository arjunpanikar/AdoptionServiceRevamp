package com.expeditors.backend.domain;

import com.expeditors.backend.domain.Pet;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Adopter {

    private static int adopterCounter = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adopterId;
    @Column(name = "name")
    private String adopterName;
    @Column(name = "phone")
    private String adopterPhone;
    @Column(name = "adoption_date")
    private LocalDate adoptionDate;
    @OneToOne
    private Pet Pet;

    public Adopter(){}
    public int getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(int adopterId) {
        this.adopterId = adopterId;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        if (adopterName != null && !adopterName.trim().isEmpty() && adopterName.matches("^[a-zA-Z ]*$")) {
            this.adopterName = adopterName;

        } else {
            System.out.println("Name: " + adopterName + "invalid");
                throw new IllegalArgumentException("Invalid Adopter Name Entered");
            }
    }

    public String getAdopterPhone() {
        return adopterPhone;
    }

    public void setAdopterPhone(String adopterPhone) {
        if (adopterPhone != null && !adopterPhone.trim().isEmpty() && adopterPhone.matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}$")) {
            this.adopterPhone = adopterPhone;
        } else {
            throw new RuntimeException("Invalid Phone Number Entered");
        }
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        if (LocalDate.now().isAfter(adoptionDate)) {
            this.adoptionDate = adoptionDate;
        } else {
            throw new RuntimeException("Only past dates allowed");
        }
    }

    public com.expeditors.backend.domain.Pet getPet() {
        return Pet;
    }

    public void setPet(com.expeditors.backend.domain.Pet pet) {
        Pet = pet;
    }

    public Adopter(String adopterName, String adopterPhone, LocalDate adoptionDate, Pet pet){
        setAdopterId(adopterCounter++);
        setAdopterName(adopterName);
        setAdopterPhone(adopterPhone);
        setAdoptionDate(adoptionDate);
        setPet(pet);
    }

    public Adopter(String adopterName, String adopterPhone){
        setAdopterId(adopterCounter++);
        setAdopterName(adopterName);
        setAdopterPhone(adopterPhone);
        this.adoptionDate = LocalDate.of(2000, 1, 1);
        this.Pet = new Pet("NA","NA","NA");
    }

    @Override
    public String toString(){
        return "Adopter Id - " +  adopterId + "; Adopter Name - " +  adopterName + "; Adopter Phone - " + adopterPhone + "; Adopter Date - " + adoptionDate + ";" + Pet;
    }

}
