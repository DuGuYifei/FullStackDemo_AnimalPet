package com.example.springbootlab.animal.dto;

import com.example.springbootlab.animal.entity.Animal;
import com.example.springbootlab.animal.entity.FoodType;
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
public class CreateAnimalRequest {

    private String name;

    private FoodType food_type;

    public static Function<CreateAnimalRequest, Animal> dtoToEntityMapper(){
        return request -> Animal.builder()
                .name(request.getName())
                .food_type(request.getFood_type())
                .build();
    }
}
