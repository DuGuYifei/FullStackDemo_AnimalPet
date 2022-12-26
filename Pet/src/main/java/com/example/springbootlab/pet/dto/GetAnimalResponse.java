package com.example.springbootlab.pet.dto;

import com.example.springbootlab.pet.entity.Animal;
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

    public static Function<Animal, GetAnimalResponse> entityToDtoMapper() {
        return animal -> GetAnimalResponse.builder()
                .name(animal.getName())
                .build();
    }
}
