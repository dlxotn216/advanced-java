package me.advanced.java.java7.nio2.file_networking.ch01.path;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by taesu on 2018-07-24.
 */
@Service
public class CoparePathSample implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void comparePath() {
        Path path1 = Paths.get("C:/users/taesu/test");
        Path path2 = Paths.get("/users/taesu/test");

        System.out.println(path1);
        System.out.println(path2);
        System.out.println(path1.equals(path2));
    }

    @ChapterLogger
    public void compareByIsSampleFile() {
        Path path1 = Paths.get("C:/users/taesu/test");
        Path path2 = Paths.get("/users/taesu/test");

        System.out.println(path1);
        System.out.println(path2);
        try {
            //두 경로가 같은 파일/폴더 인지 조사할 때
            System.out.println(Files.isSameFile(path1, path2));
        } catch (IOException e) {
            System.out.println("파일이 존재하지 않음");
        }

        System.out.println();

        path1 = Paths.get("C:/users/taesu");
        path2 = Paths.get("C:/users/taesu");

        System.out.println(path1);
        System.out.println(path2);
        try {
            //두 경로가 같은 파일/폴더 인지 조사할 때
            System.out.println(Files.isSameFile(path1, path2));
        } catch (IOException e) {
            System.out.println("파일이 존재하지 않음");
        }

        System.out.println("startWith 'C:'? " + path1.startsWith("users"));
        System.out.println("endWith 'taesu'? " + path1.endsWith("taesu"));
    }
}
