package com.example.springbootlab.pet.repository;

import com.example.springbootlab.datastore.DataStore;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 11:04
 * Description: The DAO of Pet.
 */

@org.springframework.stereotype.Repository
public class PetRepository implements Repository<Pet, Long> {
    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public PetRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Pet> find(Long id) {
        return store.findPet(id);
    }

    @Override
    public List<Pet> findAll() {
        return store.findAllPets();
    }

    @Override
    public void create(Pet entity) {
        store.createPet(entity);
    }

    @Override
    public void delete(Pet entity) {
        store.deletePet(entity.getId());
    }

    @Override
    public void update(Pet entity) {
        store.updatePet(entity);
    }
}

