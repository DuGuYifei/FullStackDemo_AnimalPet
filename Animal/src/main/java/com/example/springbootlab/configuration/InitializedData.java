package com.example.springbootlab.configuration;

import com.example.springbootlab.animal.entity.Animal;
import com.example.springbootlab.animal.entity.FoodType;
import com.example.springbootlab.animal.service.AnimalService;
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
     * Service for animals operations.
     */
    private final AnimalService animalService;

    @Autowired
    public InitializedData(AnimalService animalService) {
        this.animalService = animalService;
    }

    /**
     * Initializes database with some example values. Should be called after creating this object. This object should
     * be created only once.
     */
    @PostConstruct
    private synchronized void init() {

        Animal dog = Animal.builder().name("Dog").food_type(FoodType.omnivore).build();
        Animal cat = Animal.builder().name("Cat").food_type(FoodType.carnivore).build();
        Animal sheep = Animal.builder().name("Sheep").food_type(FoodType.herbivore).build();

        animalService.create(dog);
        animalService.create(cat);
        animalService.create(sheep);

    }
}
