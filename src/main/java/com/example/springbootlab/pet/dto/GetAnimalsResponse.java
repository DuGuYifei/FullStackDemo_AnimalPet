package com.example.springbootlab.pet.dto;

import com.example.springbootlab.pet.entity.Animal;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/24 11:46
 * Description:
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetAnimalsResponse {

    @Singular
    private List<Animal> animals;

    public static Function<Collection<Animal>, GetAnimalsResponse> entityToDtoMapper(){
        return animals -> {
            GetAnimalsResponseBuilder response = GetAnimalsResponse.builder();
            animals.stream().map(animal -> Animal.builder()
                                .name(animal.getName())
                                .food_type(animal.getFood_type())
                                .build())
                    .forEach(response::animal);
            return response.build();
        };
    }
}
