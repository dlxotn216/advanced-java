package me.advanced.java.java8.in.action.ch09.datetime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

/**
 * Created by taesu on 2018-07-17.
 */
@Service
public class Instant_Duration_Period implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nInstant_Duration_Period");
        System.out.println("Instant");
        System.out.println(Instant.now());
        System.out.println();

        System.out.println("Duration");
        try {
            System.out.println(Duration.between(
                    LocalDate.of(2018, 7, 17),
                    LocalDate.of(2018, 7, 27)
            ));
        } catch (UnsupportedTemporalTypeException e) {
            System.out.println("Duration은 LocalDate를 기반으로 표현할 수 없다. (초와 나노초로 시간 단위를 표현하기 때문이다)");
            //LocalDate는 Period로
        }
        Duration duration = Duration.between(
                LocalDateTime.of(2018, 8, 18, 12, 0),
                LocalDateTime.of(2018, 8, 28, 16, 34));
        System.out.println(duration.toDays() + "D::" + duration.toHours() + "H::" + duration.toMinutes() + "M");


        System.out.println("Period");
        Period period = Period.between(
                LocalDate.of(2014, 7, 17),
                LocalDate.of(2018, 7, 27));
        System.out.println(period.getYears() + "Y::" + period.getMonths() + "M::" + period.getDays() + "D");


        //Compile error
//        Period period1 = Period.between(
//                LocalDateTime.of(2018, 8, 18, 12, 0),
//                LocalDateTime.of(2018, 8, 28, 16, 34));

        //Usage
        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(threeMinutes);
        System.out.println(threeMinutes2);
        System.out.println();

        Period tenDays = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        System.out.println(tenDays);
        System.out.println(threeWeeks);
    }
}
