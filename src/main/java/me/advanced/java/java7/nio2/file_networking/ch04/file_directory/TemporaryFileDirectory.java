package me.advanced.java.java7.nio2.file_networking.ch04.file_directory;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.*;

/**
 * Created by taesu on 2018-07-31.
 */
@Service
public class TemporaryFileDirectory implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void createTempDir() {
        try {
            System.out.println("tempDir with prefix :" + Files.createTempDirectory("prefix"));

            System.out.println("tempDir without prefix :" + Files.createTempDirectory(null));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("basic temp dir is :" + System.getProperty("java.io.tmpdir"));
    }

    @ChapterLogger
    public void 종료_후크로_임시디렉토리_삭제() {
        //JVM 종로 될 때 후크가 실행 됨
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(System.getProperty("java.io.tmpdir")))) {
                directoryStream.forEach(path -> {
                    try {
                        System.out.println("임시 디렉토리 삭제 :" + path);
                        Files.delete(path);

//                        path.toFile().deleteOnExit();
                        //각 임시 파일이나 디렉터리에 대해 메모리를 소비한다
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    @ChapterLogger
    public void tempFile(){
        try {
            System.out.println("tempFile with prefix suffix :" + Files.createTempFile("prefix", "suffix"));
            //C:\Users\taesu\AppData\Local\Temp\prefix3656986791445617955suffix

            System.out.println("tempFile with prefix suffix :" + Files.createTempFile(null, null));
            //C:\Users\taesu\AppData\Local\Temp\4136485220437908814.tmp

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void deleteOnClose(){
        try(OutputStream outputStream = Files.newOutputStream(Files.createTempFile("prefix", "suffix"), StandardOpenOption.DELETE_ON_CLOSE)){
            BufferedWriter writer= new BufferedWriter(new OutputStreamWriter(outputStream));
            //do work
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
