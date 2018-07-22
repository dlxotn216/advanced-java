package me.advanced.java.java8.in.action.ch11.etc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by taesu on 2018-07-22.
 */
@Service
public class Concurrency implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nConcurrency");
        AtomicInteger atomicInteger = new AtomicInteger(1);
        System.out.println("Get And update " + atomicInteger.getAndUpdate(operand -> operand * 10));        //1
        System.out.println("Update and get " + atomicInteger.updateAndGet(operand -> operand * 10));        //100

        //만약 여러 스레드에서 읽기보다 갱신을 더 만ㅇ히 수행한다면
        //Atomic 클래스보다는 LongAdder, LongAccumulator, DoubleAdder, DoubleAccumulator 를 사용해야 함
        LongAdder longAdder = new LongAdder();
        longAdder.add(10);
        longAdder.add(20);

        System.out.println("Long adder sum result is " + longAdder.sum());                  //30

        LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left + right, 0);
        //여러 스레드에서 값을 더함
        longAccumulator.accumulate(10);
        longAccumulator.accumulate(10);
        longAccumulator.accumulate(10);

        System.out.println("LongAccumulator sum result is " + longAccumulator.get());       //30
        System.out.println();
    }
}
