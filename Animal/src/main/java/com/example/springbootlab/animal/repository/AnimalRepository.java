package com.example.springbootlab.animal.repository;

import com.example.springbootlab.animal.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created By IDEA
 * Author: s188026 Yifei Liu
 * Date: 2022/10/10 10:32
 * Description: Repository for animal entity. Repositories should be used in business layer (e.g.: in services).
 */

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String> {

}
