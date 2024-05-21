package com.expeditors.backend.service;

import com.expeditors.backend.dao.genericDao;
import com.expeditors.backend.domain.Adopter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
1. AdoptionService should allow users to perform basic create, update, delete operations on Adoptions to a store.
2. AdoptionService should allow users to retrieve an Adopter by ID
3. AdoptionService should allow users to retrieve all Adopters.
*/

@Service
public class AdoptionService {

    @Autowired
    private genericDao adopterDAO;


//    public AdoptionServiceAutowired(genericDao adopterDAO) {
//        this.adopterDAO = adopterDAO;
//    }

    public Adopter create(Adopter adopter) {
        //Validate DOB; Other business logic; Save the student to a Store == what kind of store?; Adopter insertedAdopter = adopterDAO.insert(adopter);
        return adopterDAO.insert(adopter);
    }

    public boolean delete(int id) {
        return adopterDAO.delete(id);
    }

    public boolean update(Adopter adopter) {
        return adopterDAO.update(adopter);
    }

    public Adopter getAdopter(int id) {
        return adopterDAO.findById(id);
    }

    public List<Adopter> getAdopters() {
        return adopterDAO.findAll();
    }

    public genericDao getAdopterDAO() {
        return adopterDAO;
    }

    public void setAdopterDAO(genericDao adopterDAO) {
        this.adopterDAO = adopterDAO;
    }
}

