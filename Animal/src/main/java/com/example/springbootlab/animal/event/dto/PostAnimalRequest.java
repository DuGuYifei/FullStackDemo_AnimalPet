package com.example.springbootlab.animal.event.dto;

import com.example.springbootlab.animal.entity.Animal;
import lombok.*;

import java.util.function.Function;

/**
 * PSOT animal request. Contains only fields that can be set during animal creation. Animal is defined in
 * {@link Animal}.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostAnimalRequest {

    /**
     * User's login.
     */
    private String name;

    /**
     * @return mapper for convenient converting dto object to entity object
     */
    public static Function<Animal, PostAnimalRequest> entityToDtoMapper() {
        return entity -> PostAnimalRequest.builder()
                .name(entity.getName())
                .build();
    }

}
