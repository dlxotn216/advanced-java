package me.advanced.java.java8.in.action.ch04.collector;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by taesu on 2018-07-09.
 */
@Service
public class AdvancedLogic implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        this.case1();
        this.case2();
    }

    private void case1() {
        System.out.println("Case1 by lambda");
        Transaction.list().stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency))
                .forEach((s, transactions) -> {
                    System.out.println(s + "합계 :" + transactions.stream().mapToInt(Transaction::getMoney).sum());
                });
        System.out.println();
    }

    private void case2(){
        System.out.println("Case2 by lambda");
        Map<String, Map<Boolean, List<Transaction>>> result = new HashMap<>();
        Transaction.list().stream()
                .collect(Collectors.groupingBy(Transaction::getCity))
                .forEach((s, transactions) ->
                            result.put(s, transactions.stream().collect(Collectors.groupingBy(Transaction::isExpensiveTransaction))));

        result.forEach((s, booleanListMap) -> {
            System.out.println("도시 : " + s);
            booleanListMap.forEach((aBoolean, transactions) -> {
                if (aBoolean) {
                    System.out.println("비싼것");
                } else {
                    System.out.println("싼것");
                }
                transactions.forEach(System.out::println);
                System.out.println();
            });
            System.out.println();
        });

        System.out.println();
    }
}
