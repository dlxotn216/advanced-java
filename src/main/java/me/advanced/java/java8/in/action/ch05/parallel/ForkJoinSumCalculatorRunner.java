package me.advanced.java.java8.in.action.ch05.parallel;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by taesu on 2018-07-11.
 */
@Service
public class ForkJoinSumCalculatorRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        long[] numbers = LongStream.rangeClosed(1, 100000000L).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        System.out.println(new ForkJoinPool().invoke(task));

        stopWatch.stop();
        System.out.println("분할계산 수행시간");
        System.out.println(stopWatch.prettyPrint());

        stopWatch.start();
        System.out.println(new SequencedSumCalculator(numbers).computeSequentially());
        stopWatch.stop();
        System.out.println("순차계산 수행시간");
        System.out.println(stopWatch.prettyPrint());
    }
}

class ForkJoinSumCalculator extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * Devide and conquer
     *
     * @return 합산 겨로가
     */
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            //더 이상 분할이 불가능 한 경우
            return computeSequentially();
        }

        //좌측 분할
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, (start + end) / 2);
        leftTask.fork();        //비동기 실행

        //우측 분할
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, (start + end) / 2, end);

        //join -> 완료될 때까지 대기(동기)
        //compute -> 동기 실행
        return leftTask.join() + rightTask.compute();
    }

    /**
     * 더 이상 분할 할 수 없는 경우 (할 필요가 없는 경우) 순차 계산한다
     *
     * @return
     */
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

class SequencedSumCalculator {
    private final long[] numbers;

    public SequencedSumCalculator(long[] numbers) {
        this.numbers = numbers;
    }

    public long computeSequentially() {
        long sum = 0;
        int end = numbers.length;
        for (int i = 0; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

