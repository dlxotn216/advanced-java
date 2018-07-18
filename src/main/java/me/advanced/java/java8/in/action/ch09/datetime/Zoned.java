package me.advanced.java.java8.in.action.ch09.datetime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Created by taesu on 2018-07-18.
 */
@Service
public class Zoned implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nZoned");
        LocalDate date = LocalDate.of(2018, 7, 18);
        ZonedDateTime zonedDateTime = date.atStartOfDay(ZoneId.of("UTC"));
        System.out.println("date is " + date);
        System.out.println("zoned date is " + zonedDateTime);

        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.now());
        ZonedDateTime zonedDateTime1 = dateTime.atZone(ZoneId.of("UTC"));
        System.out.println("datetime is :" + dateTime);
        System.out.println("zoned date is " + zonedDateTime1);

        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.YEAR)
                .appendLiteral("/")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("/")
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(" ")
                .appendText(ChronoField.HOUR_OF_DAY)
                .appendLiteral(":")
                .appendText(ChronoField.MINUTE_OF_HOUR)
                .appendLiteral(":")
                .appendText(ChronoField.SECOND_OF_MINUTE)
                .appendLiteral(".")
                .appendText(ChronoField.MILLI_OF_SECOND)
                .appendZoneId()
                .toFormatter(Locale.ENGLISH);

        String formatted = zonedDateTime1.format(dateTimeFormatter);
        System.out.println("formatted zoned date time is " + formatted);

        System.out.println();
    }
}
