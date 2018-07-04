package me.advanced.java.java8.in.action.ch02.lambda;

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
public class LambdaExample implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("Ch02 LambdaExample");
        System.out.println("Consume");
        consume(Arrays.asList(1, 2, 3, 4, 5), System.out::println);     //Method reference

        System.out.println("Filter");
        filter(Arrays.asList(1, 2, 3, 4, 5, 1, 23, 123, 23), (item) -> item > 10).forEach(System.out::println);

        System.out.println("Map");
        map(Arrays.asList("aefawf", "awfawefa", "awefaewf", "afeawf"), String::length).forEach(System.out::println);

        System.out.println("기본형 특화");
        System.out.println(Arrays.toString(filterByInt(new int[]{1, 2234, 3, 12, 321, 3213123, 1, 2311, 5}, item -> item > 40)));
    }

    /**
     * @param list     소비 할 목록
     * @param consumer 소비자
     * @param <T>      소비할 타입
     * @see java.util.function.Consumer
     */
    private <T> void consume(List<T> list, MyConsumer<T> consumer) {
        list.forEach(consumer::accept);
    }

    /**
     * @param list   filtering 할 목록
     * @param filter filtering 조건
     * @param <T>    filtering 할 목록 타입
     * @return filtering 된 결과 목록
     * @see java.util.function.Predicate
     */
    private <T> List<T> filter(List<T> list, MyFilter<T> filter) {
        List<T> result = new ArrayList<>();
        list.forEach(item -> {
            if (filter.test(item)) {
                result.add(item);
            }
        });
        return result;
    }

    /**
     * @param list     function을 적용할 대상 목록
     * @param function function
     * @param <T>      Input type
     * @param <R>      Result type
     * @return function이 적용된 R 타입 목록
     * @see java.util.function.Function
     */
    private <T, R> List<R> map(List<T> list, MyFunction<T, R> function) {
        List<R> result = new ArrayList<>();
        list.forEach(item -> {
            result.add(function.apply(item));
        });
        return result;
    }

    /**
     * 박싱과 언박싱이 일어나지 않는 filter
     *
     * @param list      int 목록
     * @param predicate 필터링 조건
     * @return 필터링 된 기본 타입 배열
     * @see java.util.function.IntPredicate
     */
    private int[] filterByInt(int[] list, IntPredicate predicate) {
        int[] temp = new int[list.length];
        int size = 0;
        for (int aList : list) {
            if (predicate.test(aList)) {
                temp[size++] = aList;
            }
        }
        if (size > 0) {
            int[] result = new int[size];
            System.arraycopy(temp, 0, result, 0, result.length);
            return result;
        } else {
            return new int[]{};
        }
    }
}

@FunctionalInterface
interface MyConsumer<T> {
    void accept(T t);
}

@FunctionalInterface
interface MyFilter<T> {
    Boolean test(T t);
}

@FunctionalInterface
interface MyFunction<T, R> {
    R apply(T t);
}

interface IntPredicate {
    boolean test(int i);
}