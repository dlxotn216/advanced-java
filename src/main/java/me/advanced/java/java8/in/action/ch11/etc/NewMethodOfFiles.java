package me.advanced.java.java8.in.action.ch11.etc;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Created by taesu on 2018-07-22.
 */
@Service
public class NewMethodOfFiles implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nNewMethodOfFiles");

        System.out.println("Files.list");
        //주어진 디렉터리의 개체를 포함하는 Stream<Path> 생성,
        //재귀가 아니며 게으르게 스트림이 처리되므로 큰 디렉터리 처리시 유용하다
        Files.list(Paths.get(Optional.ofNullable(getClass().getClassLoader().getResource("temp"))
                .orElseThrow(IllegalArgumentException::new).toURI()))
                .forEach(path -> System.out.println("Files.list Result is " + path));
        /*
        Files.list
        Files.list Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1
        Files.list Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner2
        Files.list Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test.txt
        Files.list Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test2.txt
        Files.list Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test3.txt

         */

        //Files.list와 마찬가지로 주어진 디렉터리의 개체를 포함하는 Stream<Path> 생성
        //재귀적으로 실행되며 깊이 수준 ㄱ설정가능하고, 깊이우선 방식으로 탐색한다.
        System.out.println("\nFiles.walk");
        Files.walk(Paths.get(Optional.ofNullable(getClass().getClassLoader().getResource("temp"))
                .orElseThrow(IllegalArgumentException::new).toURI()), 3)
                .forEach(path -> System.out.println("Files.walk Result is " + path));

        /*
        Files.walk
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\inner1ofinnser1
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\inner1ofinnser1\target.txt
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\inner1ofinnser1\test2.txt
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\test3.txt
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner2
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner2\test2.txt
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test.txt
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test2.txt
        Files.walk Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test3.txt
         */

        System.out.println("\nFiles.find");
        //디렉터리를 재귀적으로 탐색하며 Predicate와 일치하는 개체를 찾아 Stream<Path> 생성
        Files.find(Paths.get(Optional.ofNullable(getClass().getClassLoader().getResource("temp"))
                .orElseThrow(IllegalArgumentException::new).toURI()), 3, (path, basicFileAttributes) -> {
            System.out.println("Find path ..." + path + " basic info ..." + basicFileAttributes.creationTime());
            //basicFileAttributes 객체도 제공

            if (path.endsWith("target.txt")) {
                System.out.println("Find target!");
                return true;
            } else {
                return false;
            }
        }).forEach(path -> System.out.println("Files.find Result is " + path));
        /*
        Files.find
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp basic info ...2018-07-07T02:04:47.000416Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1 basic info ...2018-07-22T12:20:50.065143Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\inner1ofinnser1 basic info ...2018-07-22T12:20:50.07114Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\inner1ofinnser1\target.txt basic info ...2018-07-22T12:23:05.991934Z
        Find target!
        Files.find Result is C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\inner1ofinnser1\target.txt
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\inner1ofinnser1\test2.txt basic info ...2018-07-22T12:20:50.079135Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner1\test3.txt basic info ...2018-07-22T12:20:50.176079Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner2 basic info ...2018-07-22T12:20:50.10512Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\inner2\test2.txt basic info ...2018-07-22T12:20:50.108118Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test.txt basic info ...2018-07-07T02:04:47.002414Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test2.txt basic info ...2018-07-22T12:17:10.17987Z
        Find path ...C:\Users\taesu\Desktop\advanced-java\target\classes\temp\test3.txt basic info ...2018-07-22T12:17:10.154885Z


         */

        System.out.println();
    }
}
