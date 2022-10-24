package com.example.springbootlab.pet.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 10:28
 * Description: The entity of pet class.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Pet implements Serializable {
    /**
     * Unique id (primary key).
     */
    private Long id;

    /**
     * The name of pet. e.g.:Tom, Jerry.
     */
    private String name;

    /**
     * Whether the pet is sick.
     */
    private Boolean isSick;

    /**
     * The class of pet.
     */
    private Animal animal;
}
