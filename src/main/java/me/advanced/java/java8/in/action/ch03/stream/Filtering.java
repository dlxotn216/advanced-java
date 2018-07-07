package me.advanced.java.java8.in.action.ch03.stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taesu on 2018-07-06.
 */
@Service
public class Filtering implements ApplicationRunner {
    private List<Dish> dishes = Arrays.asList(
            new Dish(100, "오트밀"),
            new Dish(1000, "튀김"),
            new Dish(230, "핫도그"),
            new Dish(300, "감자튀김"),
            new Dish(520, "햄버거"),
            new Dish(2000, "피자한판"),
            new Dish(407, "식빵"),
            new Dish(670, "서브웨이"),
            new Dish(304, "밥한공기"),
            new Dish(20, "과일")
    );

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nFiltering basic");
        this.dishes.stream().filter(Dish::isLowerCalories).forEach(System.out::println);

        System.out.println("\nFiltering top 3");
        this.dishes.stream().filter(Dish::isLowerCalories).limit(3).forEach(System.out::println);

        System.out.println("\nFiltering top 3 and skip 2");
        this.dishes.stream().filter(Dish::isLowerCalories).limit(3).skip(2).forEach(System.out::println);

        System.out.println("\nFiltering skip 2 and top 3");
        this.dishes.stream().filter(Dish::isLowerCalories).skip(2).limit(3).forEach(System.out::println);
    }
}
