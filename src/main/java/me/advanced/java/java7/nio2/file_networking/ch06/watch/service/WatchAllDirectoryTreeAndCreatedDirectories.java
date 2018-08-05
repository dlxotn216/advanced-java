package me.advanced.java.java7.nio2.file_networking.ch06.watch.service;

import com.sun.nio.file.SensitivityWatchEventModifier;
import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by taesu on 2018-08-05.
 */
@Service
public class WatchAllDirectoryTreeAndCreatedDirectories implements ApplicationRunner {
    private Map<WatchKey, Path> watchKeyPathMap = new HashMap<>();
    private WatchService watchService;
    private Path base = Paths.get("C:/user/taesu/watchallandnewcompleted");

    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(this.base);
        this.watchService = FileSystems.getDefault().newWatchService();

        Files.walkFileTree(this.base, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerPath(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void registerPath(Path path) {
        if(!Files.isDirectory(path)){
            return;
        }
        try {
            watchKeyPathMap.put(path.register(watchService, new WatchEvent.Kind[]{ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE}, SensitivityWatchEventModifier.HIGH), path);
            System.out.println("Path "+ path + " was registered");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void detectAll() {
        CompletableFuture.runAsync(() -> {
            while(true){
                try {
                    WatchKey key = this.watchService.take();

                    watchKeyPathMap.computeIfPresent(key, (watchKey, directoryPath) -> {
                        watchKey.pollEvents().stream()
                                .filter(watchEvent -> watchEvent.kind() != OVERFLOW)
                                .map(watchEvent -> ((WatchEvent<Path>) watchEvent).context())
                                .forEach(path -> registerPath(directoryPath.resolve(path)));

                        return this.watchKeyPathMap.get(watchKey);
                    });

                    if(!key.reset()){
                        if(this.watchKeyPathMap.isEmpty()){
                            System.out.println("유효한 키가 없으므로 루프 종료");
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, Executors.newSingleThreadExecutor());
    }

    @PreDestroy
    public void onDestroy(){
        try {
            watchService.close();
            System.out.println("WatchService was closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
