package me.advanced.java.java7.nio2.file_networking.ch02.meta_attributes;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Optional;

/**
 * Created by taesu on 2018-07-26.
 */
@Service
public class UserDefiedFileAttributeView implements ApplicationRunner {
    private Path path;

    public UserDefiedFileAttributeView() {
        try {
            this.path = Paths.get(Optional.ofNullable(getClass().getClassLoader().getResource("temp/test.txt"))
                    .orElseThrow(IllegalArgumentException::new).toURI());
        } catch (URISyntaxException e) {
            this.path = null;
        }
    }

    @ChapterRunner
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

    }

    @ChapterLogger
    public void defineAttribute() {
        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);

        try {
            int write = userDefinedFileAttributeView.write("file.description", Charset.defaultCharset().encode("This file contains private information"));
            System.out.println("Result is :" + write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void getAttribute() {
        this.defineAttribute();  //먼저 선행되어야 함
        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);

        try {
            System.out.println(userDefinedFileAttributeView.size("file.description"));
            ByteBuffer byteBuffer = ByteBuffer.allocate(userDefinedFileAttributeView.size("file.description"));
            userDefinedFileAttributeView.read("file.description", byteBuffer);
            byteBuffer.flip();

            System.out.println(Charset.defaultCharset().decode(byteBuffer).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ChapterLogger
    public void delete(){
        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);

        try {
            userDefinedFileAttributeView.delete("file.description");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
