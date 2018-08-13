package me.advanced.java.java7.nio2.file_networking.ch07.zip;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by taesu on 2018-08-13.
 */
@Service
public class ZipFileSystemBasicSample extends SimpleFileVisitor<Path> implements ApplicationRunner {
    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void zipToSingle() {
        Path fromPath = Paths.get("C:/user/taesu/single/from");
        try {
            Files.createDirectories(fromPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            Files.write(fromPath.resolve("test1.txt"), "Test1".getBytes("UTF-8"));
            Files.write(fromPath.resolve("test2.txt"), "멜ㄷㅁ젤".getBytes("UTF-8"));
            Files.write(fromPath.resolve("test3.txt"), "테스트2".getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> env = new HashMap<>();
        env.put("create", "true");              //자동으로 생성
        env.put("encoding", "ISO-8859-1");

        String resultPathStr = "C:/user/taesu/single/zip";
        Path resultPath = Paths.get(resultPathStr);
        try {
            Files.createDirectories(resultPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        URI uri = URI.create("jar:file:/" + resultPathStr + "/result.zip");
        try (FileSystem zipFileSystems = FileSystems.newFileSystem(uri, env)) {
            Files.copy(fromPath.resolve("test1.txt"), zipFileSystems.getPath("test1-zip.txt"), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(fromPath.resolve("test2.txt"), zipFileSystems.getPath("test2-zip.txt"), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(fromPath.resolve("test3.txt"), zipFileSystems.getPath("test3-zip.txt"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void createZipFromDirectorySystem() {
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");              //자동으로 생성
//        env.put("encoding", "ISO-8859-1");            //Unmapable 발생
//        env.put("encoding", "UTF-8");
        env.put("encoding", Charset.defaultCharset().toString());

        String resultPathStr = "C:/user/taesu/zip";
        Path resultPath = Paths.get(resultPathStr);
        try {
            Files.createDirectories(resultPath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        URI uri = URI.create("jar:file:/" + resultPathStr + "/result.7z");

        try (FileSystem zipFileSystems = FileSystems.newFileSystem(uri, env)) {
            Path zipRoot = zipFileSystems.getPath("/");
            Path from = Paths.get("C:/user/taesu/zip/target");
            Files.createDirectories(from);

            FileVisitor<Path> visitor = new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("dir is " + zipRoot.resolve(from.relativize(dir).toString()));

                    Files.createDirectories(zipRoot.resolve(from.relativize(dir).toString()));

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println(from.relativize(file).toString());
                    System.out.println("file is :" + zipRoot.resolve(from.relativize(file).toString()));
                    System.out.println("file is :" + file);
                    Files.copy(file, zipRoot.resolve(from.relativize(file).toString()), StandardCopyOption.REPLACE_EXISTING);
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

            Files.walkFileTree(from, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
