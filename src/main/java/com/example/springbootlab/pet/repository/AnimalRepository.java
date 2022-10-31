package com.example.springbootlab.pet.repository;

import com.example.springbootlab.datastore.DataStore;
import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 10:32
 * Description: The DAO of Animal.
 */

@org.springframework.stereotype.Repository
public class AnimalRepository implements Repository<Animal, String> {
    /**
     * Underlying data store. In future should be replaced with database connection.
     */
    private DataStore store;

    /**
     * @param store data store
     */
    @Autowired
    public AnimalRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Animal> find(String id) {
        return store.findAnimal(id);
    }

    @Override
    public List<Animal> findAll() {
        return store.findAllAnimals();
    }

    @Override
    public void create(Animal entity) {
        store.createAnimal(entity);
    }

    @Override
    public void delete(Animal entity) {
        store.deleteAnimal(entity.getName());
    }

    @Override
    public void update(Animal entity) {
        store.updateAnimal(entity);
    }
}
