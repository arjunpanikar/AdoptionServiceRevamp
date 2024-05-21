package com.expeditors.backend.dao.JPA;

import com.expeditors.backend.dao.genericDao;
import com.expeditors.backend.domain.Adopter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile(value="dev")
public class JPAAdopterDao implements genericDao {

    private Map<Integer, Adopter> adopters = new ConcurrentHashMap<>();

    @Override
    public Adopter insert(Adopter adopter) {
        adopters.put(adopter.getAdopterId(), adopter);
        return adopter;
    }

    @Override
    public boolean update(Adopter adopter) {
        return adopters.replace(adopter.getAdopterId(), adopter) != null;
    }

    @Override
    public boolean delete(int id) {
        return adopters.remove(id) != null;
    }

    @Override
    public Adopter findById(int id) {
        return adopters.get(id);
    }

    @Override
    public List<Adopter> findAll() {
        return new ArrayList<>(adopters.values());
    }
}

