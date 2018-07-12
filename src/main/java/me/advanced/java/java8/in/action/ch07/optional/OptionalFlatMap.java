package me.advanced.java.java8.in.action.ch07.optional;

import me.advanced.java.java8.in.action.ch07.optional.model.Insurance;
import me.advanced.java.java8.in.action.ch07.optional.model.OptionalCar;
import me.advanced.java.java8.in.action.ch07.optional.model.OptionalPerson;
import me.advanced.java.java8.in.action.ch07.optional.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by taesu on 2018-07-12.
 */
@Service
public class OptionalFlatMap implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("Optional flatmap");
        OptionalPerson optionalPerson = new OptionalPerson();
        optionalPerson.setName("이태수");
        optionalPerson.setCar(Optional.of(new OptionalCar()));
//        optionalPerson.setCar(Optional.of(new OptionalCar(Optional.of(new Insurance("Flatmap 보험")))));


        /*
        Optional 도메인의 각 getXXX 에서 empty를 반환하는 코드가 없다면 NPE 발생
        각 도메인에선 Optional.ofNullable로 비어있는 Optional을 반환하는 것을 기본으로 해야할 듯
         */
        Optional.of(optionalPerson)
                .flatMap(OptionalPerson::getCar)
                .flatMap(OptionalCar::getInsurance)
                .ifPresent(insurance -> System.out.println(optionalPerson.getName() + "의 보험 " + insurance.getName()));

    }
}
