package com.example.springbootlab.pet.dto;

import com.example.springbootlab.pet.entity.Pet;
import lombok.*;

import java.util.function.BiFunction;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/24 11:47
 * Description: PUT pet request.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdatePetRequest {

    private String name;

    private Boolean is_sick;

    public static BiFunction<Pet, UpdatePetRequest, Pet> dtoToEntityUpdater() {
        return (pet, request) -> {
            pet.setName(request.getName());
            pet.setIs_sick(request.getIs_sick());
            return pet;
        };
    }
}
