package me.advanced.java.java8.in.action.ch01.actionparam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by taesu on 2018-07-06.
 */
@SpringBootApplication(scanBasePackages = "me.advanced.java.java8.in.action.ch01.actionparam")
public class Ch01Application {

    public static void main(String[] args) {
        System.out.println("Ch01 LambdaExample");
        SpringApplication.run(Ch01Application.class);
    }
}
