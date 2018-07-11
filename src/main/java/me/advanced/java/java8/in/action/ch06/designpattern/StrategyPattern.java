package me.advanced.java.java8.in.action.ch06.designpattern;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Created by taesu on 2018-07-11.
 */
@Service
public class StrategyPattern implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String str1 = "12132131";
        String str2 = "1213213aa1";
        System.out.println(new NumericValidator().validate(str1));      //may be bean
        System.out.println(new NumericValidator().validate(str2));
        System.out.println(ValidatorUtils.numericValidator.validate(str1));
        System.out.println(ValidatorUtils.numericValidator.validate(str2));

        String str3 = "afawefawf";
        String str4 = "AWaefawawfewaAFEW";
        System.out.println(new IsAllLowerCaseValidator().validate(str3));
        System.out.println(new IsAllLowerCaseValidator().validate(str4));
        System.out.println(ValidatorUtils.isAllLowerCaseValidator.validate(str3));
        System.out.println(ValidatorUtils.isAllLowerCaseValidator.validate(str4));
        System.out.println();

        System.out.println(Discount.BASIC.calculate(10d));
        System.out.println(Discount.HIGH.calculate(10d));
        System.out.println(DiscountOld.BASIC.calculate(10d));
        System.out.println(DiscountOld.HIGH.calculate(10d));

    }
}

interface Validator<T> {
    boolean validate(T t);
}

class NumericValidator implements Validator<String> {

    @Override
    public boolean validate(java.lang.String string) {
        return string.matches("\\d+");
    }
}

class IsAllLowerCaseValidator implements Validator<String>{

    @Override
    public boolean validate(String s) {
        return s.matches("[a-z]+");
    }
}

class ValidatorUtils {
    public static final Validator<String> numericValidator = s -> s.matches("\\d+");
    public static final Validator<String> isAllLowerCaseValidator  = s -> s.matches("[a-z]+");
}

/**
 * 할인 정책에 대해 계산 로직을 Enum에 심을 수 있다
 */
enum Discount {
    BASIC(amount -> amount * 0.9),
    HIGH(amount -> amount * 0.3);

    private final Function<Double, Double> discountCalcurlator;

    Discount(Function<Double, Double> discountCalcurlator) {
        this.discountCalcurlator = discountCalcurlator;
    }

    public Double calculate(Double amount){
        return discountCalcurlator.apply(amount);
    }
}

/**
 * Java8 이전 방식
 */
enum DiscountOld {
    BASIC{
        Double calculate(Double amount) {return amount * 0.9;}
    },
    HIGH{
        Double calculate(Double amount) {return amount * 0.3;}
    };

    abstract Double calculate(Double amount);
}