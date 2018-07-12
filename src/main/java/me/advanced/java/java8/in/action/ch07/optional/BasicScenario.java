package me.advanced.java.java8.in.action.ch07.optional;

import me.advanced.java.java8.in.action.ch07.optional.model.Car;
import me.advanced.java.java8.in.action.ch07.optional.model.Insurance;
import me.advanced.java.java8.in.action.ch07.optional.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * Created by taesu on 2018-07-12.
 */
@Service
public class BasicScenario implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //정상 동작 케이스
        Person person = new Person("Person1");
        Car car = new Car();
        car.setInsurance(new Insurance("보험1"));
        person.setCar(car);
        print(person);

        //예외 발생
        Person person1 = new Person("Person2");
        try {
            print(person1);
        } catch (NullPointerException e) {
            System.out.println("모든 사람이 차를 가지고 있진 않음");
        }

        //방어코드가 필요 -> 그에 따라 if 중첩이 늘어남
        Person anotherPerson = new Person("방어코드");
        if (anotherPerson != null) {
            Car anotherPersonCar = anotherPerson.getCar();
            if (anotherPersonCar != null) {
                Insurance anotherPersonCarInsurance = anotherPersonCar.getInsurance();
                if (anotherPersonCarInsurance != null) {
                    print(anotherPerson);
                }
            }
        }
        System.out.println();
    }

    private void print(Person person) {
        System.out.println(person.getName() + "의 보험 :" + person.getCar().getInsurance().getName());
    }
}
