package me.advanced.java.java7.nio2.file_networking.ch02.meta_attributes;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Created by taesu on 2018-07-26.
 */
@Service
public class FileStore implements ApplicationRunner {
    private Path path;

    public FileStore() {
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
    public void getAllFileStores() {
        FileSystems.getDefault().getFileStores()
                .forEach(fileStore -> {
                    try {
                        System.out.println("Store name is " + fileStore.name() + " -------- type is " + fileStore.type());
                        System.out.println("total space " + fileStore.getTotalSpace() / 1024);
                        System.out.println("Used space= " + (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()) / 1024);
                        System.out.println("Available space " + fileStore.getUsableSpace() / 1024);
                        System.out.println("is read only ? " + fileStore.isReadOnly());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    @ChapterLogger
    public void getFileStoreByFile(){
        try{
            java.nio.file.FileStore fileStore = Files.getFileStore(path);

            System.out.println("Store name is " + fileStore.name() + " -------- type is " + fileStore.type());
            System.out.println("total space " + fileStore.getTotalSpace() / 1024);
            System.out.println("Used space= " + (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()) / 1024);
            System.out.println("Available space " + fileStore.getUsableSpace() / 1024);
            System.out.println("is read only ? " + fileStore.isReadOnly());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
