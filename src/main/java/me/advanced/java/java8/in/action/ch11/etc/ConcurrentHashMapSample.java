package me.advanced.java.java8.in.action.ch11.etc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by taesu on 2018-07-22.
 * ComcurrentHashMap은 HashTable에 비해
 * 내부 자료구조에서 일부만 잠근 상태로 동시 덧셈, 갱신 작업을 수행하는 기능 제공한다.
 * 따라서 HashTable보다 빠른 속도로 읽기, 쓰기 연산이 가능하다
 */
@Service
public class ConcurrentHashMapSample implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nConcurrentHashMapSample");

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("name", "leetaesu");
        concurrentHashMap.put("address", "mapo");
        concurrentHashMap.put("email", "taesu@crscube.co.kr");
        concurrentHashMap.put("search", "target");

        concurrentHashMap.forEach((s, s2) -> System.out.println(s + "::" + s2));

        System.out.println("Search");
        //null을 반환하기 전까지 키/값 쌍에 함수를 적용
        concurrentHashMap.search(1000L, (s, s2) -> {
            System.out.println("On search " + s + "::" + s2);
            if (ObjectUtils.nullSafeEquals(s2, "target")) {
                return true;
            }
            return null;
        });

        //parallelism threshold -> 현재 맵이 한계보다 작다고 추정되면 순차 연산을 수행함
        //1이면 병렬성 최대화, Long.MAX_VALUE이면 하나의 스레드만 사용

        System.out.println();

    }
}
