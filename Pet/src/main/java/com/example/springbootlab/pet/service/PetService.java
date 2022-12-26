package com.example.springbootlab.pet.service;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.pet.repository.AnimalRepository;
import com.example.springbootlab.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final AnimalRepository animalRepository;

    /**
     * @param repository repository for pet entity
     */
    @Autowired
    public PetService(PetRepository repository, AnimalRepository animalRepository) {
        this.repository = repository;
        this.animalRepository = animalRepository;
    }
    /**
     * Finds single pet.
     *
     * @param id pet's id
     * @return container with pet
     */
    public Optional<Pet> find(Long id) {
        return repository.findById(id);
    }

    /**
     * @return all available pets
     */
    public List<Pet> findAll() {
        return repository.findAll();
    }
    public List<Pet> findAll(Animal animal){ return repository.findAllByAnimal(animal); }
    public Optional<Pet> findByIdAndAnimal(Long id, String animalName){
        Optional<Animal> animal = animalRepository.findById(animalName);
        return repository.findByIdAndAnimal(id, animal.get());
    }
    /**
     * Creates new pet.
     *
     * @param pet new pet
     */
    @Transactional
    public Pet create(Pet pet) { return repository.save(pet); }

    /**
     * Updates existing pet.
     *
     * @param pet pet to be updated
     */
    @Transactional
    public void update(Pet pet) {
        repository.save(pet);
    }

    /**
     * Deletes existing pet.
     *
     * @param pet existing pet's id to be deleted
     */
    @Transactional
    public void delete(Long pet) { repository.deleteById(pet); }
}
