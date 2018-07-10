package me.advanced.java.java8.in.action.ch04.collector;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by taesu on 2018-07-10.
 */
@Service
public class CollectBySubGroup implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\n그룹핑 및 Counting");
        Transaction.list().stream().collect(Collectors.groupingBy(Transaction::isExpensiveTransaction, Collectors.counting()))
                .forEach((isExpensive, count) -> {
                    if (isExpensive) {
                        System.out.println("비싼 트랜잭션 수 :" + count);
                    } else {
                        System.out.println("싼 트랜잭션 수 :" + count);
                    }
                });
        System.out.println();

        System.out.println("\n그룹핑 및 최대값찾기 return Optional");
        Transaction.list().stream().collect(Collectors.groupingBy(Transaction::isExpensiveTransaction,
                                                                    Collectors.maxBy(Comparator.comparingInt(Transaction::getMoney))))
                .forEach((isExpensive, transaction) -> {
                    if(isExpensive){
                        System.out.println("비싼 트랜잭션 중 최대 트랜잭션");
                        transaction.ifPresent(System.out::println);
                    }
                    else {
                        System.out.println("싼 트랜잭션 중 최대 트랜잭션");
                        transaction.ifPresent(System.out::println);
                    }
                });
        System.out.println();

        System.out.println("\n그룹핑 및 최대값찾기 return Result");
        Transaction.list().stream().collect(Collectors.groupingBy(Transaction::isExpensiveTransaction,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Transaction::getMoney)), Optional::get)))
                .forEach((isExpensive, transaction) -> {
                    if(isExpensive){
                        System.out.println("비싼 트랜잭션 중 최대 트랜잭션");
                    }
                    else {
                        System.out.println("싼 트랜잭션 중 최대 트랜잭션");
                    }
                    System.out.println(transaction);
                });
        System.out.println();

        System.out.println("\n그룹핑 후 Mapping");
        Transaction.list().stream().collect(
                Collectors.groupingBy(Transaction::isExpensiveTransaction,
                                        Collectors.mapping(Transaction::getCity, Collectors.toSet())))
                .forEach((isExpensive, set) -> {
                    if(isExpensive){
                        System.out.println("비싼 트랜잭션인 City 집합");
                    } else {
                        System.out.println("싼 트랜잭션인 City 집합");
                    }
                    System.out.println(set.stream().collect(Collectors.joining(",")));
                });
        System.out.println();
    }
}
