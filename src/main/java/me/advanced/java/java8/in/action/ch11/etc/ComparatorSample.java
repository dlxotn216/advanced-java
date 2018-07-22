package me.advanced.java.java8.in.action.ch11.etc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by taesu on 2018-07-22.
 */
@Service
public class ComparatorSample implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nComparatorSample");

        System.out.println("\nNull First");
        List<Integer> list = Arrays.asList(1, 3, null, 5, 1, 2, 3, 10, null);
        list.sort(Comparator.nullsFirst(Integer::compareTo));
        System.out.println(list);

        System.out.println("\nNull last");
        list = Arrays.asList(1, 3, null, 5, 1, 2, 3, 10, null);
        list.sort(Comparator.nullsLast(Integer::compareTo));
        System.out.println(list);

        System.out.println("\nNull last and reversed");
        list = Arrays.asList(1, 3, null, 5, 1, 2, 3, 10, null);
        list.sort(Comparator.nullsLast(Integer::compareTo).reversed());
        System.out.println(list);

        System.out.println();
    }
}
