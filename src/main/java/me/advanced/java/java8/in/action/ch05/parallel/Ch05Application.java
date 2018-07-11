package me.advanced.java.java8.in.action.ch05.parallel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by taesu on 2018-07-11.
 *
 * 병렬 스트림은 항상 주의해서 사용할 것.
 * limit, findFirst 등 full scan이 필요한 부분에 대해 병렬 스트림을 사용하여 낭비하지 말 것.
 * collect 연산에서 병렬 스트림의 이점을 잃지 않도록 주의할 것.
 * 항상 측정하여 개선됨을 확인할 것.
 */
@SpringBootApplication
public class Ch05Application {
    public static void main(String[] args) {
        SpringApplication.run(Ch05Application.class);
    }
}
