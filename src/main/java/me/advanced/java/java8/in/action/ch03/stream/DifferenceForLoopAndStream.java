package me.advanced.java.java8.in.action.ch03.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by taesu on 2018-07-06.
 */
@Service
public class DifferenceForLoopAndStream implements ApplicationRunner {
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
        Supplier<List<String>> basicForLoop = () -> {
            List<Dish> lowCaloriesMenu = new ArrayList<>();
            //외부반복
            for (Dish dish : this.dishes) {
                /*
                    만약 this.dishes를 먼저 정렬한 후 로직을 수행한다면 원본 리스트를 변형하기때문에
                    side effect 발생 가능성 높아짐
                 */
                if (dish.isLowerCalories()) {
                    lowCaloriesMenu.add(dish);
                }
            }

            if (CollectionUtils.isEmpty(lowCaloriesMenu)) {
                return Collections.emptyList();
            }

            Collections.sort(lowCaloriesMenu, new Comparator<Dish>() {
                @Override
                public int compare(Dish o1, Dish o2) {
                    return o1.getCalories() - o2.getCalories();
                }
            });

            List<String> lowerCaloriesMenuName = new ArrayList<>();
            for (Dish dish : lowCaloriesMenu) {
                lowerCaloriesMenuName.add(dish.getName());
            }
            return lowerCaloriesMenuName;
        };
        System.out.println("BasicForLoop");
        basicForLoop.get().forEach(System.out::println);


        //원본은 유지된다, 내부반복
        Supplier<List<String>> useStream = () -> this.dishes.stream().filter(Dish::isLowerCalories)
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("\nUserStream");
        useStream.get().forEach(System.out::println);
    }
}
