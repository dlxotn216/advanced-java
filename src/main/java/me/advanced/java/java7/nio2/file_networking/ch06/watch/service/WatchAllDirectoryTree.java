package me.advanced.java.java7.nio2.file_networking.ch06.watch.service;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by taesu on 2018-08-01.
 */
//@Service
public class WatchAllDirectoryTree implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void watchAllTree() {
        Path path = Paths.get("C:/user/taesu/watchall");
        Path sub1 = path.resolve("sub1");
        Path sub2 = path.resolve("sub2");
        Path children = sub1.resolve("children");
        try {
            Files.createDirectories(path);
            Files.createDirectories(sub1);
            Files.createDirectories(sub2);
            Files.createDirectories(children);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        new Thread(() -> {
            WatchService watchService;
            try {
                watchService = FileSystems.getDefault().newWatchService();
                registerAllSubDirectoryEvent(path, watchService);

                while (true) {
                    WatchKey watchKey = watchService.poll();

                    if(watchKey != null){
                        watchKey.pollEvents().forEach(watchEvent -> {
                            WatchEvent.Kind<?> kind = watchEvent.kind();
                            WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                            System.out.println("Event occurred :" + kind + " [" + watchEventPath.context().getFileName() + "]");
                        });
                    }

                    if (watchKey != null && !watchKey.reset()) {
                        System.out.println("Watch key is invalid " + watchKey);
                        watchService.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Files.write(path.resolve("test1.txt"), "test1".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();
            Files.write(path.resolve("test2.txt"), "test2".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();
            Files.write(path.resolve("test3.txt"), "test3".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();

            Files.write(path.resolve("test1.txt"), "test1".getBytes("UTF-8"), StandardOpenOption.APPEND);
            System.out.println();
            Files.write(path.resolve("test1.txt"), "test1".getBytes("UTF-8"), StandardOpenOption.APPEND);
            System.out.println();
            Files.write(path.resolve("test1.txt"), "test1".getBytes("UTF-8"), StandardOpenOption.APPEND);
            System.out.println();

            Files.delete(path.resolve("test1.txt"));
            System.out.println();

            Files.write(sub1.resolve("sub1-test1.txt"), "sub11".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();
            Files.write(sub1.resolve("sub1-test2.txt"), "sub112".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();
            Files.write(sub1.resolve("sub1-test3.txt"), "sub1123".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();

            Files.write(sub2.resolve("sub2-test.txt"), "sub1123".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();

            Files.write(children.resolve("child-test1.txt"), "children1".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();
            Files.write(children.resolve("child-test2.txt"), "children2".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();
            Files.write(children.resolve("child-test3.txt"), "children3333".getBytes("UTF-8"), StandardOpenOption.CREATE);
            System.out.println();

            Files.delete(sub2.resolve("sub2-test.txt"));
            Files.delete(sub2);

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void registerAllSubDirectoryEvent(Path path, WatchService watchService){
        FileVisitor<Path> visitor = new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
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
            Files.walkFileTree(path, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
