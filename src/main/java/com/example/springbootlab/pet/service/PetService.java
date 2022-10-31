package com.example.springbootlab.pet.service;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 11:13
 * Description: Service layer for all business actions regarding pet entity
 */

@Service
public class PetService {

    /**
     * Repository for pet entity.
     */
    private final PetRepository repository;
    
    /**
     * @param repository repository for pet entity
     */
    @Autowired
    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    /**
     * Finds single pet.
     *
     * @param id pet's id
     * @return container with pet
     */
    public Optional<Pet> find(Long id) {
        return repository.find(id);
    }

    /**
     * @return all available pets
     */
    public List<Pet> findAll() {
        return repository.findAll();
    }

    /**
     * Creates new pet.
     *
     * @param pet new pet
     */
    public void create(Pet pet) {
        repository.create(pet);
    }

    /**
     * Updates existing pet.
     *
     * @param pet pet to be updated
     */
    public void update(Pet pet) {
        repository.update(pet);
    }

    /**
     * Deletes existing pet.
     *
     * @param pet existing pet's id to be deleted
     */
    public void delete(Long pet) {
        repository.delete(repository.find(pet).orElseThrow());
    }
    
}
