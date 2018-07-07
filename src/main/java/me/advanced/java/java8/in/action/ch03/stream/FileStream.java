package me.advanced.java.java8.in.action.ch03.stream;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by taesu on 2018-07-07.
 */
@Service
public class FileStream implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("\nFileStream");

        try(Stream<String> lines = Files.lines(Paths.get(Optional.ofNullable(getClass().getClassLoader().getResource("temp/test.txt"))
                                                                    .orElseThrow(IllegalArgumentException::new).toURI()), Charset.forName("UTF-8"))){
            long numberOfApple = lines.flatMap(line -> Stream.of(line.split(" ")))
                    .filter(word -> word.equalsIgnoreCase("apple"))
                    .count();
            System.out.println("The number of apple word :"+ numberOfApple);
        } catch (IOException e){
            //
            System.out.println(e);
        }
    }
}
