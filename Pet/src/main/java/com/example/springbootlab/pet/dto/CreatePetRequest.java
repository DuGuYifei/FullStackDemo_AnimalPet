package com.example.springbootlab.pet.dto;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.Pet;
import lombok.*;

import java.util.function.Function;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/24 11:26
 * Description: POST pet request.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreatePetRequest {
    /**
     * Name of the pet.
     */
    private String name;

    /**
     * Whether the pet is sick.
     */
    private Boolean is_sick;

    /**
     * Name of the pets's animal.
     */
    private String animal;

    /**
     * @param animalFunction function for converting animal name to animal entity object
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<CreatePetRequest, Pet> dtoToEntityMapper(Function<String, Animal> animalFunction){
        return request -> Pet.builder()
                .name(request.getName())
                .is_sick(request.getIs_sick())
                .animal(animalFunction.apply(request.getAnimal()))
                .build();
    }
}
