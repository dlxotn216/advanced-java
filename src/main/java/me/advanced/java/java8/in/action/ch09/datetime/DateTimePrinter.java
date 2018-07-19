package me.advanced.java.java8.in.action.ch09.datetime;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-19
 */
@Service
public class DateTimePrinter {
	
	/**
	 * <code>requests</code>의 목록 순서대로 DateTimeFormatting을 반영한 현재 날짜를 
	 * formatting하여 반환한다.
	 * 
	 * @see DateTimeFormatterRequest
	 * 
	 * @param requests DateTimeFormatterRequest 목록
	 * @param zoneId ZoneId string
	 * @param locale Locale
	 *                  
	 * @return formatting 된 DateTime 문자열
	 */
	public String getFormattedCurrentDate(List<DateTimeFormatterRequest> requests, String zoneId, Locale locale) {
		DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
		requests.forEach(request -> {
			if(!StringUtils.isEmpty(request.getText())) {
				builder.appendText(request.getText(), TextStyle.FULL);
			}
			if(request.getLiteral() != null) {
				builder.appendLiteral(request.getLiteral());
			}
		});
		builder.appendLiteral(" [");
		builder.appendZoneId();
		builder.appendOffsetId();
		builder.appendLiteral("]");
		return builder.toFormatter(locale).withZone(ZoneId.of(zoneId)).format(this.getCurrentZonedDateTime(zoneId));
	}
	
	/**
	 * 지정된 pattern을 적용하여 <code>zoneId</code>, <code>locale</code>이 적용된
	 * DateTime을 formatting하여 문자열로 반환
	 * 
	 * @param pattern pattern string (yyyy/MM/dd, DD는 유효하지 않은 pattern임을 주의)
	 * @param zoneId  ZoneId string
	 * @param locale Locale
	 *                  
	 * @return formatting 된 DateTime 문자열
	 */
	public String getFormattedCurrentDate(String pattern, String zoneId, Locale locale) {
		return new DateTimeFormatterBuilder().appendPattern(pattern)
				.appendLiteral(" [")
				.appendZoneId()
				.appendOffsetId()
				.appendLiteral("_")
				.appendZoneRegionId()
				.appendLiteral("_")
				.appendZoneText(TextStyle.SHORT)
				.appendLiteral("_")
				.appendZoneText(TextStyle.FULL)
				.appendLiteral("]")
				.toFormatter(locale)
				.withZone(ZoneId.of(zoneId))
				.format(this.getCurrentZonedDateTime(zoneId));
	}
	
	public String getCurrentDateByZoneId(String zoneId) {
		return this.getCurrentZonedDateTime(zoneId).toString();
	}
	
	private ZonedDateTime getCurrentZonedDateTime(String zoneId) {
		if(!StringUtils.isEmpty(zoneId) && ZoneId.getAvailableZoneIds().contains(zoneId)) {
			return ZonedDateTime.now(ZoneId.of(zoneId));
		}
		else {
			return this.getCurrentZonedDateTime();
		}
	}
	
	/**
	 * 
	 * 응답 예시)
	 *
	 * <pre> 
	 * {
	 * 	UTC+02:00 : ["Africa/Cairo, Africa/Mbabane, Europe/Brussels", "Europe/Warsaw, CET, Europe/Luxembourg"
	 * 				"Europe/Zagreb, Europe/Paris, Africa/Ceuta", "Europe/Prague, Antarctica/Troll, Africa/Gaborone"]
	 * }
	 * </pre>
	 * 
	 * 응답을 아래와 같은 형식으로 출력 할 수 있음.
	 * refs) DateTimePrinterTest#지원되는_타임존_오프셋별로_분류_테스트 참조
	 * 
	 * (UTC+02:00) Europe/Warsaw, CET, Europe/Luxembourg
	 * (UTC+02:00) Europe/Zagreb, Europe/Paris, Africa/Ceuta		 
	 * (UTC+02:00) Europe/Prague, Antarctica/Troll, Africa/Gaborone
	 * 
	 * @return OffsetId 별 ZoneId 문자열(3개씩 ,로 Join 된) 목록
	 */
	public List<Map<String, List<String>>> getZoneIdssByOffsetId() {
		final Instant current = getCurrentZonedDateTime().toInstant();
		
		//현재 시간 기준의 Offset에는 DST가 적용되어있다.
		List<Map.Entry<ZoneOffset, List<ZoneId>>> zonIdsByOffset = ZoneId.getAvailableZoneIds().stream()
				.map(ZoneId::of)
				.collect(Collectors.groupingBy(zoneId -> zoneId.getRules().getOffset(current)))
				.entrySet()
				.stream()
				.sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
				.collect(Collectors.toList());//<ZoneOffset, List<ZoneId>>
		
		return zonIdsByOffset.stream()
				.map(zoneOffsetListEntry -> {
					List<String> zoneIdStrings = Stream.of(zoneOffsetListEntry.getValue())
							.flatMap(zoneIds -> partition(zoneIds, 3).stream())
							.map(zoneIds -> zoneIds.stream().map(ZoneId::getId).collect(Collectors.joining(", ")))
							.collect(Collectors.toList());
					
					Map<String, List<String>> map = new HashMap<>();
					map.put("UTC"+zoneOffsetListEntry.getKey().getRules().getOffset(current).getId().replaceAll("Z", "+00:00"), zoneIdStrings);
					return map;
				})        //3개씩 끊은 ZoneId를 다시 String으로 join하여 Map으로 mapping
				.collect(Collectors.toList());
		
	}
	
	private ZonedDateTime getCurrentZonedDateTime() {
		return ZonedDateTime.now(ZoneId.from(ZoneOffset.UTC));
	}
	
	private static <T> Collection<List<T>> partition(List<T> list, int size) {
		final AtomicInteger counter = new AtomicInteger(0);
		
		return list.stream()
				.collect(Collectors.groupingBy(it -> counter.getAndIncrement() / size))
				.values();
	}
}
