package com.example.springbootlab.animal.event.repository;

import com.example.springbootlab.animal.entity.Animal;
import com.example.springbootlab.animal.event.dto.PostAnimalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AnimalEventRepository  {

    private final RestTemplate restTemplate;

    @Autowired
    public AnimalEventRepository(@Value("${springbootlab.pet.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(String animal) {
        restTemplate.delete("/animals/{name}", animal);
    }

    public void create(Animal animal) {
        restTemplate.postForLocation("/animals", PostAnimalRequest.entityToDtoMapper().apply(animal));
    }
}
