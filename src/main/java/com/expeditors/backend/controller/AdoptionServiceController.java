package com.expeditors.backend.controller;

import com.expeditors.backend.domain.Adopter;
import com.expeditors.backend.service.AdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/adopter")
@RestController
public class AdoptionServiceController {

    @Autowired
    private AdoptionService adoptionService;

    @GetMapping()
    public List<Adopter> getAll(){
        List<Adopter> adopters = adoptionService.getAdopters();
        return adopters;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdopterByID(@PathVariable("id") int adopterId){
        Adopter adopter = adoptionService.getAdopter(adopterId);
        if(adopter==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Adopter with id: " + adopterId);
        }
        return ResponseEntity.ok(adopter);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Adopter adopter) {
        Adopter newAdopter = adoptionService.create(adopter);

            URI newResource = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{adopterId}")
                    .buildAndExpand(adopter.getAdopterId())
                    .toUri();

        //return ResponseEntity.created(newResource).body(newAdopter);
        return ResponseEntity.created(newResource).build();
    }

    @DeleteMapping("/{adopterId}")
    public ResponseEntity<?> delete(@PathVariable("adopterId") int adopterId){
        boolean result = adoptionService.delete(adopterId);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Adopter with id: " + adopterId);
        }

        //return ResponseEntity.created(newResource).body(newAdopter);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{adopterId}")
    public ResponseEntity<?> update(@RequestBody Adopter adopter){
        boolean result = adoptionService.update(adopter);
        if(!result) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Adopter with id: " + adopter.getAdopterId());
        }

        //return ResponseEntity.created(newResource).body(newAdopter);
        return ResponseEntity.noContent().build();
    }
}
