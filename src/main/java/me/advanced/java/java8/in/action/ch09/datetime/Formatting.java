package me.advanced.java.java8.in.action.ch09.datetime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * Created by taesu on 2018-07-18.
 */
@Service
public class Formatting implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        LocalDate date = LocalDate.of(2018, 7, 18);
        System.out.println("BASIC_ISO_DATE is " + date.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("ISO_LOCAL_DATE is " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));

        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.now());
        System.out.println("ISO_LOCAL_DATE_TIME is " + dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ISO_OFFSET_DATE_TIME is " + zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        /*
        BASIC_ISO_DATE is 20180718
        ISO_LOCAL_DATE is 2018-07-18
        ISO_LOCAL_DATE_TIME is 2018-07-18T21:12:01.268
        ISO_OFFSET_DATE_TIME is 2018-07-18T21:12:01.271+09:00
         */

        System.out.println();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1 = LocalDate.of(2018, 7, 18);
        String formatted = date1.format(dateTimeFormatter);
        LocalDate date2 = LocalDate.parse(formatted, dateTimeFormatter);

        System.out.println("Origin date is " + date1);
        System.out.println("Formatted date is " + formatted);
        System.out.println("Parsed date is " + date2);

        System.out.println("\nItalian formatter");
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        date1 = LocalDate.of(2018, 7, 18);
        formatted = date1.format(italianFormatter);
        date2 = LocalDate.parse(formatted, italianFormatter);

        System.out.println("Origin date is " + date1);
        System.out.println("Formatted date is " + formatted);
        System.out.println("Parsed date is " + date2);

        System.out.println("\nDateTimeFormatter builder");
        DateTimeFormatter dateTimeFormatter1 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral("::")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral("::")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter();
        date1 = LocalDate.of(2018, 7, 18);
        formatted = date1.format(dateTimeFormatter1);
        date2 = LocalDate.parse(formatted, dateTimeFormatter1);

        System.out.println("Origin date is " + date1);
        System.out.println("Formatted date is " + formatted);
        System.out.println("Parsed date is " + date2);

        System.out.println();

        System.out.println("\nMultiple DateTimeFormat");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy-MMM-dd]", Locale.US);
        System.out.println(LocalDate.parse("2018-09-23", formatter));
        System.out.println(LocalDate.parse("2018-Sep-23", formatter));

        formatter = DateTimeFormatter.ofPattern("yyyy-[MM-dd][MMM-dd]", Locale.KOREA);
        System.out.println(LocalDate.parse("2018-09-23", formatter));
        System.out.println(LocalDate.parse("2018-9월-23", formatter));

        formatter = DateTimeFormatter.ofPattern("yyyy-[MM-dd][MMM-dd]", Locale.US);
        System.out.println(LocalDate.parse("2018-09-23", formatter));
        System.out.println(LocalDate.parse("2018-Sep-23", formatter));

        date = LocalDate.parse("2018-09-23");
        // will result in 2018-09-232018-Sep-23
        date.format(DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy-MMM-dd]"));
        // will result in 2018-09-23Sep-23
        date.format(DateTimeFormatter.ofPattern("yyyy-[MM-dd][MMM-dd]"));
    }

}
