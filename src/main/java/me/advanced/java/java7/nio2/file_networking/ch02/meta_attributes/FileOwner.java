package me.advanced.java.java7.nio2.file_networking.ch02.meta_attributes;

import me.advanced.java.config.aop.ChapterLogger;
import me.advanced.java.config.aop.ChapterRunner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project java-demo
 * @since 2018-07-26
 */
@Service
public class FileOwner implements ApplicationRunner {
	private Path path;
	
	public FileOwner() {
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
	public void getOwner() {
		FileOwnerAttributeView attributes = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
		try {
			System.out.println(attributes.getOwner().getName()
			);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@ChapterLogger
	public void setOwner(){
		//반드시 시스템에 존재하는 사용자이어야 한다
//		try {
//			Files.setOwner(path, () -> "taesu");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
