package com.example.springbootlab.pet.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Pet> pets;
}
