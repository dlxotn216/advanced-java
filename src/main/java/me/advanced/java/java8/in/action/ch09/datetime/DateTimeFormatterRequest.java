package me.advanced.java.java8.in.action.ch09.datetime;

import lombok.Value;

import java.time.temporal.ChronoField;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-19
 */
@Value
public class DateTimeFormatterRequest {
	private ChronoField text;
	private String literal;
}
