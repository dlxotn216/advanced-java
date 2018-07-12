package me.advanced.java.java8.in.action.ch07.optional.model;

import lombok.Data;

import java.util.Optional;

/**
 * Created by taesu on 2018-07-12.
 */
@Data
public class OptionalPerson {
    private String name;
    private Optional<OptionalCar> car;

    public Optional<OptionalCar> getCar(){
        return car == null ? Optional.empty() : car;
    }
}
