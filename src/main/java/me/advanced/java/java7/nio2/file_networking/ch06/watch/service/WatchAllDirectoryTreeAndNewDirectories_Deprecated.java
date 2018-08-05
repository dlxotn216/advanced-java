package me.advanced.java.java7.nio2.file_networking.ch06.watch.service;

import com.sun.nio.file.SensitivityWatchEventModifier;
import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by taesu on 2018-08-02.
 */
@Service
public class WatchAllDirectoryTreeAndNewDirectories_Deprecated implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    /**
     * <a href="http://fabriziofortino.github.io/articles/recursive-watchservice-java8/">참고 링크</a>
     * @throws IOException
     */
    @ChapterLogger
    public void test() throws IOException {
        final Map<WatchKey, Path> keys = new HashMap<>();
        WatchService watcher = FileSystems.getDefault().newWatchService();

        Consumer<Path> register = p -> {
            if (!p.toFile().exists() || !p.toFile().isDirectory()) {
                throw new RuntimeException("folder " + p + " does not exist or is not a directory");
            }
            try {
                Files.walkFileTree(p, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        WatchKey watchKey = dir.register(watcher, new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE}, SensitivityWatchEventModifier.HIGH);
                        keys.put(watchKey, dir);
                        System.out.println("Register dir " + dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                throw new RuntimeException("Error registering path " + p);
            }
        };

        final Path path = Paths.get("C:/user/taesu/watchallandnew");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            return;
        }

        register.accept(path);


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (true) {
                final WatchKey key;
                try {
                    key = watcher.take(); // wait for a key to be available
                } catch (InterruptedException ex) {
                    return;
                }

                final Path dir = keys.get(key);
                if (dir == null) {
                    System.err.println("WatchKey " + key + " not recognized!");
                    continue;
                }

                key.pollEvents().stream()
                        .peek(this::logging)
                        .filter(e -> (e.kind() != OVERFLOW))
                        .map(e -> ((WatchEvent<Path>) e).context())
                        .forEach(p -> {
                            final Path absPath = dir.resolve(p);
                            if (absPath.toFile().isDirectory()) {
                                register.accept(absPath);
                            } else {
                                final File f = absPath.toFile();
                            }
                        });

                boolean valid = key.reset(); // IMPORTANT: The key must be reset after processed
                if (!valid) {
//                    break;
                }
            }
        });
        this.simulate(path);
    }

    //    @ChapterLogger
    public void watchAllAndNewDirectories() {
        final Path path = Paths.get("C:/user/taesu/watchallandnew");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            return;
        }

        new Thread(() -> {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                registerPath(path, watchService);

                while (true) {
                    WatchKey watchKey = watchService.poll();

                    if (watchKey != null) {
//                        watchKey.pollEvents()
//                                .forEach(watchEvent -> {
//                                    if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
//                                        onCreated(watchEvent, watchService);
//                                    }
//                                    logging(watchEvent);
//                                });

                        watchKey.pollEvents().stream()
                                .peek(this::logging)
                                .filter(watchEvent -> (watchEvent.kind().equals(ENTRY_CREATE)
                                        || watchEvent.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)))
                                .forEach(watchEvent -> onCreated(watchEvent, watchService));
                    }

                    if (watchKey != null && !watchKey.reset()) {
                        System.out.println("Watch key is invalid " + watchKey);
                        break;
                    }
                }
                watchService.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        simulate(path);
    }

    private void onCreated(WatchEvent<?> watchEvent, WatchService watchService) {
        WatchEvent<Path> pathWatchEvent = (WatchEvent<Path>) watchEvent;
        if (Files.isDirectory(pathWatchEvent.context(), LinkOption.NOFOLLOW_LINKS)) {
            this.registerPath(pathWatchEvent.context(), watchService);
        }
    }

    private void logging(WatchEvent<?> watchEvent) {
        System.out.println("Event occurred :" + watchEvent.kind() + " [" + ((WatchEvent<Path>) watchEvent).context().getFileName() + "]");
    }

    private void registerPath(Path path, WatchService watchService) {
        FileVisitor<Path> visitor = new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                dir.register(watchService, ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
                System.out.println("Register [" + dir + "]");
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

    private void simulate(Path path) {
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

            Path sub1 = path.resolve("sub1");
            Path sub2 = path.resolve("sub2");
            Path children = sub1.resolve("children");
            Files.createDirectories(sub1);
            Files.createDirectories(sub2);
            Files.createDirectories(children);

            //디렉토리 생성 후 잠시 sleep
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n=====================================");
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
            //
        }
    }
}
