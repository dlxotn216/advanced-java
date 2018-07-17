package me.advanced.java.java8.in.action.ch09.datetime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Created by taesu on 2018-07-17.
 */
@Service
public class ModifyDate implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nModify date");
        LocalDate date1 = LocalDate.of(2017, 7, 17);
        System.out.println(date1);
        date1 = date1.withYear(2018);
        System.out.println("withYear(2018)  -> " + date1);

        date1 = date1.withMonth(11);
        System.out.println("withMonth(11)  -> " + date1);

        date1 = date1.with(ChronoField.MONTH_OF_YEAR, 3);
        System.out.println("with(ChronoField.MONTH_OF_YEAR, 3)  -> " + date1);

        date1 = date1.plusWeeks(1);
        System.out.println("plusWeeks(1)  -> " + date1);

        date1 = date1.plus(2, ChronoUnit.YEARS);
        System.out.println("plus(2, ChronoUnit.YEARS)  -> " + date1);
        //모두다 새로운 LocalDate 반환 (Immutable)
        System.out.println();

    }
}
