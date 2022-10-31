package com.example.springbootlab;

import com.example.springbootlab.pet.entity.Animal;
import com.example.springbootlab.pet.entity.FoodType;
import com.example.springbootlab.pet.entity.Pet;
import com.example.springbootlab.pet.service.PetService;
import com.example.springbootlab.pet.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;


/**
 * Component for interaction with user using command line. Components annotated with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    private final PetService petService;
    private final AnimalService animalService;

    @Autowired
    public CommandLine(PetService petService, AnimalService animalService) {
        this.petService = petService;
        this.animalService = animalService;
    }

    @Override
    public void run(String... args) throws Exception {
        petService.findAll().forEach(System.out::println);
        animalService.findAll().forEach(System.out::println);
        System.out.println("""
                p1: list  all categories
                p2: list all elements
                c: add new pet or animal
                d1: delete existing animal
                d2: delete existing pet
                q: stop the application""");
        Scanner input = new Scanner(System.in);
        String val = null;
        int stop = 1;
        while(stop > 0){
            System.out.print("Input command：");
            val = input.next();
            switch (val) {
                case "q" -> stop = 0;
                case "p1" -> animalService.findAll().forEach(System.out::println);
                case "p2" -> petService.findAll().forEach(System.out::println);
                case "c" -> {
                    System.out.println("Create new animal(a) or pet(p)? Input a or p or c(cancel)");
                    int cancel = 1;
                    while(cancel > 0) {
                        val = input.next();
                        switch (val) {
                            case "a" -> {
                                System.out.print("Input Animal name: ");
                                String name = input.next();
                                System.out.print("Choose animal's food type:\n0: " +
                                        FoodType.carnivore + "\n1: " +
                                        FoodType.herbivore + "\n2: " +
                                        FoodType.omnivore + "\n");
                                int idx = Integer.parseInt(input.next());
                                Animal a = Animal.builder().name(name).foodType(FoodType.values()[idx]).build();
                                animalService.create(a);
                                System.out.println("You have create animal:\n" + a);
                                cancel = 0;
                            }
                            case "p" -> {
                                System.out.print("Choose animal categories:\n");
                                Integer i = 0;
                                List<Animal> animalsList = animalService.findAll();
                                for (Animal a : animalsList) {
                                    System.out.println(i + ": " + a);
                                    i++;
                                }
                                int idx = Integer.parseInt(input.next());
                                System.out.print("Input pet name: ");
                                String name = input.next();
                                System.out.print("Input whether sick: ");
                                Boolean isSick = Boolean.parseBoolean(input.next());
                                Pet p = Pet.builder()
                                        .name(name)
                                        .isSick(isSick)
                                        .animal(animalsList.get(idx))
                                        .build();
                                petService.create(p);
                                System.out.println("You have create pet:\n" + p);
                                cancel = 0;
                            }
                            case "c" -> cancel = 0;
                        }
                    }
                }
                case "d1" -> {
                    System.out.println("Choose animal name:\n");
                    List<Animal> animalsList = animalService.findAll();
                    for (Animal a : animalsList) {
                        System.out.println(a.getName() + ": " + a);
                    }
                    String name = input.next();
                    System.out.println("Deleting animal:\n" + animalService.find(name));
                    animalService.delete(name);
                    System.out.println("Finish deleting service\n");
                }
                case "d2" -> {
                    System.out.println("Choose pet id:\n");
                    List<Pet> petsList = petService.findAll();
                    for (Pet p : petsList) {
                        System.out.println(p.getId() + ": " + p);
                    }
                    Long idx = Long.parseLong(input.next());
                    System.out.println("Deleting pet:\n" + petService.find(idx));
                    petService.delete(idx);
                    System.out.println("Finish deleting service\n");
                }
            }
        }
        System.out.println("You input q, Good-bye！");
        input.close(); // 关闭资源
    }
}
