package me.advanced.java.java8.in.action.ch10.currying;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.function.DoubleUnaryOperator;

/**
 * Created by taesu on 2018-07-19.
 * <p>
 * Currying
 * x, y를 받는 함수 f룰 한 개의 인수를 받는 g로 대체하는 기법
 */
@Service
public class CurryingSample implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        DoubleUnaryOperator convertCtoF = curriedConverter(9.0 / 5, 32);
        DoubleUnaryOperator convertUSDtoGBP = curriedConverter(.6, 0);
        DoubleUnaryOperator convertKmtToMi = curriedConverter(0.624, 0);

        System.out.println("섭씨 41도를 화씨로 " + convertCtoF.applyAsDouble(41));
        System.out.println("100USD를 GBP로 " + convertUSDtoGBP.applyAsDouble(100));
        System.out.println("1KM를 마일로 " + convertKmtToMi.applyAsDouble(1));

    }

    static DoubleUnaryOperator curriedConverter(double x, double y) {
        return (double a) -> a * x + y;
    }
}
