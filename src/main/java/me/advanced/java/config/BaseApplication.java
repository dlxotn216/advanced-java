package me.advanced.java.config;

import me.advanced.java.config.aop.ChapterLoggerAspect;
import me.advanced.java.config.aop.ChapterRunnerAspect;
import org.springframework.context.annotation.Bean;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-23
 */
public class BaseApplication {
	@Bean
	public ChapterLoggerAspect chapterLoggerAspect() {
		return new ChapterLoggerAspect();
	}
	
	@Bean
	public ChapterRunnerAspect chapterRunnerAspect(){
		return new ChapterRunnerAspect();
	}
}
