package com.example.springbootlab.pet.controller;

import com.example.springbootlab.pet.dto.CreatePetRequest;
import com.example.springbootlab.pet.dto.GetPetResponse;
import com.example.springbootlab.pet.dto.GetPetsResponse;
import com.example.springbootlab.pet.dto.UpdatePetRequest;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.pet.service.AnimalService;
import com.example.springbootlab.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * REST controller for pet resource. It does not return or receive entity objects but dto objects which present
 * only those fields which are converted to JSON.
 */
@RestController
@RequestMapping("api/pets")
public class PetController {

    /**
     * Service for managing pets.
     */
    private final PetService petService;

    /**
     * Service for managing animals.
     */
    private final AnimalService animalService;

    /**
     * @param petService  service for managing pets
     * @param animalService service for managing animals
     */
    @Autowired
    public PetController(PetService petService, AnimalService animalService) {
        this.petService = petService;
        this.animalService = animalService;
    }

    /**
     * @return list of pets which will be converted to JSON
     */
    @GetMapping
    public ResponseEntity<GetPetsResponse> getPets() {
        return ResponseEntity.ok(GetPetsResponse.entityToDtoMapper().apply(petService.findAll()));
    }

    /**
     * @param id id of the pet
     * @return single pet in JSON format or 404 when pet does not exist
     */
    @GetMapping("{id}")
    public ResponseEntity<GetPetResponse> getPet(@PathVariable("id") long id) {
        Optional<Pet> pet = petService.find(id);
        return pet.map(value -> ResponseEntity.ok(GetPetResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * @param request new pet parsed from JSON
     * @param builder URI builder
     * @return response with location header
     */
    @PostMapping
    public ResponseEntity<Void> createPet(@RequestBody CreatePetRequest request, UriComponentsBuilder builder) {
        Pet pet = CreatePetRequest
                .dtoToEntityMapper(animal -> animalService.find(animal).orElseThrow())
                .apply(request);
        pet = petService.create(pet);
        return ResponseEntity.created(builder.pathSegment("api", "pets", "{id}").buildAndExpand(pet.getId()).toUri()).build();
    }

    /**
     * Deletes selected pet.
     *
     * @param id pet's id
     * @return accepted for not found if pet does not exist
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePet(@PathVariable("id") long id) {
        Optional<Pet> pet = petService.find(id);
        if (pet.isPresent()) {
            petService.delete(pet.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Updates existing pet.
     *
     * @param request pet's data parsed from JSON
     * @param id      pet's id
     * @return accepted or not found if pet does not exist
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> updatePet(@RequestBody UpdatePetRequest request, @PathVariable("id") long id) {
        Optional<Pet> pet = petService.find(id);
        if (pet.isPresent()) {
            UpdatePetRequest.dtoToEntityUpdater().apply(pet.get(), request);
            petService.update(pet.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
