package com.example.springbootlab.configuration;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.FoodType;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.pet.service.AnimalService;
import com.example.springbootlab.pet.service.PetService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

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

        Animal dog = Animal.builder().name("Dog").foodType(FoodType.omnivore).build();
        Animal cat = Animal.builder().name("Cat").foodType(FoodType.carnivore).build();
        Animal sheep = Animal.builder().name("Sheep").foodType(FoodType.herbivore).build();

        animalService.create(dog);
        animalService.create(cat);
        animalService.create(sheep);

        Pet spike = Pet.builder()
                .name("Spike")
                .isSick(false)
                .animal(dog)
                .build();

        Pet tom = Pet.builder()
                .name("Tom")
                .isSick(false)
                .animal(cat)
                .build();

        Pet shaun = Pet.builder()
                .name("Shaun")
                .isSick(false)
                .animal(sheep)
                .build();

        petService.create(spike);
        petService.create(tom);
        petService.create(shaun);
    }

    /**
     * @param name name of the desired resource
     * @return array of bytes read from the resource
     */
    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }
}
