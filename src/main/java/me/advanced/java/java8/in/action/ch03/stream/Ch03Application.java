package me.advanced.java.java8.in.action.ch03.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by taesu on 2018-07-06.
 */
@SpringBootApplication(scanBasePackages = "me.advanced.java.java8.in.action.ch03.stream")
public class Ch03Application {
    public static void main(String[] args) {
        System.out.println("Ch03Application");
        SpringApplication.run(Ch03Application.class);
    }
}
