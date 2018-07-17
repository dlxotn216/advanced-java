package me.advanced.java.java8.in.action.ch09.datetime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * Created by taesu on 2018-07-17.
 */
@Service
public class LocalDate_LocalTime_LocalDateTime implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nLocalDateTime");
        LocalDate date = LocalDate.of(2018, 7, 17);
        System.out.println(date.toString());
        System.out.println("Year is :" + date.getYear());
        System.out.println("Month is " + date.getMonth());
        System.out.println("day of month is :" + date.getDayOfMonth());
        System.out.println("day of week is :" + date.getDayOfWeek());
        System.out.println("day of year is :" + date.getDayOfYear());
        System.out.println("length of month :" + date.lengthOfMonth());
        System.out.println("length of year :" + date.lengthOfYear());
        /*
        2018-07-17
        Year is :2018
        Month is JULY
        day of month is :17
        day of week is :TUESDAY
        day of year is :198
        length of month :31
        length of year :365
         */

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
        System.out.println("Week of year :" + weekNumber);
        System.out.println();

        LocalTime time = LocalTime.of(12, 34, 12);
        System.out.println(time);

        LocalDateTime dateTime = LocalDateTime.of(2017, 7, 17, 12, 45, 13);
        LocalDateTime dateTime1 = LocalDateTime.of(date, time);
        LocalDateTime dateTime2 = date.atTime(time);
        LocalDateTime dateTime3 = date.atTime(12, 3, 4);
        LocalDateTime dateTime4 = time.atDate(date);
        //다양한 생성 방법

    }
}
