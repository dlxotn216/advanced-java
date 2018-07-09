package me.advanced.java.java8.in.action.ch04.collector;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Created by taesu on 2018-07-09.
 */
@Service
public class AggregateOperation implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("집계연산");
        //최대값 최소값
        Transaction.list().stream().collect(Collectors.maxBy((o1, o2) -> o1.getMoney() - o2.getMoney())).ifPresent(transaction -> System.out.println("최대 트랜잭션 :" + transaction));
        System.out.println();

        //Advanced
        Transaction.list().stream().min(Comparator.comparingInt(Transaction::getMoney)).ifPresent(transaction -> System.out.println("최소 트랜잭션 :" + transaction));
        System.out.println();

        System.out.println("총 트랜잭션 :" + Transaction.list().stream().mapToInt(Transaction::getMoney).sum());
        System.out.println();

        System.out.println("전체 도시 이름 "+Transaction.list().stream().map(Transaction::getCity).collect(Collectors.joining(", ")));
        System.out.println();

        System.out.println("범위별 그룹화");
        Transaction.list().stream().collect(Collectors.groupingBy(transaction -> {
            if(transaction.getMoney() < 3000){
                return "Low Transaction";
            }
            else if(transaction.getMoney() < 6000){
                return "Middle Transaction";
            }
            else {
                return "High Transaction";
            }
        })).forEach((level, transactions) -> {
            System.out.println("Transaction level "+ level);
            System.out.println(transactions.stream().map(Transaction::toString).collect(Collectors.joining(",")));
            System.out.println();
        });
    }
}
