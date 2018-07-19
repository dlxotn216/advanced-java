package me.advanced.java.java8.in.action.ch11.etc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by taesu on 2018-07-19.
 */
@Service
public class NewMethodOfMap implements ApplicationRunner {
    private Map<String, String> map = new HashMap<>();

    public NewMethodOfMap() {
        map.put("age", "12");
        map.put("addr", "afeaf");
        map.put("cookie", "degogogo");
        map.put("post", "Post");
        map.put("aop", "ukafe");
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nNewMethodOfMap");
        System.out.println("getOrDefault " + map.getOrDefault("userName", "undefined"));

        System.out.println("\nforeach");
        map.forEach((s, object) -> {
            System.out.println("key is " + s);
            System.out.println("value is " + object);
        });
        System.out.println();

        System.out.println("\ncompute");
        System.out.println(compute());
        System.out.println(map);
        System.out.println("result: " + compute());
        System.out.println(map);

        System.out.println("\ncompute if absent");
        System.out.println("result : " + getForComputeIfAbsent());
        System.out.println(map);

        System.out.println("\nmerge");
        System.out.println("result :" + map.merge("mergeKey", "init", (s, s2) -> s + s2));
        System.out.println(map);
        System.out.println("result :" + map.merge("mergeKey", "+plus", (s, s2) -> s + s2));
        System.out.println(map);

        System.out.println("\bput if absent");
        System.out.println("result : " + map.putIfAbsent("pubIfAbsent", "오버헤드가 큰 것"));       //null 반환 됨
        System.out.println(map);
        System.out.println("result : " + map.putIfAbsent("pubIfAbsent", "오버헤드가 큰 것"));       //어쨌든 생성함(이미 존재하더라도, 반면 compute if absent는 존재하지 않는 경우에만 mapping function을 통해 받음
        System.out.println(map);
        System.out.println();

        map.put("keyforRem1", "111");
        map.remove("keyforRem1");
        System.out.println("remove by key keyforRem1");
        System.out.println(map);

        map.put("keyforRem1", "111");
        map.remove("keyforRem1", "123");
        System.out.println("remove by key keyforRem1, value 123");
        System.out.println(map);

        map.put("keyforRem1", "111");
        map.remove("keyforRem1", "111");
        System.out.println("remove by key keyforRem1, value 111");
        System.out.println(map);

        System.out.println();

        map.put("replace", "replacc");
        System.out.println(map);

        map.replace("replace", "replace");
        System.out.println("after replace");
        System.out.println(map);
        System.out.println();

        map.put("replace", "replacc");
        System.out.println(map);

        //oldvalue가 일치하는 것만 대체
        map.replace("replace", "replace", "newReplace");
        System.out.println("after replace (with oldvalue)");
        System.out.println(map);

        System.out.println("\nReplace all");
        map.replaceAll((key, oldValue) -> key + "::" + oldValue);
        System.out.println(map);
    }

    public Object compute() {
        return map.compute("compute", (s, object) -> object == null ? " emptyForCompute" : object.trim());
    }

    private String getForComputeIfAbsent() {
        return map.computeIfAbsent("computeIfAbsent", this::getData);
    }

    private String getData(String url) {
        return url + " computed if absent data";
    }
}
