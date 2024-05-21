package com.expeditors.backend.dao;

import com.expeditors.backend.domain.Adopter;

import java.util.List;

public interface genericDao {
    Adopter insert(Adopter adopter);

    boolean update(Adopter adopter);

    boolean delete(int id);

    Adopter findById(int id);

    List<Adopter> findAll();
}
