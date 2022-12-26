package com.example.springbootlab.animal.dto;

import com.example.springbootlab.animal.entity.Animal;
import com.example.springbootlab.animal.entity.FoodType;
import lombok.*;

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

    private FoodType food_type;

    public static Function<Animal, GetAnimalResponse> entityToDtoMapper() {
        return animal -> GetAnimalResponse.builder()
                .name(animal.getName())
                .food_type(animal.getFood_type())
                .build();
    }
}
