package me.advanced.java.java8.in.action.ch02.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by taesu on 2018-07-06.
 */
@SpringBootApplication(scanBasePackages = "me.advanced.java.java8.in.action.ch02.lambda")
public class Ch02Application {

    public static void main(String[] args) {
        System.out.println("Ch02 LambdaExample");
        SpringApplication.run(Ch02Application.class);
    }
}

