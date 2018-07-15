package me.advanced.java.java8.in.action.ch08.future;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Created by taesu on 2018-07-15.
 */
@Service
public class FutureSample implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("FutureSample ");
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(1000);
                System.out.println("Do Something long term task");
                return new Object();
            }
        });

        System.out.println("Do Something other task");
        try {
            System.out.println("future.get() wait 3 seconds maximum: " + future.get(3, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            //When timeout (3 seconds)
        }
        System.out.println("After get()\n");
    }
}
