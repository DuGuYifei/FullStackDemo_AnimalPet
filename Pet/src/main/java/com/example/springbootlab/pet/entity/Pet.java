package com.example.springbootlab.pet.entity;

import lombok.*;

import javax.persistence.*;
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
@Entity
@Table(name = "pets")
public class Pet implements Serializable {
    /**
     * Unique id (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    /**
     * The name of pet. e.g.:Tom, Jerry.
     */
    private String name;

    /**
     * Whether the pet is sick.
     */
    private Boolean is_sick;

    /**
     * The class of pet.
     */
    @ManyToOne
    @JoinColumn(name = "animal")
    private Animal animal;
}
