package com.example.springbootlab.datastore;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.serialization.CloningUtility;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * For the sake of simplification instead of using real database this example is using an data source object which
 * should be put in servlet context in a single instance. In order to avoid
 * {@link java.util.ConcurrentModificationException} all methods are synchronized. Normally synchronization would be
 * carried on by the database server.
 */

@Log
@Component
public class DataStore {

    /**
     * Set of all available animals.
     */
    private Set<Animal> animals = new HashSet<>();

    /**
     * Set of all pets.
     */
    private Set<Pet> pets = new HashSet<>();

    /**
     * Seeks for all animals.
     *
     * @return list (can be empty) of all animals
     */
    public synchronized List<Animal> findAllAnimals() {
        return new ArrayList<>(animals);
    }

    /**
     * Seeks for the animal in the memory storage.
     *
     * @param name name of the animal
     * @return container (can be empty) with animal if present
     */
    public Optional<Animal> findAnimal(String name) {
        return animals.stream()
                .filter(animal -> animal.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Stores new animal.
     *
     * @param animal new animal to be stored
     * @throws IllegalArgumentException if animal with provided name already exists
     */
    public synchronized void createAnimal(Animal animal) throws IllegalArgumentException {
        findAnimal(animal.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The animal name \"%s\" is not unique", animal.getName()));
                },
                () -> animals.add(animal));
    }

    /**
     * Updates existing animal.
     *
     * @param animal pet to be updated
     * @throws IllegalArgumentException if animal with the same id does not exist
     */
    public synchronized void updateAnimal(Animal animal) throws IllegalArgumentException {
        findAnimal(animal.getName()).ifPresentOrElse(
                original -> {
                    animals.remove(original);
                    animals.add(animal);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The animal with name \"%s\" does not exist", animal.getName()));
                });
    }

    /**
     * Deletes existing animal.
     *
     * @param name animal's name
     * @throws IllegalArgumentException if animal with provided name does not exist
     */
    public synchronized void deleteAnimal(String name) throws IllegalArgumentException {
        findAnimal(name).ifPresentOrElse(
                original -> animals.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The animal with name \"%s\" does not exist", name));
                });
    }

    /**
     * Seeks for all pets.
     *
     * @return list (can be empty) of all pets
     */
    public synchronized List<Pet> findAllPets() {
        return pets.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    /**
     * Seeks for single pet.
     *
     * @param id pet's id
     * @return container (can be empty) with pet
     */
    public synchronized Optional<Pet> findPet(Long id) {
        return pets.stream()
                .filter(pet -> pet.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    /**
     * Stores new pet.
     *
     * @param pet new pet
     */
    public synchronized void createPet(Pet pet) throws IllegalArgumentException {
        pet.setId(findAllPets().stream().mapToLong(Pet::getId).max().orElse(0) + 1);
        pets.add(pet);
    }

    /**
     * Updates existing pet.
     *
     * @param pet pet to be updated
     * @throws IllegalArgumentException if pet with the same id does not exist
     */
    public synchronized void updatePet(Pet pet) throws IllegalArgumentException {
        findPet(pet.getId()).ifPresentOrElse(
                original -> {
                    pets.remove(original);
                    pets.add(pet);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The pet with id \"%d\" does not exist", pet.getId()));
                });
    }

    /**
     * Deletes existing pet.
     *
     * @param id pet's id
     * @throws IllegalArgumentException if pet with provided id does not exist
     */
    public synchronized void deletePet(Long id) throws IllegalArgumentException {
        findPet(id).ifPresentOrElse(
                original -> pets.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The pet with id \"%d\" does not exist", id));
                });
    }

    /**
     * Get stream to be used (for filtering, sorting, etc) in repositories.
     *
     * @return pet's stream
     */
    public Stream<Pet> getPetStream() {
        return pets.stream();
    }

}
