package com.example.springbootlab.animal.controller;

import com.example.springbootlab.animal.dto.GetAnimalResponse;
import com.example.springbootlab.animal.dto.GetAnimalsResponse;
import com.example.springbootlab.animal.entity.Animal;
import com.example.springbootlab.animal.service.AnimalService;
import com.example.springbootlab.animal.dto.CreateAnimalRequest;
import com.example.springbootlab.animal.dto.UpdateAnimalRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/11/1 1:32
 * Description: REST controller for animal resource
 */

@RestController
@RequestMapping("api/animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<GetAnimalsResponse> getAnimals() {
        return ResponseEntity.ok(GetAnimalsResponse.entityToDtoMapper().apply(animalService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetAnimalResponse> getAnimal(@PathVariable("name") String name) {
        Optional<Animal> animal = animalService.find(name);
        System.out.println(name);
        return animal.map(value -> ResponseEntity.ok(GetAnimalResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createAnimal(@RequestBody CreateAnimalRequest request, UriComponentsBuilder builder) {
        Optional<Animal> findRes = animalService.find(request.getName());
        if(findRes.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("This animal has been existed!");
        }
        Animal animal = CreateAnimalRequest.dtoToEntityMapper().apply(request);
        animal = animalService.create(animal);
        return ResponseEntity.created(builder.pathSegment("api", "animals", "{name}").buildAndExpand(animal.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable("name") String name) {
        Optional<Animal> animal = animalService.find(name);
        if (animal.isPresent()) {
            animalService.delete(animal.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> updateAnimal(@RequestBody UpdateAnimalRequest request, @PathVariable("name") String name) {
        Optional<Animal> animal = animalService.find(name);
        if (animal.isPresent()) {
            UpdateAnimalRequest.dtoToEntityUpdater().apply(animal.get(), request);
            animalService.update(animal.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
