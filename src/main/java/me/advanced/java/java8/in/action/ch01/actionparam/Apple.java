package me.advanced.java.java8.in.action.ch01.actionparam;

import lombok.Value;

/**
 * Created by taesu on 2018-07-04.
 */
@Value
public class Apple {
    private Integer weight;
    private String color;
    private Integer price;
}

interface AbstractPredicate<T> {
    Boolean test(T t);
}

interface ApplePredicate {
    Boolean test(Apple apple);
}
