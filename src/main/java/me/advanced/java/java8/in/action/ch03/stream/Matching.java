package me.advanced.java.java8.in.action.ch03.stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taesu on 2018-07-07.
 */
@Service
public class Matching implements ApplicationRunner {
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
        System.out.println("Any match");
        if (this.dishes.stream().anyMatch(dish -> dish.getName().contains("감자"))) {
            System.out.println("감자 요리가 있다");
        }

        System.out.println("\nallMatch");
        if (this.dishes.stream().allMatch(dish -> dish.getCalories() > 100)) {
            System.out.println("모두 고칼로리 음식 뿐이구만");
        } else {
            System.out.println("저칼로리 음식도 있네");
        }


        System.out.println("\nnoneMatch");
        if (this.dishes.stream().noneMatch(dish -> dish.getName().contains("피자한판"))) {
            System.out.println("피자 한판은 없으니 마음껏 코스르 즐기자");
        } else {
            System.out.println("피자 한판은 자제하자");
        }

        System.out.println("\nfindAny");
        System.out.println("저칼로리 아무거나주세요  :" + this.dishes.stream().filter(Dish::isLowerCalories).findAny().orElseGet(null));



    }
}
