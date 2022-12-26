package com.example.springbootlab.configuration;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.pet.service.AnimalService;
import com.example.springbootlab.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Listener started automatically on CDI application context initialized. Injects proxy to the services and fills
 * database with default content. When using persistence storage application instance should be initialized only during
 * first run in order to init database with starting data. Good place to create first default admin user.
 */
@Component
public class InitializedData {

    /**
     * Service for pets operations.
     */
    private final PetService petService;

    /**
     * Service for animals operations.
     */
    private final AnimalService animalService;

    @Autowired
    public InitializedData(PetService petService, AnimalService animalService) {
        this.petService = petService;
        this.animalService = animalService;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should
     * be created only once.
     */
    @PostConstruct
    private synchronized void init() {

        Animal dog = Animal.builder().name("Dog").build();
        Animal cat = Animal.builder().name("Cat").build();
        Animal sheep = Animal.builder().name("Sheep").build();

        animalService.create(dog);
        animalService.create(cat);
        animalService.create(sheep);

        Pet spike = Pet.builder()
                .name("Spike")
                .is_sick(false)
                .animal(dog)
                .build();

        Pet tom = Pet.builder()
                .name("Tom")
                .is_sick(false)
                .animal(cat)
                .build();

        Pet shaun = Pet.builder()
                .name("Shaun")
                .is_sick(false)
                .animal(sheep)
                .build();

        petService.create(spike);
        petService.create(tom);
        petService.create(shaun);

    }
}
