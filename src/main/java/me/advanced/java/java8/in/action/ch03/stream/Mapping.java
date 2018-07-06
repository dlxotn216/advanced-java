package me.advanced.java.java8.in.action.ch03.stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by taesu on 2018-07-06.
 */
@Service
public class Mapping implements ApplicationRunner {
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
        System.out.println("Map to name");
        this.dishes.stream().map(Dish::getName).forEach(System.out::println);

        System.out.println("\nMap to concat name");
        //Stream 평면화
        this.dishes.stream().map(Dish::getName).flatMap(name -> name.chars().mapToObj(i -> (char) i)).forEach(System.out::print);
        System.out.println();

        System.out.println("\nMap to concat name and print distinct character set");    //또는 set으로 collecting
        Stream.concat(this.dishes.stream().map(Dish::getName).flatMap(name -> name.chars().mapToObj(i -> (char) i)).distinct(), Stream.of('\n')).forEach(System.out::print);

        System.out.println("\n제곱근");
        Stream.concat(Arrays.stream(new int[]{1, 2, 3, 4, 5}).mapToObj(i -> i * i + ","), Stream.of('\n')).forEach(System.out::print);

        System.out.println("\n순서쌍");
        int[] list1 = new int[]{1, 2, 3, 4, 5};
        int[] list2 = new int[]{6, 7, 8};
        Arrays.stream(list1)
                .boxed()
                .flatMap(i -> Arrays.stream(list2)
                        .boxed()
                        .map(j -> new int[]{i, j}))
                .forEach(array -> {
                    Arrays.stream(array).forEach(element -> System.out.print(element + ","));
                    System.out.println();
                });

        System.out.println("\n합이 3배수인 순서쌍");
        Arrays.stream(list1)
                .boxed()
                .flatMap(i -> Arrays.stream(list2)
                        .boxed()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .forEach(array -> {
                    Arrays.stream(array).forEach(element -> System.out.print(element + ","));
                    System.out.println();
                });

    }
}
