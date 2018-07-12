package me.advanced.java.java8.in.action.ch06.designpattern;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by taesu on 2018-07-12.
 */
@Service
public class ObserverPattern implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("Observer pattern");
        Subject subject1 = new Subject();
        subject1.addObserver(new News("AAA"));
        subject1.addObserver(new News("ABC"));
        subject1.addObserver(new Media("NHN"));
        subject1.addObserver(new Media("BBC"));
        subject1.addObserver(new Media("TTD"));
        subject1.notifyToAllObservers("wake");
        System.out.println();

        System.out.println("Observer pattern by lambda");
        Subject subject2 = new Subject();
        subject2.addObserver((message -> System.out.println("Lambda news " + message)));
        subject2.addObserver((message -> System.out.println("Rambda news " + message)));
        subject2.addObserver((message -> System.out.println("ambda news " + message)));
        subject2.addObserver((message -> System.out.println("mbda news " + message)));
        subject2.notifyToAllObservers("Hello lambda with observer");
        System.out.println();
    }
}

interface Observer {
    void notify(String message);
}

class News implements Observer {
    private String name;

    public News(String name) {
        this.name = name;
    }

    @Override
    public void notify(String message) {
        System.out.println(name + "News get message :" + message);
    }
}

class Media implements Observer {
    private String name;

    public Media(String name) {
        this.name = name;
    }

    @Override
    public void notify(String message) {
        System.out.println(name + "Media get message " + message);
    }
}

class Subject {
    private List<Observer> observers = new ArrayList<>();

    void addObserver(Observer observer){
        this.observers.add(observer);
    }

    void notifyToAllObservers(String message){
        this.observers.forEach(observer -> observer.notify(message));
    }
}
