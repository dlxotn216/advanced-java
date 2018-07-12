package me.advanced.java.java8.in.action.ch07.optional.model;

import lombok.Data;

/**
 * Created by taesu on 2018-07-12.
 */
@Data
public class Person {
    private String name;
    private Car car;

    public Person(String name) {
        this.name = name;
    }
}
