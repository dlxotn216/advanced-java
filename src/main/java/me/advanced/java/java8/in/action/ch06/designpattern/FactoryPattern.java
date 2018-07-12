package me.advanced.java.java8.in.action.ch06.designpattern;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by taesu on 2018-07-12.
 */
@Service
public class FactoryPattern implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("Factory by old");
        OldLogicFactory.getLogic("B").processLogic();
        OldLogicFactory.getLogic("N").processLogic();
        System.out.println();

        System.out.println("Factory by lambda");
        LogicFactory.getLogicById("B").processLogic();
        LogicFactory.getLogicById("N").processLogic();
        try {
            LogicFactory.getLogicById("A").processLogic();
        } catch (IllegalArgumentException e) {
            System.out.println("Exception occurred");
            System.out.println(e.getMessage());
        }
        System.out.println();
    }
}

interface Logic {
    void processLogic();
}

class Business implements Logic {

    @Override
    public void processLogic() {
        System.out.println("Business logic processed");
    }
}

class Nerd implements Logic {

    @Override
    public void processLogic() {
        System.out.println("Nerd logic processed");
    }
}

class OldLogicFactory {
    public static Logic getLogic(String id) {
        if (id.equalsIgnoreCase("B")) {
            return new Business();
        } else if (id.equalsIgnoreCase("N")) {
            return new Nerd();
        } else {
            throw new IllegalArgumentException(id + " Logic does not matched any logic");
        }
    }
}

class LogicFactory {
    private static final Map<String, Supplier<Logic>> logicMap;

    static {
        Map<String, Supplier<Logic>> map = new HashMap<>();
        map.put("B", () -> Business::new);
        map.put("N", () -> Nerd::new);
        logicMap = Collections.unmodifiableMap(map);
    }

    public static Logic getLogicById(String id) {
        return Optional.ofNullable(logicMap.get(id))
                .orElseThrow(() -> new IllegalArgumentException(id + " Logic does not matched any logic"))
                .get();
    }
}
