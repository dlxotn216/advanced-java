package me.advanced.java.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-23
 * <p>
 * ChapterRunner 애노테이션이 붙은 메소드 실행 시
 * 해당 메소드가 존재하는 모든 메소드 중
 * <pre><code>@ChapterLogger</code></pre> 애노테이션이 붙은 메소드를 모두 실행한다.
 *
 * 사실 아래 Aspect에서 각 메소드 호출 시
 * ChapterLogger에서 하는 역할처럼 joinPoint.proceed() 호출 전후로
 * 로깅을 해주어도 구현은 가능하다.
 *
 * 하지만 분리하고 싶엇다...
 */
@Slf4j
@Aspect
public class ChapterRunnerAspect {
	@Autowired
	private ApplicationContext applicationContext;
	
	@Around(value = "@annotation(me.advanced.java.config.aop.ChapterRunner)")
	/*
		AOP가 동작하지 않는 원인이 새로운 객체를 생성하더라도
		Around로 실행하여 안되는 것이라 판단하고
		
		Around를 사용하지 않음에따라 ProceedJoinPoint -> JointPoint 변경한 코드.
		@AfterReturning(value = "@annotation(ChapterRunner)")
		public void afterReturning(JoinPoint joinPoint) throws Throwable {
		...
		
		-> Around가 문제가 아니라 Advice 상관 없이
		 method.invoke() 시 실행하는 주체가 proxy가 아닌 것이 문제였다.
	 */
	public Object afterReturning(ProceedingJoinPoint joinPoint) throws Throwable {
		final Class<?> clazz = Class.forName(joinPoint.getSignature().getDeclaringTypeName());
		Arrays.stream(clazz.getDeclaredMethods())
				.filter(method -> Arrays.stream(method.getDeclaredAnnotations()).anyMatch(annotation -> annotation instanceof ChapterLogger))
				.forEach(method -> {
							try {
								method.invoke(applicationContext.getBean(clazz));
								/*
									private 메소드에 접근 가능하도록 하려고 accessible 설정 적용
									하지만 proxy 기반 AOP가 동작하기 위해선 실행 주체가 
									CGLIB로 생성한 Proxy이면서 target mehotd는 public 이어야 한다
									
									method.setAccessible(true);
									method.invoke(clazz.newInstance());
								 */
								
							} catch (IllegalAccessException | InvocationTargetException e) {
								log.error("ChapterLogger 수행 중 에러 발생", e);
							}
						}
				);
		return joinPoint.proceed();
	}
}
