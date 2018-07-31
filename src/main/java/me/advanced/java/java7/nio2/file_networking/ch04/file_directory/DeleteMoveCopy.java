package me.advanced.java.java7.nio2.file_networking.ch04.file_directory;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by taesu on 2018-07-31.
 */
@Service
public class DeleteMoveCopy implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void delete() {
        Path newDir = FileSystems.getDefault().getPath("C:/Users/taesu/test");
        try {
            Files.delete(newDir);
            Files.deleteIfExists(newDir);
        } catch (IOException e) {
            //빈 디렉토리가 없다면 DirectoryNotEmptyException 발생
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void copy() {
        Path copyFrom = FileSystems.getDefault().getPath("C:/Users/taesu/test");
        try {
            Files.createDirectories(copyFrom);
            Path copyTo = copyFrom.resolveSibling("test2");
            Files.createDirectories(copyTo);

            Files.write(copyFrom.resolve("copyTest.txt"), "copy".getBytes("UTF-8"));      //copyTest.txt 생성

            //파일을 복사
            Files.copy(copyFrom.resolve("copyTest.txt"),
                    copyTo.resolve("copyTestResult.txt"),
                    StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void move(){
        Path moveFrom = FileSystems.getDefault().getPath("C:/Users/taesu/test");
        try {
            Files.createDirectories(moveFrom);
            Files.write(moveFrom.resolve("moveTest.txt"), "move".getBytes("UTF-8"));      //copyTest.txt 생성

            //Move 연산을 통해 rename
            Files.move(moveFrom.resolve("moveTest.txt"), moveFrom.resolve("moveTest.txt").resolveSibling("moveTest_2.txt"), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
