package me.advanced.java.java8.in.action.ch08.future;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Created by taesu on 2018-07-16.
 */
@Service
public class CombineCompletableFuture implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nCombineCompletableFuture");
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 100 * 100);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 10 * 10);

        //합치는 여산은 단순 계산이므로 별도의 태스크(Thread)에서 실행할 필요가 없으므로 thenCombineAsync가 아님
        System.out.println(future1.thenCombine(future2, (res1, res2) -> res1 + res2).get());
        System.out.println();
    }
}
