package me.advanced.java.java7.nio2.file_networking.ch06.watch.service;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by taesu on 2018-08-01.
 */
@Service
public class WatchServiceSample implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void addWatcher() {
        Path path = Paths.get("C:/user/taesu/watcher");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
                path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

                while (true) {
                    WatchKey watchKey = watchService.poll();
                    //즉시 가져온다 (없으면 null)
                    if (watchKey != null) {
                        watchKey.pollEvents().forEach(watchEvent -> {
                            WatchEvent.Kind<?> kind = watchEvent.kind();
                            System.out.println(Thread.currentThread().getName() + "] Watch event kind is :" + kind);
                            WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                            System.out.println("fileName is " + watchEventPath.context().getFileName());
                        });
                    }
//                    watchService.take();
                    //Event가 있을때까지 blocking 된다.

                    if (watchKey != null && !watchKey.reset()) {
                        //디렉토리가 삭제된 것과 같이 더이상 키가 유효하지 않은 경우
                        System.out.println("더이상 WatchKey는 유효하지 않으므로 WatchService shut down");
                        break;
                    }
                }
            } catch (IOException e) {
                //
            }
        }).start();

        try {
            Thread.sleep(3000L);
            Files.write(path.resolve("for_watchService.txt"), "test".getBytes("UTF-8"), StandardOpenOption.CREATE);     //CREATE, MODIFY 동시 발생
            Files.write(path.resolve("for_watchService.txt"), "test_for_change".getBytes("UTF-8"), StandardOpenOption.APPEND);
            Files.delete(path.resolve("for_watchService.txt"));
            Files.delete(path);
        } catch (IOException e) {
            //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
