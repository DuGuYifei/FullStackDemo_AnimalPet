package com.example.springbootlab.pet.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;


/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/24 11:35
 * Description: GET pets response
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPetsResponse {
    /**
     * Represents single pet in list.
     */
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Pet {

        private Long id;

        private String name;

    }

    /**
     * Name of the selected pets.
     */
    @Singular
    private List<Pet> pets;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Collection<com.example.springbootlab.pet.entity.Pet>, GetPetsResponse> entityToDtoMapper() {
        return pets -> {
            GetPetsResponseBuilder response = GetPetsResponse.builder();
            pets.stream()
                    .map(pet -> Pet.builder()
                            .id(pet.getId())
                            .name(pet.getName())
                            .build())
                    .forEach(response::pet);
            return response.build();
        };
    }
}
