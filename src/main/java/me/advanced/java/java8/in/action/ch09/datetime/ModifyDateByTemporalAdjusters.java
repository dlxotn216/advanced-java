package me.advanced.java.java8.in.action.ch09.datetime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by taesu on 2018-07-17.
 */
@Service
public class ModifyDateByTemporalAdjusters implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("TemporalAdjusters");
        //3월의 첫 번째 화요일, 올해의 마지막 날짜, 다음달의 마지막 날짜, 현재 이후로 지정한 요일이 처음 나타나는날 등
        //복잡한 조건에 대해 날짜를 조작할 수 있도록 도와주는 유틸리티
        LocalDate date = LocalDate.of(2017, 7, 17);
        System.out.println(date);
        System.out.println("nextOrSame  -> " + date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)));
        System.out.println("lastDayOfMonth  -> " + date.with(TemporalAdjusters.lastDayOfMonth()));

        LocalDate today = LocalDate.of(2018, 7, 20);
        LocalDate nextWorkingDay = today.with(new NextWorkingDayAdjusters());
        System.out.println("nextWorkingDay is -> " + nextWorkingDay);
        System.out.println();
    }
}


class NextWorkingDayAdjustersByLocalDate implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        Temporal added = temporal.plus(1, ChronoUnit.DAYS);
        LocalDate localDate = LocalDate.from(added);

        if (localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            added = added.plus(2, ChronoUnit.DAYS);
        } else if (localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            added = added.plus(1, ChronoUnit.DAYS);
        }

        return added;
    }
}

/**
 * Custom Temporal adjuster
 */
class NextWorkingDayAdjusters implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(final Temporal temporal) {
        Temporal generallyAdded = temporal.plus(1, ChronoUnit.DAYS);
        DayOfWeek dayOfWeek = DayOfWeek.from(generallyAdded);

        if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
            generallyAdded = generallyAdded.plus(2, ChronoUnit.DAYS);
        } else if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            generallyAdded = generallyAdded.plus(1, ChronoUnit.DAYS);
        }

        return generallyAdded;
    }
}
