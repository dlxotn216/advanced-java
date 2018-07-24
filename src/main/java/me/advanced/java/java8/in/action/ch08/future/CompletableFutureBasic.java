package me.advanced.java.java8.in.action.ch08.future;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by taesu on 2018-07-15.
 */
@Service
public class CompletableFutureBasic implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("CompletableFutureBasic");
        System.out.println("find shop");
        Shop shop = new Shop();

        Future<Double> priceFuture = shop.getPriceAsync("test1");

        System.out.println("Call blocking to get Result: " + priceFuture.get(3, TimeUnit.SECONDS));
        System.out.println();
    }
}

class Shop {
    public Future<Double> getPriceAsync(String product) {
        System.out.println("getPriceAsync called '" + product + "'");
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Double price = this.calculatePrice(product);
                futurePrice.complete(price);
                System.out.println("[" + product + "] Completed price :" + price);
            } catch (Exception e){
                //When exception occurred
                futurePrice.completeExceptionally(e);
            }
        }).start();
        //Non-blocking call
        System.out.println("getPriceAsync returned");
        return futurePrice;
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
