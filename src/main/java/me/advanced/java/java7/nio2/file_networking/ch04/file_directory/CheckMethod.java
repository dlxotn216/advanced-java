package me.advanced.java.java7.nio2.file_networking.ch04.file_directory;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Created by taesu on 2018-07-26.
 */
@Service
public class CheckMethod implements ApplicationRunner {
    private Path path;

    public CheckMethod() {
        try {
            this.path = Paths.get(Optional.ofNullable(getClass().getClassLoader().getResource("temp/test.txt"))
                    .orElseThrow(IllegalArgumentException::new).toURI());
        } catch (URISyntaxException e) {
            this.path = null;
        }
    }

    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void checkFileOrDirectoryExist() {
        // !Files.exists() 와 Files.notExsists()는 동등하지 않으며 notExists()는 exists의 보완이 아니다.
        if (Files.exists(path)) {
            System.out.println("파일이 존재");
        }

        if (Files.notExists(path)) {
            System.out.println("파일이 존재하지 않음");
        }

        //두 if문이 동시에 실행될 수 있다.
        //존재, 미존재, 알수없음의 상태를 가지기 때문
    }

    @ChapterLogger
    public void checkAccessbility() {
        //파일 접그 전 아래 메소드를 통해 전처리하는 것이 좋다
        System.out.println("isReadable " + Files.isReadable(path));
        System.out.println("isWritable " + Files.isWritable(path));
        System.out.println("isExecutable " + Files.isExecutable(path));

        System.out.println("\n일반 파일 => 심볼링크, 디렉터리가아닌것");
        System.out.println("isRegularFile " + Files.isRegularFile(path));
    }

    @ChapterLogger
    public void isSameFile() {
        Path path2 = path.getParent().resolve("test.txt");
        try {
            System.out.println(path);
            System.out.println(path2);
            System.out.println("isSameFile ? " + Files.isSameFile(path, path2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void isHidden() {
        try {
            System.out.println(Files.isHidden(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
