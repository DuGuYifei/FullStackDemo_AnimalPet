package com.example.springbootlab.animal.dto;

import com.example.springbootlab.animal.entity.Animal;
import com.example.springbootlab.animal.entity.FoodType;
import lombok.*;

import java.util.function.BiFunction;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/11/1 4:05
 * Description: PUT animal request
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateAnimalRequest {

    private FoodType food_type;

    public static BiFunction<Animal, UpdateAnimalRequest, Animal> dtoToEntityUpdater(){
        return (animal, request) -> {
            animal.setFood_type(request.getFood_type());
            return animal;
        };
    }
}
