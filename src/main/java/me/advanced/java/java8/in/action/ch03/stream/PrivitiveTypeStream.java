package me.advanced.java.java8.in.action.ch03.stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

/**
 * Created by taesu on 2018-07-07.
 */
@Service
public class PrivitiveTypeStream implements ApplicationRunner {
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
        System.out.println("\n기본형 스트림");
        System.out.println("\n총 칼로리 :"+this.dishes.stream().map(Dish::getCalories).reduce(0,Integer::sum));
        //boxing 발생

        System.out.println("\n총 칼로리 (mapToInt):"+this.dishes.stream().mapToInt(Dish::getCalories).sum());

        //기본형 특화 Optional wrapper
        OptionalInt maxCalories = this.dishes.stream().mapToInt(Dish::getCalories).max();
        System.out.println("\n최대 칼로리 : "+maxCalories.orElse(-1));

    }
}
