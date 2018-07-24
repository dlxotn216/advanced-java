package me.advanced.java.java7.nio2.file_networking.ch01.path;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by taesu on 2018-07-24.
 */
@Service
public class PathInfoSample implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void getFileOrDirectoryName() {
        Path path1 = Paths.get("C:/users/taesu/desktop");
        System.out.println("file or directory name is " + path1.getFileName());

        path1 = Paths.get("C:\\Users\\taesu\\Downloads/expcust.dmp").normalize();
        System.out.println("file or directory name is " + path1.getFileName());
    }

    @ChapterLogger
    public void getRoot(){
        System.out.println(Paths.get("C:/users/taesu/desktop").getRoot());
    }

    @ChapterLogger
    public void getParent(){
        System.out.println(Paths.get("C:/users/taesu/desktop").getParent());
    }

    @ChapterLogger
    public void getSubPaths(){
        System.out.println("각 요소 출력");
        Paths.get("C:/users/taesu/desktop").forEach(System.out::println);

        System.out.println("\nsubPath");
        System.out.println(Paths.get("C:/users/taesu/desktop").subpath(0, 2));
    }
}
