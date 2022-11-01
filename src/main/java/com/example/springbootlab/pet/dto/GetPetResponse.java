package com.example.springbootlab.pet.dto;

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
 * Date: 2022/10/24 11:32
 * Description: GET profession response.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetPetResponse {
    private Long id;

    private String name;

    private Boolean is_sick;

    private String animal;

    /**
     * @return mapper for convenient converting entity object to dto object
     */
    public static Function<Pet, GetPetResponse> entityToDtoMapper() {
        return pet -> GetPetResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .is_sick(pet.getIs_sick())
                .animal(pet.getAnimal().getName())
                .build();
    }
}
