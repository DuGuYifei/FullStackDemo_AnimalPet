package com.example.springbootlab.pet.dto;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.FoodType;
import com.example.springbootlab.pet.entity.Pet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/24 11:44
 * Description: GET animal response.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAnimalResponse {
    /**
     * Name of the profession.
     */
    private String name;

    private FoodType foodType;

    public static Function<Animal, GetAnimalResponse> entityToDtoMapper() {
        return animal -> GetAnimalResponse.builder()
                .name(animal.getName())
                .foodType(animal.getFood_type())
                .build();
    }
}
