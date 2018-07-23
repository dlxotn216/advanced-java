package me.advanced.java.config.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-23
 * 
 * 대상 메소드는 반드시 public이어야 한다.
 * 
 * @see ChapterRunnerAspect
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({
		ElementType.METHOD
})
public @interface ChapterRunner {
}
