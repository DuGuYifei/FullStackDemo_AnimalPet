package com.example.springbootlab.animal.service;

import com.example.springbootlab.animal.entity.Animal;
import com.example.springbootlab.animal.event.repository.AnimalEventRepository;
import com.example.springbootlab.animal.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 11:10
 * Description: Service layer for all business actions regarding pet's class(Animal) entity.
 */

@Service
public class AnimalService {

    /**
     * Repository for animal entity.
     */
    private final AnimalRepository repository;
    private final AnimalEventRepository eventRepository;

    /**
     * @param repository repository for animal entity
     */
    @Autowired
    public AnimalService(AnimalRepository repository, AnimalEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    /**
     * @param name name of the animal
     * @return container with animal entity
     */
    public Optional<Animal> find(String name) {
        return repository.findById(name);
    }

    public List<Animal> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(String name) {
        repository.deleteById(name);
        eventRepository.delete(name);
    }

    @Transactional
    public void update(Animal entity) { repository.save(entity); }

    @Transactional
    public Animal create(Animal animal) {
        eventRepository.create(animal);
        return repository.save(animal);
    }

}