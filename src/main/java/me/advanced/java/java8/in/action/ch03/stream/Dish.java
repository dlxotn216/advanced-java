package me.advanced.java.java8.in.action.ch03.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;

/**
 * Created by taesu on 2018-07-06.
 */
@AllArgsConstructor
@Getter
@ToString
class Dish implements Comparator<Dish> {
    private int calories;
    private String name;

    public boolean isLowerCalories() {
        return this.getCalories() < 400;
    }

    @Override
    public int compare(Dish o1, Dish o2) {
        return o1.getCalories() - o2.getCalories();
    }
}