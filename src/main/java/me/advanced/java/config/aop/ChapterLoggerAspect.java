package me.advanced.java.config.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-23
 * <p>
 * ChapterLogger 애노테이션이 붙은 메소드 실행 전후로
 * 아래와 같은 형태로 로그를 남긴다.
 * <p>
 * Start of [method name]
 * <p>
 * //proceed
 * <p>
 * End of [method name]
 */
@Aspect
public class ChapterLoggerAspect {

    @Around(value = "@annotation(me.advanced.java.config.aop.ChapterLogger)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("\n@see " + joinPoint.getSignature().getDeclaringType().getTypeName() + "#" + joinPoint.getSignature().toShortString().split("\\.")[1]);
        Object proceed = joinPoint.proceed();
        System.out.println("===========================End   of " + joinPoint.getSignature().toShortString() + "==========================\n");

        return proceed;
    }
}
