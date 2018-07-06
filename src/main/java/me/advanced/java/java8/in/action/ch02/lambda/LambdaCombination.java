package me.advanced.java.java8.in.action.ch02.lambda;

import me.advanced.java.java8.in.action.ch01.actionparam.Apple;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by taesu on 2018-07-06.
 */
@Service
public class LambdaCombination implements ApplicationRunner {
    private List<Apple> apples = Arrays.asList(
            new Apple(300, "Red", 3000),
            new Apple(300, "Red", 2000),
            new Apple(300, "Red", 4000),
            new Apple(431, "Red", 3100),
            new Apple(210, "Green", 4000),
            new Apple(1002, "Red", 2000),
            new Apple(650, "Blue", 1000),
            new Apple(88, "Red", 52000),
            new Apple(110, "Green", 7000));

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Action combineComparator = () -> {
            Comparator<Apple> compareByWeight = Comparator.comparing(Apple::getWeight);
            Comparator<Apple> compareByPrice = Comparator.comparing(Apple::getPrice);

            //무거우면서 가격이 싼 순서대로
            System.out.println("Combine comparators");
            apples.sort(compareByWeight.reversed().thenComparing(compareByPrice));
            apples.forEach(System.out::println);
        };
        combineComparator.action();

        Action combinePredicate = () -> {
            Predicate<Apple> heavyApple = apple -> apple.getWeight() >= 300;
            Predicate<Apple> redApple = apple -> apple.getColor().equalsIgnoreCase("red");

            System.out.println("\nCombine predicates");
            apples.stream().filter(heavyApple.and(redApple.negate())).forEach(System.out::println);
        };
        combinePredicate.action();

        Action combineFunction  = () -> {
            Function<Apple, String> mapToAppleColor = Apple::getColor;
            Function<String, String> mapToLowerCase = String::toLowerCase;

            System.out.println("\nCombine combineFunction");
            apples.stream().map(mapToAppleColor).map(mapToLowerCase).forEach(System.out::println);
        };
        combineFunction.action();
    }
}

interface Action {
    void action();
}