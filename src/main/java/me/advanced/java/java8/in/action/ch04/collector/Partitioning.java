package me.advanced.java.java8.in.action.ch04.collector;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by taesu on 2018-07-10.
 */
@Service
public class Partitioning implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\n파티셔닝");
        Transaction.list().stream().collect(Collectors.partitioningBy(Transaction::isExpensiveTransaction))
                .forEach((isExpensive, transactions) -> {
                    if (isExpensive) {
                        System.out.println("비싼 트랜잭션");
                    } else {
                        System.out.println("싼 트랜잭션");
                    }
                    System.out.println(transactions.stream().map(Transaction::toString).collect(Collectors.joining(",")));
                });
        System.out.println();
        //groupingBy 와 비슷하지만 Predicate을 인자로 받는다
        //partitioningBy는 참, 거짓 두 요소의 스트림을 분할 후에도 유지한다

        System.out.println("\n비싼지 여부로 분할 후 currency 별로 그룹화");
        Transaction.list().stream().collect(Collectors.partitioningBy(                      //downstream으로 groupingBy 적용
                Transaction::isExpensiveTransaction, Collectors.groupingBy(Transaction::getCurrency)))
                .forEach((isExpensive, byCurrency) -> {
                    if (isExpensive) {
                        System.out.println("비싼 트랜잭션");
                    } else {
                        System.out.println("싼 트랜잭션");
                    }
                    byCurrency.forEach((currency, transactions) -> {
                        System.out.println(currency);
                        System.out.println(transactions.stream().map(Transaction::toString).collect(Collectors.joining(",")));
                        System.out.println();
                    });
                });
        System.out.println();

        System.out.println("\n분할 중첩");
        Transaction.list().stream().collect(Collectors.partitioningBy(Transaction::isExpensiveTransaction,
                Collectors.partitioningBy(transaction -> transaction.getCity().length() > 3, Collectors.counting())))
                .forEach((isExpensive, byLengthMap) -> {
                    if (isExpensive) {
                        System.out.println("비싼 트랜잭션");
                    } else {
                        System.out.println("싼 트랜잭션");
                    }
                    byLengthMap.forEach((cityNameLongerThen3, count) -> {
                        if(cityNameLongerThen3){
                            System.out.println("도시 이름이 3보다 긴 것");
                        } else {
                            System.out.println("도시 이름이 3보다 짧은 것");
                        }
                        System.out.println(count);
                        System.out.println();
                    });
                    System.out.println();
                });
        System.out.println();
    }
}
