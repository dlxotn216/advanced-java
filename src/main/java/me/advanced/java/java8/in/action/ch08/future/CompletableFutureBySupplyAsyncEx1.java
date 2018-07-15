package me.advanced.java.java8.in.action.ch08.future;

import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by taesu on 2018-07-15.
 */
@Service
public class CompletableFutureBySupplyAsyncEx1 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nCompletableFutureBySupplyAsyncEx1");

        System.out.println("run blocking call by stream");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("Blocking call by stream");
        getShopAndPricesByCallBlocking().forEach(System.out::print);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println();

        System.out.println("run blocking call by parallel stream");
        stopWatch.start("Blocking call by parallel stream");
        getShopAndPricesByCallBlockingToParallel().forEach(System.out::print);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println();

        System.out.println("run non-blocking call(Actually Blocking) by stream");
        stopWatch.start("Non-blocking(Actually Blocking)  call by stream");
        getShopAndPricesByCallNonBlocking_ButActuallyBlocking().forEach(System.out::print);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println();

        System.out.println("run non-blocking call by stream");
        stopWatch.start("Non-blocking call by stream");
        getShopAndPricesByCallNonBlocking().forEach(System.out::print);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
        System.out.println();
    }

    private List<String> getShopAndPricesByCallBlocking() {
        return Stream.of(
                new MyShop("Show1"),
                new MyShop("Best Shop"),
                new MyShop("Auction"),
                new MyShop("G-Market"),
                new MyShop("11st Street"))
                .map(myShop -> myShop.getName() + " :: " + myShop.getPrice("phone"))
                .collect(Collectors.toList());
    }

    private List<String> getShopAndPricesByCallBlockingToParallel() {
        return Arrays.asList(
                new MyShop("Show1"),
                new MyShop("Best Shop"),
                new MyShop("Auction"),
                new MyShop("G-Market"),
                new MyShop("11st Street"))
                .parallelStream()
                .map(myShop -> myShop.getName() + " :: " + myShop.getPrice("phone"))
                .collect(Collectors.toList());
    }

    private List<String> getShopAndPricesByCallNonBlocking_ButActuallyBlocking() {
        return Stream.of(
                new MyShop("Show1"),
                new MyShop("Best Shop"),
                new MyShop("Auction"),
                new MyShop("G-Market"),
                new MyShop("11st Street"))
                .map(myShop -> CompletableFuture.supplyAsync(() -> myShop.getName() + " :: " + myShop.getPrice("phone")))
                .map(CompletableFuture::join)           //개별 마다 join 하여 blocking 됨
                .collect(Collectors.toList());
    }

    private List<String> getShopAndPricesByCallNonBlocking() {
        List<CompletableFuture<String>> completableFutures = Stream.of(
                new MyShop("Show1"),
                new MyShop("Best Shop"),
                new MyShop("Auction"),
                new MyShop("G-Market"),
                new MyShop("11st Street"))
                .map(myShop -> CompletableFuture.supplyAsync(() -> myShop.getName() + " :: " + myShop.getPrice("phone")))
                .collect(Collectors.toList());

        return completableFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}

@Getter
class MyShop {
    private String name;

    public MyShop(String name) {
        this.name = name;
    }

    public Double getPrice(String product) {
        return this.calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> this.calculatePrice(product));
    }

    private Double calculatePrice(String product) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //do stub
        }
        return product.charAt(0) * 0.5;
    }
}
