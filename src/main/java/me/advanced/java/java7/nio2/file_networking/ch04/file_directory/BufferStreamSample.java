package me.advanced.java.java7.nio2.file_networking.ch04.file_directory;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Created by taesu on 2018-07-31.
 */
@Service
public class BufferStreamSample implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void bufferedWriter(){
        Path newFile = FileSystems.getDefault().getPath("C:/user/taesu/test", "createFile.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(newFile, Charset.forName("UTF-8"), StandardOpenOption.APPEND, StandardOpenOption.CREATE_NEW)){
            writer.write("");
            writer.write("from buffered writer");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void bufferedReader(){
        Path newFile = FileSystems.getDefault().getPath("C:/user/taesu/test", "createFile.txt");
        try(BufferedReader reader = Files.newBufferedReader(newFile, Charset.forName("UTF-8"))) {
            reader.lines()
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
