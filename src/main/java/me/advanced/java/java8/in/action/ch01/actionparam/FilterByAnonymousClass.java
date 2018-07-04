package me.advanced.java.java8.in.action.ch01.actionparam;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by taesu on 2018-07-04.
 */
@Service
public class FilterByAnonymousClass implements ApplicationRunner {
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<Apple> apples = Arrays.asList(
                new Apple(300, "Red", 3000),
                new Apple(431, "Red", 3100),
                new Apple(210, "Green", 4000),
                new Apple(1002, "Red", 2000),
                new Apple(650, "Blue", 1000),
                new Apple(88, "Red", 52000),
                new Apple(110, "Green", 7000));


        //단일 필터
        System.out.println("무게가 300그램 초과인 사과");
        this.filter(apples, new ApplePredicate() {
            @Override
            public Boolean test(Apple apple) {
                if(apple.getWeight() > 300){
                    return true;
                }
                return false;
            }
        }).forEach(System.out::println);

        //필터의 중첩
        System.out.println("무게가 300그램 초과이면서 빨간색인 사과");
        this.filter(this.filter(apples, new ApplePredicate() {
            @Override
            public Boolean test(Apple apple) {
                if (apple.getWeight() > 300) {
                    return true;
                }
                return false;
            }
        }), new ApplePredicate() {
            @Override
            public Boolean test(Apple apple) {
                if(apple.getColor().equalsIgnoreCase("Red")){
                    return true;
                }
                return false;
            }
        }).forEach(System.out::println);
    }

    private List<Apple> filter(List<Apple> apples, final ApplePredicate predicate) {
        final List<Apple> result = new ArrayList<>();
        apples.forEach(apple -> {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        });
        return result;
    }
}
