package me.advanced.java.java8.in.action.ch04.collector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taesu on 2018-07-09.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Transaction {
    private String city;
    private String currency;
    private Integer money;

    public Boolean isChapTransaction(){
        return money > 5000;
    }

    public static List<Transaction> list(){
        return Arrays.asList(
                new Transaction("Seoul", "Won", 10000),
                new Transaction("Seoul", "Won", 500),
                new Transaction("Tokyo", "En", 1000),
                new Transaction("Tokyo", "En", 1200),
                new Transaction("Osaka", "En", 12000),
                new Transaction("상하이", "위안", 23000),
                new Transaction("상하이", "위안", 43000),
                new Transaction("상하이", "위안", 4000),
                new Transaction("대구", "Won", 1004),
                new Transaction("워싱턴", "Us", 103200),
                new Transaction("아프리카", "Af", 21000),
                new Transaction("타지마할", "Taj", 240000),
                new Transaction("싱가포르", "Sing", 670000),
                new Transaction("룩셈부르크", "Ruk", 800),
                new Transaction("아르헨티나", "Arh", 70000),
                new Transaction("상파울루", "Sang", 1500)
        );
    }
}
