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
public class ManageDirectory implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void readAllRootDirectories() {
        FileSystems.getDefault().getRootDirectories()
                .forEach(System.out::println);
    }

    @ChapterLogger
    public void createDirectory() {
//        Path newDir = FileSystems.getDefault().getPath("C:/user/taesu/test");
        Path newDir = FileSystems.getDefault().getPath("C:/Users/taesu/test");

        try {
            Files.createDirectory(newDir);
        } catch (IOException e) {
            //이미 존재한다면 Exception이 발생, 중간 위치에 있는 디렉토리가 없다면 NoSearchFileException 발생
            System.out.println("디렉토리 생성 중 예외 발생 " + e.getMessage());
        }
    }

    @ChapterLogger
    public void createAllDirectories() {
        Path newDir = FileSystems.getDefault().getPath("C:/user/taesu/test");

        try {
            Files.createDirectories(newDir);
        } catch (IOException e) {
            //이미 존재한다면 건너뛴 후 다음 디렉터리로 이동
        }
    }

    @ChapterLogger
    public void searchDirectory() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("C:/"))) {
            directoryStream.forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void applyGlobPattern() {
        //Glob pattern 적용하여 필터링 [] 내부는 와일드카드 같은 것 없이 문자 그대로 매칭 됨
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("C:/"), "*.{dll,txt,sys}")) {
            directoryStream.forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void applyFilter() {
        System.out.println("Directory인 것만 골라내는 필터");
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("C:/"), entry -> Files.isDirectory(entry))) {
            directoryStream.forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n100KB 보다 큰 파일/디렉토리를 필터");
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get("C:/"), entry -> Files.size(entry) > 102400L)) {
            directoryStream.forEach(path -> System.out.println(path.getFileName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
