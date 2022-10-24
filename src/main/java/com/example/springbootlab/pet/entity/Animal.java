package com.example.springbootlab.pet.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 9:36
 * Description: The class of pet.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Animal implements Serializable {
    /**
     * Name of animal, e.g.: dog, cat
     */
    private String name;

    /**
     * Food type of animal: carnivore, herbivore, omnivore
     */
    private FoodType foodType;
}
