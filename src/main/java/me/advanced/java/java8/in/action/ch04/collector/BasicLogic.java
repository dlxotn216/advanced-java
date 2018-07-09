package me.advanced.java.java8.in.action.ch04.collector;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by taesu on 2018-07-09.
 */
@Service
public class BasicLogic implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        this.case1();
        this.case2();
    }

    //통화별로 트랜잭션 그룹화 및 통화별 총 합계 계산
    private void case1() {
        System.out.println("통화별로 트랜잭션 그룹화 및 통화별 총 합계 계산");
        Map<String, List<Transaction>> group = new HashMap<>();
        Transaction.list().forEach(item -> {
            if (!group.containsKey(item.getCurrency())) {
                group.put(item.getCurrency(), new ArrayList<>());
            }
            group.get(item.getCurrency()).add(item);
        });

        group.keySet().forEach(currency -> {
            Integer sum = 0;
            for (Transaction transaction : group.get(currency)) {
                sum += transaction.getMoney();
            }
            System.out.println(currency + " 합계 :" + sum);
        });

        group.forEach((s, transactions) -> {
            System.out.println("통화 " + s);
            transactions.forEach(System.out::print);
            System.out.println();
        });
        System.out.println();
    }

    //트랜잭션을 도시로 분류 후 다시 비싼것과 비싸지 않은 것으로 분류
    private void case2() {
        System.out.println("트랜잭션을 도시로 분류 후 다시 비싼것과 비싸지 않은 것으로 분류");
        Map<String, Map<Boolean, List<Transaction>>> result = new HashMap<>();
        Transaction.list().forEach(item -> {
            if (!result.containsKey(item.getCity())) {
                result.put(item.getCity(), new HashMap<>());
            }

            List<Transaction> transactions = result.get(item.getCity()).get(item.isChapTransaction());
            if (transactions == null) {
                transactions = new ArrayList<>();
                result.get(item.getCity()).put(item.isChapTransaction(), transactions);
            }
            transactions.add(item);
        });


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
    }
}
