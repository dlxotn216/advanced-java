package me.advanced.java.java8.in.action.ch08.future;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by taesu on 2018-07-16.
 */
@Service
public class PipeCompletableFuture implements ApplicationRunner {
    private final ShopResponseParser parser = new ShopResponseParser();
    private final DiscountService discountService = new DiscountService();
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println();
        StopWatch stopWatch = new StopWatch("PipeCompletableFuture");
        stopWatch.start();
        List<CompletableFuture<String>> completableFutures = new ShopService().findShops().stream()
                .map(innerShop -> CompletableFuture.supplyAsync(() -> innerShop.getPrice("phone"), executor))
                .map(parserFuture -> parserFuture.thenApply(parser::getPrice))
//                .map(parserFuture -> parserFuture.thenCompose(s -> CompletableFuture.supplyAsync(() -> parser.getPrice(s))))
//                .map(parserFuture -> parserFuture.thenApplyAsync(parser::getPrice))
                .map(findDiscountedPriceFuture -> findDiscountedPriceFuture.thenCompose(parsed -> CompletableFuture.supplyAsync(() -> discountService.getDiscountedPrice(parsed), executor)))
//                .map(findDiscountedPriceFuture -> findDiscountedPriceFuture.thenComposeAsync(parsed -> CompletableFuture.supplyAsync(() -> discountService.getDiscountedPrice(parsed), executor)))
                .collect(Collectors.toList());

        /*
        thenApply, thenCompose
        thenApply는 Stream.map과 비슷하다
        thenCompose는 Stream.flagMap과 비슷하다

        parsing 작업에서 별도의 지연이 없으므로(I/O) thenApply를 통해 즉시 작업을 처리한다
        discountService를 호출하는 작업에서는 지연이 존재하므로 thenCompose를 통해 작업을 처리한다

        thenApply로 parsing 할 경우
        ====================Response is -> LOTTE::76000
        ====================Response is -> IP::73000
        ====================Response is -> GS::71000
        GS's price is :63900.0
        ====================Response is -> SAMSUNG::83000
        IP's price is :65700.0
        ====================Response is -> LG::76000
        SAMSUNG's price is :33200.0
        LOTTE's price is :53200.0
        LG's price is :60800.0

        thenCompose로 parsing 할 경우
        ====================Response is -> GS::71000
        ====================Response is -> LG::76000
        GS's price is :63900.0
        ====================Response is -> LOTTE::76000
        ====================Response is -> IP::73000
        ====================Response is -> SAMSUNG::83000
        IP's price is :65700.0
        SAMSUNG's price is :33200.0
        LOTTE's price is :53200.0
        LG's price is :60800.0
         */

        /*
        xxxAsync 메소드는 이전 Task를 수행한 Thread와 별개의 Thread에서 작업을 실행함을 의미한다. (Thread pool에 있는)
        이때 할인된 가격을 조회하는 CompletableFuture Task는 이전 Task에 의존적이다 (순서성이 있다)
        따라서 Thread를 전환하는 오버헤드가 적으며 효율성이 더 좋은 thenCompose를 사용하는 것이 낫다.
         */

        completableFutures.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        System.out.println();
    }
}


class ShopService {

    private List<InnerShop> shops = Arrays.asList(
            new InnerShop("GS"),
            new InnerShop("IP"),
            new InnerShop("SAMSUNG"),
            new InnerShop("LOTTE"),
            new InnerShop("LG")
    );

    public List<InnerShop> findShops() {
        return shops;
    }


    @Getter
    @AllArgsConstructor
    static class InnerShop {
        private String name;

        public String getPrice(String product) {
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {

            }
            return name + "::" + name.charAt(0) * 1000;
        }
    }
}

@AllArgsConstructor
@Getter
class Parsed {
    private Double price;
    private String shopName;
}

class ShopResponseParser {
    public Parsed getPrice(String shopResponse) {
        System.out.println("====================Response is -> " + shopResponse);
        Double price;
        try {
            price = Double.parseDouble(shopResponse.split("::")[1]);
        } catch (Exception e) {
            price = 0d;
        }
        return new Parsed(price, shopResponse.split("::")[0]);
    }
}

class DiscountService {
    enum Discount {
        BRONZE(0.1),
        SILVER(0.2),
        GOLD(0.3),
        DIA(0.6);
        private double rate;

        Discount(double rate) {
            this.rate = rate;
        }
    }

    public String getDiscountedPrice(Parsed parsed) {
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {

        }
        Double rate;
        switch (parsed.getShopName()) {
            case "GS":
                rate = Discount.BRONZE.rate;
                break;
            case "IP":
                rate = Discount.BRONZE.rate;
                break;
            case "SAMSUNG":
                rate = Discount.DIA.rate;
                break;
            case "LOTTE":
                rate = Discount.GOLD.rate;
                break;
            case "LG":
                rate = Discount.SILVER.rate;
                break;
            default:
                rate = Discount.SILVER.rate;
        }

        return parsed.getShopName() + "'s price is :" + (parsed.getPrice() * (1 - rate));
    }
}

/*


====================Response is -> GS::71000
====================Response is -> SAMSUNG::83000
====================Response is -> IP::73000
====================Response is -> LOTTE::76000
====================Response is -> LG::76000
GS's price is :63900.0
IP's price is :65700.0
SAMSUNG's price is :33200.0
LOTTE's price is :53200.0
LG's price is :60800.0
 */