package com.example.springbootlab.pet.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 9:36
 * Description: The entity of Animal class.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "animals")
public class Animal implements Serializable {
    /**
     * Name of animal, e.g.: dog, cat
     */
    @Id
    private String name;

    /**
     * Food type of animal: carnivore, herbivore, omnivore
     */
    private FoodType food_type;
}
