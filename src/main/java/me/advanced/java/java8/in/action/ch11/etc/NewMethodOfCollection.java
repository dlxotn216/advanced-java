package me.advanced.java.java8.in.action.ch11.etc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-20
 */
@Service
public class NewMethodOfCollection implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		System.out.println("\nNewMethodOfCollection");
		Predicate<Integer> isEven = (i) -> i % 2 == 0;
		List<Integer> list = IntStream.rangeClosed(0, 100)
				.boxed()
				.collect(Collectors.toList());
		System.out.println("Origin");
		System.out.println(list.stream()
				.map(Object::toString)
				.collect(Collectors.joining(",")));
		
		//Predicate에 일치하는 요소를 삭제한다
		System.out.println("\nRemove if even");
		list.removeIf(isEven);
		System.out.println(list.stream()
				.map(Object::toString)
				.collect(Collectors.joining(",")));
		
		System.out.println("\nReplace all");
		list.replaceAll(integer -> integer + 1);
		System.out.println(list.stream()
				.map(Object::toString)
				.collect(Collectors.joining(",")));
		
		//Collections.sort를 적용할 필요가 없어짐
		System.out.println("\nList.sort");
		list.sort((o1, o2) -> o2 - o1);
		System.out.println(list.stream()
				.map(Object::toString)
				.collect(Collectors.joining(",")));
	}
}
