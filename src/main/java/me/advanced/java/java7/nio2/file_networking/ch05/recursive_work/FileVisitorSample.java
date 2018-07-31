package me.advanced.java.java7.nio2.file_networking.ch05.recursive_work;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by taesu on 2018-07-31.
 */
@Service
public class FileVisitorSample implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void visitResultEnums() {
        System.out.println(Arrays.toString(FileVisitResult.values()));
    }

    @ChapterLogger
    public void visitFile() {
        FileVisitor<Path> visitor = new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("preVisitDirectory :"+ dir);
                //CONTINUE -> 디렉토리 포함 하위 방문
                //SKIP_SUBTREE -> 디렉토리 포함 하위 방문하지 않음 (preVisitDirectory 메소드에서 반환해야만 의미가 있다)
                //SKIP_SIBLINGS -> 방문 중인 파일 또는 디렉토리의 형제 방문하지 않음
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("visitFile :"+ file);
                //실제 파일을 방문
                //CONTINUE -> 계속 순회
                //TERMINATE -> 순회 종료
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("visitFileFailed :"+ file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("postVisitDirectory :"+ dir);
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(Paths.get("C:"), EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
