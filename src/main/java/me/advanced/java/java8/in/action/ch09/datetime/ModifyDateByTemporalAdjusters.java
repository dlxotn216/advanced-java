package me.advanced.java.java8.in.action.ch09.datetime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.xml.ws.ServiceMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * Created by taesu on 2018-07-17.
 */
@ServiceMode
public class ModifyDateByTemporalAdjusters implements ApplicationRunner{

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("TemporalAdjusters");
        //3월의 첫 번째 화요일, 올해의 마지막 날짜, 다음달의 마지막 날짜, 현재 이후로 지정한 요일이 처음 나타나는날 등
        //복잡한 조건에 대해 날짜를 조작할 수 있도록 도와주는 유틸리티
        LocalDate date = LocalDate.of(2017, 7, 17);
        System.out.println(date);
        System.out.println("nextOrSame  -> " + date.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)));
        System.out.println("lastDayOfMonth  -> " + date.with(TemporalAdjusters.lastDayOfMonth()));
    }
}
