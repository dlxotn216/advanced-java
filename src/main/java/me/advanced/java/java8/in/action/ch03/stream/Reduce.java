package me.advanced.java.java8.in.action.ch03.stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by taesu on 2018-07-07.
 */
@Service
public class Reduce implements ApplicationRunner {
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
        System.out.println("\n총 칼로리 : " + this.dishes.stream().map(Dish::getCalories).reduce(0, (d1, d2) -> d1 + d2));

        System.out.println("\n최대칼로리 :"+this.dishes.stream().map(Dish::getCalories).max(Comparator.comparingInt(o -> o)));

        System.out.println("\n최소칼로리 :"+this.dishes.stream().map(Dish::getCalories).min(Comparator.comparingInt(o -> o)));

        /*
        내부 상태를 갖는 연산 : 이전 상태를 알아야 하는 연산들 (reduce, distinct, max, min, sum 등)
        내부 상태를 갖지 않는 연산: 이전상태를 몰라도 되는 여산 (map, filter)
         */
    }
}
