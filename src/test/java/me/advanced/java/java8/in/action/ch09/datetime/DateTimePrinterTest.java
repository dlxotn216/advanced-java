package me.advanced.java.java8.in.action.ch09.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by Lee Tae Su on 2018-07-19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DateTimePrinterTest {
	
	@Autowired
	private DateTimePrinter dateTimePrinter;
	
	/**
	 * @link {}https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#patterns}
	 * DD는 유효하지 않은 pattern임을 주의
	 * <p>
	 * 모든 ZoneId 별로 모든 Locale을 적용하여 출력
	 * <code><pre>
	 *     ZoneId.getAvailableZoneIds().forEach(zone -> {
	 * 			log.info("Zone Id [" + zone + "]");
	 * 				Arrays.stream(Locale.getAvailableLocales())
	 * 				.forEach(locale -> {
	 * 					String temp = dateTimePrinter.getFormattedCurrentDate("MM/dd/yyyy hh:mm:ssa zZ", zone, locale);
	 * 					log.info(temp);
	 * 				});
	 * 				log.info("");
	 * 			});
	 * </pre></code>
	 */
	@Test
	public void getCurrentDateByFormattingTest() {
		String formatted = dateTimePrinter.getFormattedCurrentDate(Arrays.asList(
				new DateTimeFormatterRequest(ChronoField.YEAR, "-"),
				new DateTimeFormatterRequest(ChronoField.MONTH_OF_YEAR, "/"),
				new DateTimeFormatterRequest(ChronoField.DAY_OF_MONTH, " ("),
				new DateTimeFormatterRequest(ChronoField.DAY_OF_YEAR, "d) "),
				new DateTimeFormatterRequest(ChronoField.HOUR_OF_AMPM, ":"),
				new DateTimeFormatterRequest(ChronoField.MINUTE_OF_HOUR, ":"),
				new DateTimeFormatterRequest(ChronoField.SECOND_OF_MINUTE, " "),
				new DateTimeFormatterRequest(ChronoField.AMPM_OF_DAY, "")
		), "Asia/Seoul", Locale.KOREA);
		
		log.info(formatted);
		
		formatted = dateTimePrinter.getFormattedCurrentDate("MM/dd/yyyy HH:mm:ssa zZ", "Asia/Seoul", Locale.KOREA);
		log.info(formatted);
		
		//z -> Zone ID, Z -> Offset ID
		formatted = dateTimePrinter.getFormattedCurrentDate("MMM/dd/yyyy hh:mm:ssa zZ", "UTC", Locale.KOREA);
		log.info(formatted);
		
		formatted = dateTimePrinter.getFormattedCurrentDate("MMM/dd/yyyy hh:mm:ssa zZ", "UTC", Locale.US);
		log.info(formatted);
		
	}
	
	@Test
	public void 지원하지_않는_ZoneId를_요청했을때() {
		ZoneId.getAvailableZoneIds().forEach(zoneId
				-> log.info("ZoneId {} -> {}", zoneId, dateTimePrinter.getCurrentDateByZoneId(zoneId)));
	}
	
	@Test
	public void 지원되는_타임존_오프셋별로_분류_테스트() {
		dateTimePrinter.getZoneIdssByOffsetId()
				.forEach(zoneIdsByOffsetMap -> {
					zoneIdsByOffsetMap.forEach((offsetId, zoneIdStrings) -> {
						zoneIdStrings.forEach(zoneIdString -> {
							log.info("({}) {}", offsetId, zoneIdString);
						});
					});
				});
	}
	
	
	@Test
	public void DST_테스트() {
		final String targetZoneId = "America/Santiago";
		
		ZonedDateTime nonDstTime = ZonedDateTime.of(LocalDateTime.parse("2018-05-11T01:30:00"), ZoneId.of(targetZoneId));
		log.info("Non-dst time {}", nonDstTime);
		log.info("Non-dst time's offset is : {}", nonDstTime.getZone().getRules().getOffset(nonDstTime.toLocalDateTime()));
		
		ZonedDateTime dstTime = ZonedDateTime.of(LocalDateTime.parse("2018-05-13T01:30:00"), ZoneId.of(targetZoneId));
		log.info("Dst time {}", dstTime);
		log.info("Dst time's offset is : {}", dstTime.getZone().getRules().getOffset(dstTime.toLocalDateTime()));
		
		log.info("");
		
		/* 7일 뒤 회의를 잡는다면? (7일 이전에 DST가 존재)*/
		log.info("Today is {}", nonDstTime);
		ZonedDateTime nextMeeting = nonDstTime.plus(7, ChronoUnit.DAYS);
		log.info("When is nextMeeting [by ChronoUnit] :{}", nextMeeting);    // When is nextMeeting [by ChronoUnit] :2018-05-18T01:30-04:00[America/Santiago]
		
		nextMeeting = nonDstTime.plus(Period.ofDays(7));
		log.info("When is nextMeeting [by Period] :{}", nextMeeting);        // When is nextMeeting [by Period] :2018-05-18T01:30-04:00[America/Santiago]
		
		//Duration으로 계산할 경우엔 DST가 적용되지 않는다.
		nextMeeting = nonDstTime.plus(Duration.ofDays(7));
		log.info("When is nextMeeting [by Duration] :{}", nextMeeting);      // When is nextMeeting [by Duration] :2018-05-18T00:30-04:00[America/Santiago]
	}
	
	@Test
	public void ZoneOffsetTest() {
		String offsetString = "-04:00";
		log.info(ZoneOffset.of(offsetString).getId());
		
		offsetString = "UTC-09:00";
		offsetString = offsetString.replaceAll("UTC", "");
		log.info(ZoneOffset.of(offsetString).getId());    //-09:00
		
		offsetString = "UTC-00:00";
		offsetString = offsetString.replaceAll("UTC", "");
		log.info(ZoneOffset.of(offsetString).getId());    //Z
	}
	
	@Test
	public void ZonedDateTimeByOffsetStringTest() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneOffset.of("-09:00"));
		log.info("{}", zonedDateTime);
		
		zonedDateTime = ZonedDateTime.now(ZoneOffset.of("Z"));
		log.info("{}", zonedDateTime);
		
		try {
			zonedDateTime = ZonedDateTime.now(ZoneOffset.of("UTC-09:00"));
			log.info("{}", zonedDateTime);
		} catch (Exception e) {
			log.info("ZoneOffset으론 적용 안됨");
		}
	}
	
	@Test
	public void ZonedDateTimeByZoneIdTest() {
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC-09:00"));
		log.info("{}", zonedDateTime);
		
		zonedDateTime = ZonedDateTime.now(ZoneId.of("UTC+00:00"));
		log.info("{}", zonedDateTime);
		
		try {
			zonedDateTime = ZonedDateTime.now(ZoneId.of("UTCZ"));
			log.info("{}", zonedDateTime);
		} catch (Exception e) {
			log.info("UTCZ는 적용 안됨");
		}
		
		//UTC+10:00
		//Antarctica/DumontDUrville, Australia/Sydney, Australia/Brisbane
		zonedDateTime = ZonedDateTime.now(ZoneId.of("Australia/Brisbane"));
		log.info("{}", zonedDateTime);
	}
}