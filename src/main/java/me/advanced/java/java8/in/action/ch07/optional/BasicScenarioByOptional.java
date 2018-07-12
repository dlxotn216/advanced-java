package me.advanced.java.java8.in.action.ch07.optional;

import me.advanced.java.java8.in.action.ch07.optional.model.Car;
import me.advanced.java.java8.in.action.ch07.optional.model.Insurance;
import me.advanced.java.java8.in.action.ch07.optional.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by taesu on 2018-07-12.
 */
@Service
public class BasicScenarioByOptional implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("By Optional");
        Person person = new Person("Person1");
        Car car = new Car();
        car.setInsurance(new Insurance("보험1"));
        person.setCar(car);
        print(person);

        Person person1 = new Person("Person2");
        print(person1);
        System.out.println();
    }

    private void print(Person person) {
        Optional.ofNullable(person)
                .ifPresent(p -> Optional.ofNullable(p.getCar())
                        .ifPresent(car -> Optional.ofNullable(car.getInsurance())
                                .ifPresent(insurance -> System.out.println(person.getName() + "의 보험 :" + person.getCar().getInsurance().getName()))));
    }
}
