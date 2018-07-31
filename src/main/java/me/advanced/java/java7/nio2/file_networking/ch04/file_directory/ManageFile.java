package me.advanced.java.java7.nio2.file_networking.ch04.file_directory;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Created by taesu on 2018-07-31.
 */
@Service
public class ManageFile implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void createFile() {
        Path newFile = FileSystems.getDefault().getPath("C:/user/taesu/test", "createFile.txt");
        try {
            Files.createFile(newFile);
        } catch (IOException e) {
            //파일이 존재하면 FileAlreadyExistsException 발생함
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void writeSmallFileRunnGroup() {
        writeSmallFile();
        System.out.println("\n");
        writeSmallFileWithLine();
    }

    public void writeSmallFile() {
        Path newFile = FileSystems.getDefault().getPath("C:/user/taesu/test", "createFile.txt");
        try {
            Files.write(newFile, "For testing 테스트".getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeSmallFileWithLine() {
        Path newFile = FileSystems.getDefault().getPath("C:/user/taesu/test", "createFile.txt");
        List<String> lines = Arrays.asList(
                "Who am i",
                "Who am i",
                "Taesu",
                "Why so serious",
                "I am a boy",
                "You are a girl",
                "Let's dance"
        );
        try {
            Files.write(newFile, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);   //덧붙인다
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void readSmallFile() {
        Path newFile = FileSystems.getDefault().getPath("C:/user/taesu/test", "createFile.txt");
        try {
            System.out.println(new String(Files.readAllBytes(newFile), Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void readAllLines() {
        Path newFile = FileSystems.getDefault().getPath("C:/user/taesu/test", "createFile.txt");
        try{
            Files.readAllLines(newFile, Charset.forName("UTF-8"))
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
