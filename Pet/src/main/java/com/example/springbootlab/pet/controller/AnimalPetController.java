package com.example.springbootlab.pet.controller;

import com.example.springbootlab.pet.dto.GetPetResponse;
import com.example.springbootlab.pet.dto.GetPetsResponse;
import com.example.springbootlab.pet.dto.UpdatePetRequest;
import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.pet.service.AnimalService;
import com.example.springbootlab.pet.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/11/14 11:19
 * Description:
 */

@RestController
@RequestMapping("api/animals/{name}/pets")
public class AnimalPetController {
    private final AnimalService animalService;
    private final PetService petService;

    public AnimalPetController(AnimalService animalService, PetService petService) {
        this.animalService = animalService;
        this.petService = petService;
    }

    @GetMapping("")
    public ResponseEntity<GetPetsResponse> getPet(@PathVariable("name") String name) {
        Optional<Animal> animal = animalService.find(name);
        return animal.map(value -> ResponseEntity.ok(GetPetsResponse.entityToDtoMapper().apply(petService.findAll(value))))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("{petID}")
    public ResponseEntity<GetPetResponse> getPet(@PathVariable("name") String name, @PathVariable("petID") Long id) {
        Optional<Pet> pet = petService.findByIdAndAnimal(id, name);
        return pet.map(value -> ResponseEntity.ok(GetPetResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{petID}")
    public ResponseEntity<Void> updatePet(@RequestBody UpdatePetRequest request, @PathVariable("petID") Long id) {
        Optional<Pet> pet = petService.find(id);
        if (pet.isPresent()) {
            UpdatePetRequest.dtoToEntityUpdater().apply(pet.get(), request);
            petService.update(pet.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{petID}")
    public ResponseEntity<Void> deletePet(@PathVariable("name") String name, @PathVariable("petID") Long id) {
        Optional<Pet> pet = petService.findByIdAndAnimal(id, name);
        if (pet.isPresent()) {
            petService.delete(pet.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}