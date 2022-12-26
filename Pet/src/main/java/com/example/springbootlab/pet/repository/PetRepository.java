package com.example.springbootlab.pet.repository;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 11:04
 * Description: The DAO of Pet.
 */

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByAnimal(Animal animal);
    Optional<Pet> findByIdAndAnimal(Long id, Animal animal);
}

