package com.example.springbootlab.pet.dto;

import com.example.springbootlab.pet.entity.Animal;
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

    public static Function<CreateAnimalRequest, Animal> dtoToEntityMapper(){
        return request -> Animal.builder()
                .name(request.getName())
                .build();
    }
}
