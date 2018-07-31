package me.advanced.java.java7.nio2.file_networking.ch05.recursive_work;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

/**
 * Created by taesu on 2018-07-31.
 */
@Service
public class WalkSample implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void findGlobPatternFile() {
        FileVisitor<Path> visitor = new FileVisitor<Path>() {
            private final PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{txt,png,jpg}");

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file != null && matcher.matches(file.getFileName())) {
                    //이름으로 비교
                    System.out.println("Visit target file " + file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(Paths.get("C:"), visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void fileRecursiveRunGroup() {
        Path base = Paths.get("C:/user/taesu/test");
        try {
            Files.createDirectories(base);
            Files.write(base.resolve("A.txt"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);
            Files.write(base.resolve("B.txt"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);
            Files.write(base.resolve("C.txt"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);

            if (Files.notExists(base.resolve("D1"))) {
                Files.createDirectory(base.resolve("D1"));
            }
            Files.write(base.resolve("D1").resolve("D1_F1"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);
            Files.write(base.resolve("D1").resolve("D1_F2"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);
            Files.write(base.resolve("D1").resolve("D1_F3"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);

            if (Files.notExists(base.resolve("D2"))) {
                Files.createDirectory(base.resolve("D2"));
            }

            if (Files.notExists(base.resolve("D3"))) {
                Files.createDirectory(base.resolve("D3"));
            }
            Files.write(base.resolve("D3").resolve("D3_F1"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);
            Files.write(base.resolve("D3").resolve("D3_F2"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);
            Files.write(base.resolve("D3").resolve("D3_F3"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Copy");
        Path copiedPath = deepCopy(base);

        System.out.println("Move");
        deepMove(copiedPath);
    }

    public Path deepCopy(Path base) {
        Path copyTo = base.resolveSibling("copied");

        FileVisitor<Path> visitor = new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                try {
                    System.out.println("preVisit :" + base.relativize(dir).toString());
                    Files.copy(dir, copyTo.resolve(base.relativize(dir)), StandardCopyOption.REPLACE_EXISTING);
                    Files.setLastModifiedTime(copyTo.resolve(base.relativize(dir)), FileTime.from(Instant.now()));
                } catch (DirectoryNotEmptyException e){
                    //Ignore
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("visitFile :" + copyTo.resolve(base.relativize(file)).toString());
                Files.copy(file, copyTo.resolve(base.relativize(file)), StandardCopyOption.REPLACE_EXISTING, LinkOption.NOFOLLOW_LINKS);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(base, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return copyTo;
    }

    public void deepMove(Path base){
        Path moveTo = base.resolveSibling("moved");

        FileVisitor<Path> visitor = new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                try {
                    System.out.println("preVisit :" + base.relativize(dir).toString());
                    Files.copy(dir, moveTo.resolve(base.relativize(dir)), StandardCopyOption.REPLACE_EXISTING);
                } catch (DirectoryNotEmptyException e){
                    //Ignore
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.move(file, moveTo.resolve(base.relativize(file)), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(base, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void fileDelete() {

    }

}
