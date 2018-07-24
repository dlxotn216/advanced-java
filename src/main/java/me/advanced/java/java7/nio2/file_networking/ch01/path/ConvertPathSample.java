package me.advanced.java.java7.nio2.file_networking.ch01.path;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by taesu on 2018-07-24.
 */
@Service
public class ConvertPathSample implements ApplicationRunner {
    private Path path = Paths.get("C:/users/taesu/desktop");

    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void convertToString() {
        System.out.println(path.toString());
    }

    @ChapterLogger
    public void toURI() {
        System.out.println(path.toUri());
    }

    @ChapterLogger
    public void relativePathToAbstractPath() {
        Path path = Paths.get("users/taesu/desktop");
        System.out.println("relative path " + path);
        System.out.println("abstract path " + path.toAbsolutePath());
    }

    @ChapterLogger
    public void toRealPath() {
        try {
            //toAbsolutePath와 다르게 실제 파일을 가져온다
            //존재하지 않으면 IOException 발생
            System.out.println(path.toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            System.out.println("파일이 존재하지 않음");
        }
    }

    @ChapterLogger
    public void toFile() {
        System.out.println(path.toFile());
        System.out.println(path.toFile().exists());
        System.out.println(path.toFile().isDirectory());
    }

    @ChapterLogger
    public void 조합하기() {
        Path path1 = Paths.get("C:/users/taesu");
        Path path2 = Paths.get("data.txt");

        System.out.println(path1.resolve(path2));
        System.out.println(path1.resolve("bmp.dat"));

        Path targetPath = Paths.get("C:/users/taesu/data.txt");
        System.out.println("Target file is " + targetPath);
        System.out.println("동일위치의 형제 찾기 " + targetPath.resolveSibling("brohter.txt"));
    }

    @ChapterLogger
    public void 두_위치_사이_경로(){
        Path path1 = Paths.get("C:/users/taesu");
        Path path2 = path1.resolve("data.txt");

        System.out.println(path1.getRoot().relativize(path2));
    }

}
